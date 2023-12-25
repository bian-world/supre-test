package com.supretest.api.jmeter;

import com.alibaba.fastjson.JSON;
import com.supretest.api.exec.queue.ExecThreadPoolExecutor;
import com.supretest.api.exec.utils.GenerateHashTreeUtil;
import com.supretest.api.jmeter.utils.ServerConfig;
import com.supretest.api.jmeter.utils.SmoothWeighted;
import com.supretest.api.service.ApiScenarioReportService;
import com.supretest.api.service.RemakeReportService;
import com.supretest.base.domain.TestResource;
import com.supretest.commons.constants.ApiRunMode;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.config.JmeterProperties;
import com.supretest.config.KafkaConfig;
import io.metersphere.constants.RunModeConstants;
import io.metersphere.dto.JmeterRunRequestDTO;
import io.metersphere.dto.JvmInfoDTO;
import com.supretest.dto.NodeDTO;
import io.metersphere.jmeter.JMeterBase;
import io.metersphere.jmeter.LocalRunner;
import com.supretest.performance.engine.Engine;
import com.supretest.performance.engine.EngineFactory;
import io.metersphere.utils.LoggerUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class JMeterService {
    public static final String BASE_URL = "http://%s:%d";
    @Resource
    private JmeterProperties jmeterProperties;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    private void init() {
        String JMETER_HOME = getJmeterHome();

        String JMETER_PROPERTIES = JMETER_HOME + "/bin/jmeter.properties";
        JMeterUtils.loadJMeterProperties(JMETER_PROPERTIES);
        JMeterUtils.setJMeterHome(JMETER_HOME);
        JMeterUtils.setLocale(LocaleContextHolder.getLocale());
    }

    public String getJmeterHome() {
        String home = getClass().getResource("/").getPath() + "jmeter";
        try {
            File file = new File(home);
            if (file.exists()) {
                return home;
            } else {
                return jmeterProperties.getHome();
            }
        } catch (Exception e) {
            return jmeterProperties.getHome();
        }
    }

    private void addDebugListener(String testId, HashTree testPlan) {
        MsDebugListener resultCollector = new MsDebugListener();
        resultCollector.setName(testId);
        resultCollector.setProperty(TestElement.TEST_CLASS, MsDebugListener.class.getName());
        resultCollector.setProperty(TestElement.GUI_CLASS, SaveService.aliasToClass("ViewResultsFullVisualizer"));
        resultCollector.setEnabled(true);
        testPlan.add(testPlan.getArray()[0], resultCollector);
    }

    private void runLocal(JmeterRunRequestDTO request) {
        init();
        if (!FixedCapacityUtils.jmeterLogTask.containsKey(request.getReportId())) {
            FixedCapacityUtils.jmeterLogTask.put(request.getReportId(), System.currentTimeMillis());
        }
        if (StringUtils.isNotEmpty(request.getTestPlanReportId())
                && !FixedCapacityUtils.jmeterLogTask.containsKey(request.getTestPlanReportId())
                && StringUtils.equals(request.getReportType(), RunModeConstants.SET_REPORT.toString())) {
            FixedCapacityUtils.jmeterLogTask.put(request.getTestPlanReportId(), System.currentTimeMillis());
        }
        LoggerUtil.debug("监听MessageCache.tasks当前容量：" + FixedCapacityUtils.jmeterLogTask.size());
        if (request.isDebug() && !StringUtils.equalsAny(request.getRunMode(), ApiRunMode.DEFINITION.name())) {
            LoggerUtil.debug("为请求 [ " + request.getReportId() + " ] 添加同步接收结果 Listener");
            JMeterBase.addBackendListener(request, request.getHashTree(), APISingleResultListener.class.getCanonicalName());
        }

        if (MapUtils.isNotEmpty(request.getExtendedParameters())
                && request.getExtendedParameters().containsKey("SYN_RES")
                && (Boolean) request.getExtendedParameters().get("SYN_RES")) {
            LoggerUtil.debug("为请求 [ " + request.getReportId() + " ] 添加Debug Listener");
            addDebugListener(request.getReportId(), request.getHashTree());
        }

        if (request.isDebug()) {
            LoggerUtil.debug("为请求 [ " + request.getReportId() + " ] 添加Debug Listener");
            addDebugListener(request.getReportId(), request.getHashTree());
        } else {
            LoggerUtil.debug("为请求 [ " + request.getReportId() + " ] 添加同步接收结果 Listener");
            JMeterBase.addBackendListener(request, request.getHashTree(), APISingleResultListener.class.getCanonicalName());
        }

        LocalRunner runner = new LocalRunner(request.getHashTree());
        runner.run(request.getReportId());
    }

    private void runNode(JmeterRunRequestDTO request) {
        request.setKafkaConfig(KafkaConfig.getKafka());
        // 如果是K8S调用
        if (request.getPool().isK8s()) {
            try {
                LoggerUtil.error("开始发送请求[ " + request.getTestId() + " ] 到K8S节点执行");
                final Engine engine = EngineFactory.createApiEngine(request);
                engine.start();
            } catch (Exception e) {
                LoggerUtil.error("调用K8S执行请求[ " + request.getTestId() + " ] 失败：", e);
                ApiScenarioReportService apiScenarioReportService = CommonBeanFactory.getBean(ApiScenarioReportService.class);
                apiScenarioReportService.delete(request.getReportId());
                MSException.throwException(e.getMessage());
            }
        } else {
            this.send(request);
        }
    }

    private void send(JmeterRunRequestDTO request) {
        try {
            if (redisTemplate.opsForValue().get(SmoothWeighted.EXEC_INDEX + request.getPoolId()) != null) {
                long index = Long.parseLong(redisTemplate.opsForValue().get(SmoothWeighted.EXEC_INDEX + request.getPoolId()).toString());
                redisTemplate.opsForValue().set(SmoothWeighted.EXEC_INDEX + request.getPoolId(), index + 1);
            }
            ServerConfig config = SmoothWeighted.calculate(request.getPoolId(), redisTemplate);
            if (config == null) {
                config = SmoothWeighted.getResource(request.getPoolId());
            }
            if (config == null) {
                LoggerUtil.info("未获取到资源池，请检查配置【系统设置-系统-测试资源池】");
                RemakeReportService remakeReportService = CommonBeanFactory.getBean(RemakeReportService.class);
                remakeReportService.remake(request);
                return;
            }
            request.setCorePoolSize(config.getCorePoolSize());
            request.setEnable(config.isEnable());
            LoggerUtil.info("开始发送请求【 " + request.getReportId() + " 】,资源【 " + request.getTestId() + " 】" + config.getUrl() + " 节点执行");
            ResponseEntity<String> result = restTemplate.postForEntity(config.getUrl(), request, String.class);
            if (result == null || !StringUtils.equals("SUCCESS", result.getBody())) {
                RemakeReportService remakeReportService = CommonBeanFactory.getBean(RemakeReportService.class);
                remakeReportService.remake(request);
                LoggerUtil.error("发送请求[ " + request.getTestId() + " ] 到" + config.getUrl() + " 节点执行失败");
                LoggerUtil.info(result.getBody());
            }
        } catch (Exception e) {
            RemakeReportService remakeReportService = CommonBeanFactory.getBean(RemakeReportService.class);
            remakeReportService.remake(request);
            LoggerUtil.error("发送请求[ " + request.getTestId() + " ] 执行失败：", e);
            LoggerUtil.error(e);
        }
    }


    public void run(JmeterRunRequestDTO request) {
        if (request.getPool().isPool()) {
            this.runNode(request);
        } else {
            CommonBeanFactory.getBean(ExecThreadPoolExecutor.class).addTask(request);
        }
    }

    @Deprecated
    public void run(JmeterRunRequestDTO request, List<TestResource> resources) {
        if (request.getPool().isPool()) {
            this.runNode(request);
        } else {
            CommonBeanFactory.getBean(ExecThreadPoolExecutor.class).addTask(request);
        }
    }

    public void addQueue(JmeterRunRequestDTO request) {
        this.runLocal(request);
    }

    public boolean getRunningQueue(String poolId, String reportId) {
        try {
            List<JvmInfoDTO> resources = GenerateHashTreeUtil.setPoolResource(poolId);
            if (CollectionUtils.isEmpty(resources)) {
                return false;
            }
            boolean isRunning = false;
            for (JvmInfoDTO testResource : resources) {
                String configuration = testResource.getTestResource().getConfiguration();
                NodeDTO node = JSON.parseObject(configuration, NodeDTO.class);
                String nodeIp = node.getIp();
                Integer port = node.getPort();
                String uri = String.format(BASE_URL + "/jmeter/get/running/queue/" + reportId, nodeIp, port);
                ResponseEntity<Boolean> result = restTemplate.getForEntity(uri, Boolean.class);
                if (result != null && result.getBody()) {
                    isRunning = true;
                    break;
                }
            }
            return isRunning;
        } catch (Exception e) {
            return false;
        }
    }
}
