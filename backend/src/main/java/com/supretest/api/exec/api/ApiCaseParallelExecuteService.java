package com.supretest.api.exec.api;

import com.supretest.api.exec.queue.DBTestQueue;
import com.supretest.api.exec.scenario.ApiScenarioSerialService;
import com.supretest.api.exec.utils.GenerateHashTreeUtil;
import com.supretest.api.jmeter.JMeterService;
import com.supretest.api.jmeter.utils.SmoothWeighted;
import com.supretest.base.domain.ApiDefinitionExecResult;
import com.supretest.commons.utils.CommonBeanFactory;
import io.metersphere.constants.RunModeConstants;
import com.supretest.dto.BaseSystemConfigDTO;
import io.metersphere.dto.JmeterRunRequestDTO;
import io.metersphere.dto.RunModeConfigDTO;
import com.supretest.service.SystemParameterService;
import io.metersphere.vo.BooleanPool;
import org.apache.commons.collections4.MapUtils;
import org.apache.jorphan.collections.HashTree;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ApiCaseParallelExecuteService {
    @Resource
    private ApiScenarioSerialService apiScenarioSerialService;
    @Resource
    private JMeterService jMeterService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void parallel(Map<String, ApiDefinitionExecResult> executeQueue, RunModeConfigDTO config, DBTestQueue executionQueue, String runMode) {
        BooleanPool pool = GenerateHashTreeUtil.isResourcePool(config.getResourcePoolId());
        // 初始化分配策略
        if (pool.isPool()) {
            SmoothWeighted.setServerConfig(config.getResourcePoolId(), redisTemplate);
        }
        // 获取可以执行的资源池
        BaseSystemConfigDTO baseInfo = CommonBeanFactory.getBean(SystemParameterService.class).getBaseInfo();
        for (String testId : executeQueue.keySet()) {
            ApiDefinitionExecResult result = executeQueue.get(testId);
            String reportId = result.getId();
            JmeterRunRequestDTO runRequest = new JmeterRunRequestDTO(testId, reportId, runMode, null);
            runRequest.setPool(pool);
            runRequest.setTestPlanReportId(executionQueue.getReportId());
            runRequest.setPoolId(config.getResourcePoolId());
            runRequest.setReportType(executionQueue.getReportType());
            runRequest.setRunType(RunModeConstants.PARALLEL.toString());
            runRequest.setQueueId(executionQueue.getId());
            if (MapUtils.isNotEmpty(executionQueue.getDetailMap())) {
                runRequest.setPlatformUrl(GenerateHashTreeUtil.getPlatformUrl(baseInfo, runRequest, executionQueue.getDetailMap().get(result.getId())));
            }
            if (!pool.isPool()) {
                HashTree hashTree = apiScenarioSerialService.generateHashTree(testId, config.getEnvMap(), runRequest);
                runRequest.setHashTree(hashTree);
            }
            jMeterService.run(runRequest);
        }
    }
}
