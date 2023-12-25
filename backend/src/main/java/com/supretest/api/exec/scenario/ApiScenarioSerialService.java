package com.supretest.api.exec.scenario;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supretest.api.dto.definition.request.ElementUtil;
import com.supretest.api.dto.definition.request.MsTestPlan;
import com.supretest.api.dto.definition.request.MsThreadGroup;
import com.supretest.api.dto.definition.request.ParameterConfig;
import com.supretest.api.dto.definition.request.sampler.MsDubboSampler;
import com.supretest.api.dto.definition.request.sampler.MsHTTPSamplerProxy;
import com.supretest.api.dto.definition.request.sampler.MsJDBCSampler;
import com.supretest.api.dto.definition.request.sampler.MsTCPSampler;
import com.supretest.api.exec.utils.GenerateHashTreeUtil;
import com.supretest.api.jmeter.JMeterService;
import com.supretest.api.jmeter.utils.SmoothWeighted;
import com.supretest.api.service.ApiExecutionQueueService;
import com.supretest.api.service.ApiTestEnvironmentService;
import com.supretest.api.service.RemakeReportService;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.*;
import com.supretest.commons.constants.APITestStatus;
import com.supretest.commons.constants.ApiRunMode;
import com.supretest.commons.utils.BeanUtils;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.commons.utils.HashTreeUtil;
import com.supretest.commons.utils.LogUtil;
import com.supretest.ui.dto.definition.request.StUiScenario;
import io.metersphere.constants.RunModeConstants;
import com.supretest.dto.BaseSystemConfigDTO;
import io.metersphere.dto.JmeterRunRequestDTO;
import io.metersphere.dto.ResultDTO;
import io.metersphere.plugin.core.MsTestElement;
import com.supretest.service.SystemParameterService;
import io.metersphere.utils.LoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.jorphan.collections.HashTree;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ApiScenarioSerialService {
    @Resource
    private ApiScenarioReportMapper apiScenarioReportMapper;
    @Resource
    private JMeterService jMeterService;
    @Resource
    private ApiScenarioMapper apiScenarioMapper;
    @Resource
    private UiScenarioMapper uiScenarioMapper;
    @Resource
    private ApiDefinitionExecResultMapper apiDefinitionExecResultMapper;
    @Resource
    private TestPlanApiCaseMapper testPlanApiCaseMapper;
    @Resource
    private ApiTestCaseMapper apiTestCaseMapper;
    @Resource
    private TestPlanApiScenarioMapper testPlanApiScenarioMapper;
    @Resource
    private ApiScenarioEnvService apiScenarioEnvService;
    @Resource
    private ObjectMapper mapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void serial(ApiExecutionQueue executionQueue, ApiExecutionQueueDetail queue) {
        LoggerUtil.debug("Scenario run-执行脚本装载-进入串行准备");
        // 获取可以执行的资源池
        BaseSystemConfigDTO baseInfo = CommonBeanFactory.getBean(SystemParameterService.class).getBaseInfo();
        if (!StringUtils.equals(executionQueue.getReportType(), RunModeConstants.SET_REPORT.toString())
                || StringUtils.equalsIgnoreCase(executionQueue.getRunMode(), ApiRunMode.DEFINITION.name())) {
            if (StringUtils.equalsAny(executionQueue.getRunMode(), ApiRunMode.SCENARIO.name(), ApiRunMode.SCENARIO_PLAN.name(), ApiRunMode.SCHEDULE_SCENARIO_PLAN.name(), ApiRunMode.SCHEDULE_SCENARIO.name(), ApiRunMode.JENKINS_SCENARIO_PLAN.name())) {
                ApiScenarioReport report = apiScenarioReportMapper.selectByPrimaryKey(queue.getReportId());
                if (report != null) {
                    report.setStatus(APITestStatus.Running.name());
                    report.setCreateTime(System.currentTimeMillis());
                    report.setUpdateTime(System.currentTimeMillis());
                    apiScenarioReportMapper.updateByPrimaryKey(report);
                }
            } else  if (StringUtils.equalsAny(executionQueue.getRunMode(), ApiRunMode.UI_SCENARIO.name(), ApiRunMode.UI_SCENARIO_PLAN.name(), ApiRunMode.UI_SCHEDULE_SCENARIO_PLAN.name(), ApiRunMode.UI_SCHEDULE_SCENARIO.name(), ApiRunMode.UI_JENKINS_SCENARIO_PLAN.name())) {
                ApiScenarioReport report = apiScenarioReportMapper.selectByPrimaryKey(queue.getReportId());
                if (report != null) {
                    report.setStatus(APITestStatus.Running.name());
                    report.setCreateTime(System.currentTimeMillis());
                    report.setUpdateTime(System.currentTimeMillis());
                    apiScenarioReportMapper.updateByPrimaryKey(report);
                }
            }
            else {
                ApiDefinitionExecResult execResult = apiDefinitionExecResultMapper.selectByPrimaryKey(queue.getReportId());
                if (execResult != null) {
                    execResult.setStatus(APITestStatus.Running.name());
                    apiDefinitionExecResultMapper.updateByPrimaryKeySelective(execResult);
                }
            }
        }

        LoggerUtil.info("Scenario run-开始执行，队列ID：【 " + executionQueue.getReportId() + " 】");
        String reportId = StringUtils.isNotEmpty(executionQueue.getReportId()) ? executionQueue.getReportId() : queue.getReportId();
        if (!StringUtils.equalsAny(executionQueue.getRunMode(), ApiRunMode.SCENARIO.name())) {
            reportId = queue.getReportId();
        }
        HashTree hashTree = null;
        JmeterRunRequestDTO runRequest = new JmeterRunRequestDTO(queue.getTestId(), reportId, executionQueue.getRunMode(), hashTree);
        runRequest.setReportType(executionQueue.getReportType());
        runRequest.setPool(GenerateHashTreeUtil.isResourcePool(executionQueue.getPoolId()));
        runRequest.setTestPlanReportId(executionQueue.getReportId());
        runRequest.setRunType(RunModeConstants.SERIAL.toString());
        runRequest.setQueueId(executionQueue.getId());
        runRequest.setPoolId(executionQueue.getPoolId());
        try {
            if (StringUtils.isEmpty(executionQueue.getPoolId())) {
                if (StringUtils.equalsAny(executionQueue.getRunMode(), ApiRunMode.SCENARIO.name(), ApiRunMode.SCENARIO_PLAN.name(), ApiRunMode.SCHEDULE_SCENARIO_PLAN.name(), ApiRunMode.SCHEDULE_SCENARIO.name(), ApiRunMode.JENKINS_SCENARIO_PLAN.name() )){
                    ApiScenarioWithBLOBs scenario = null;
                    Map<String, String> planEnvMap = new LinkedHashMap<>();
                    if (StringUtils.equalsAny(executionQueue.getRunMode(), ApiRunMode.SCENARIO.name(), ApiRunMode.SCHEDULE_SCENARIO.name())) {
                        scenario = apiScenarioMapper.selectByPrimaryKey(queue.getTestId());
                    }else {
                        TestPlanApiScenario planApiScenario = testPlanApiScenarioMapper.selectByPrimaryKey(queue.getTestId());
                        if (planApiScenario != null) {
                            planEnvMap = apiScenarioEnvService.planEnvMap(queue.getTestId());
                            queue.setEvnMap(JSON.toJSONString(planEnvMap));
                            scenario = apiScenarioMapper.selectByPrimaryKey(planApiScenario.getApiScenarioId());
                        }
                    }
                    if ((planEnvMap == null || planEnvMap.isEmpty()) && StringUtils.isNotEmpty(queue.getEvnMap())) {
                        planEnvMap = JSON.parseObject(queue.getEvnMap(), Map.class);
                    }
                    hashTree = GenerateHashTreeUtil.generateHashTree(scenario, planEnvMap, runRequest);
                } else if(StringUtils.equalsAny(executionQueue.getRunMode(), ApiRunMode.UI_SCENARIO.name(), ApiRunMode.UI_SCHEDULE_SCENARIO.name())){
                    List<UiScenarioWithBLOBs> scenarios = new ArrayList<>();
                    Map<String, String> planEnvMap = new LinkedHashMap<>();
                    this.getPreScenarios(scenarios, queue.getTestId());
                    if ((planEnvMap == null || planEnvMap.isEmpty()) && StringUtils.isNotEmpty(queue.getEvnMap())) {
                        planEnvMap = JSON.parseObject(queue.getEvnMap(), Map.class);
                    }
                    hashTree = GenerateHashTreeUtil.generateHashTree(scenarios, planEnvMap, runRequest);
                }else {
                    Map<String, String> map = new LinkedHashMap<>();
                    if (StringUtils.isNotEmpty(queue.getEvnMap())) {
                        map = JSON.parseObject(queue.getEvnMap(), Map.class);
                    }
                    hashTree = generateHashTree(queue.getTestId(), map, runRequest);
                }
                // 更新环境变量
                this.initEnv(hashTree);
            }
            runRequest.setHashTree(hashTree);
            if (queue != null) {
                runRequest.setPlatformUrl(GenerateHashTreeUtil.getPlatformUrl(baseInfo, runRequest, queue.getId()));
            }
            if (runRequest.getPool().isPool()) {
                SmoothWeighted.setServerConfig(runRequest.getPoolId(), redisTemplate);
            }
            // 开始执行
            jMeterService.run(runRequest);
        } catch (Exception e) {
            RemakeReportService remakeReportService = CommonBeanFactory.getBean(RemakeReportService.class);
            remakeReportService.remake(runRequest);
            ResultDTO dto = new ResultDTO();
            BeanUtils.copyBean(dto, runRequest);
            CommonBeanFactory.getBean(ApiExecutionQueueService.class).queueNext(dto);
            LoggerUtil.error("执行终止：", e);
        }
    }

    private void getPreScenarios(List<UiScenarioWithBLOBs> list, String id){
        if(StringUtils.isEmpty(id)){
            return;
        }
           UiScenarioWithBLOBs scenario = uiScenarioMapper.selectByPrimaryKey(id);
           StUiScenario  stUiScenario = JSONObject.parseObject(scenario.getScenarioDefinition(), StUiScenario.class, Feature.DisableSpecialKeyDetect);
           list.add(scenario);
           this.getPreScenarios(list, stUiScenario.getPreScenarioId());

    }

    private void initEnv(HashTree hashTree) {
        ApiTestEnvironmentService apiTestEnvironmentService = CommonBeanFactory.getBean(ApiTestEnvironmentService.class);
        HashTreeUtil hashTreeUtil = new HashTreeUtil();
        Map<String, Map<String, String>> envParamsMap = hashTreeUtil.getEnvParamsDataByHashTree(hashTree, apiTestEnvironmentService);
        hashTreeUtil.mergeParamDataMap(null, envParamsMap);
    }

    public HashTree generateHashTree(String testId, Map<String, String> envMap, JmeterRunRequestDTO runRequest) {
        try {
            ApiTestCaseWithBLOBs caseWithBLOBs = apiTestCaseMapper.selectByPrimaryKey(testId);
            String envId = null;
            if (caseWithBLOBs == null) {
                TestPlanApiCase apiCase = testPlanApiCaseMapper.selectByPrimaryKey(testId);
                if (apiCase != null) {
                    caseWithBLOBs = apiTestCaseMapper.selectByPrimaryKey(apiCase.getApiCaseId());
                    envId = apiCase.getEnvironmentId();
                }
            }
            if (!StringUtils.equals(runRequest.getRunMode(), ApiRunMode.UI_SCHEDULE_SCENARIO.name()) && envMap != null && envMap.containsKey(caseWithBLOBs.getProjectId())) {
                envId = envMap.get(caseWithBLOBs.getProjectId());
            }
            if (caseWithBLOBs != null) {
                HashTree jmeterHashTree = new HashTree();
                MsTestPlan testPlan = new MsTestPlan();
                testPlan.setHashTree(new LinkedList<>());

                MsThreadGroup group = new MsThreadGroup();
                group.setLabel(caseWithBLOBs.getName());
                group.setName(runRequest.getReportId());
                group.setProjectId(caseWithBLOBs.getProjectId());

                MsTestElement testElement = parse(caseWithBLOBs, testId, envId);
                group.setHashTree(new LinkedList<>());
                group.getHashTree().add(testElement);
                testPlan.getHashTree().add(group);
                testPlan.toHashTree(jmeterHashTree, testPlan.getHashTree(), new ParameterConfig());
                return jmeterHashTree;
            }
        } catch (Exception ex) {
            RemakeReportService remakeReportService = CommonBeanFactory.getBean(RemakeReportService.class);
            remakeReportService.remake(runRequest);
            ResultDTO dto = new ResultDTO();
            BeanUtils.copyBean(dto, runRequest);
            CommonBeanFactory.getBean(ApiExecutionQueueService.class).queueNext(dto);
            LoggerUtil.error("生成JMX执行脚本失败：", ex);
        }
        return null;
    }

    private MsTestElement parse(ApiTestCaseWithBLOBs caseWithBLOBs, String planId, String envId) {
        try {
            String api = caseWithBLOBs.getRequest();
            JSONObject element = JSON.parseObject(api, Feature.DisableSpecialKeyDetect);
            ElementUtil.dataFormatting(element);

            LinkedList<MsTestElement> list = new LinkedList<>();
            if (element != null && StringUtils.isNotEmpty(element.getString("hashTree"))) {
                LinkedList<MsTestElement> elements = mapper.readValue(element.getString("hashTree"),
                        new TypeReference<LinkedList<MsTestElement>>() {
                        });
                list.addAll(elements);
            }
            if (element.getString("type").equals("HTTPSamplerProxy")) {
                MsHTTPSamplerProxy httpSamplerProxy = JSON.parseObject(api, MsHTTPSamplerProxy.class, Feature.DisableSpecialKeyDetect);
                httpSamplerProxy.setHashTree(list);
                httpSamplerProxy.setName(planId);
                if (StringUtils.isNotEmpty(envId)) {
                    httpSamplerProxy.setUseEnvironment(envId);
                }
                return httpSamplerProxy;
            }
            if (element.getString("type").equals("TCPSampler")) {
                MsTCPSampler msTCPSampler = JSON.parseObject(api, MsTCPSampler.class, Feature.DisableSpecialKeyDetect);
                if (StringUtils.isNotEmpty(envId)) {
                    msTCPSampler.setUseEnvironment(envId);
                }
                msTCPSampler.setHashTree(list);
                msTCPSampler.setName(planId);
                return msTCPSampler;
            }
            if (element.getString("type").equals("DubboSampler")) {
                MsDubboSampler dubboSampler = JSON.parseObject(api, MsDubboSampler.class, Feature.DisableSpecialKeyDetect);
                if (StringUtils.isNotEmpty(envId)) {
                    dubboSampler.setUseEnvironment(envId);
                }
                dubboSampler.setHashTree(list);
                dubboSampler.setName(planId);
                return dubboSampler;
            }
            if (element.getString("type").equals("JDBCSampler")) {
                MsJDBCSampler jDBCSampler = JSON.parseObject(api, MsJDBCSampler.class);
                if (StringUtils.isNotEmpty(envId)) {
                    jDBCSampler.setUseEnvironment(envId);
                }
                jDBCSampler.setHashTree(list);
                jDBCSampler.setName(planId);
                return jDBCSampler;
            }
        } catch (Exception e) {
            LogUtil.error(e);
        }
        return null;
    }
}
