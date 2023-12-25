package com.supretest.api.exec.utils;

import com.supretest.api.dto.definition.BatchRunDefinitionRequest;
import com.supretest.base.domain.ApiDefinitionExecResult;
import com.supretest.base.domain.ApiTestCase;
import com.supretest.base.domain.TestPlanApiCase;
import com.supretest.commons.constants.ApiRunMode;
import com.supretest.commons.constants.ReportTypeConstants;
import com.supretest.commons.constants.TriggerMode;
import com.supretest.commons.utils.SessionUtils;
import io.metersphere.dto.RunModeConfigDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ApiDefinitionExecResultUtil {
    public static ApiDefinitionExecResult initBase(String resourceId, String status, String reportId, RunModeConfigDTO config) {
        ApiDefinitionExecResult apiResult = new ApiDefinitionExecResult();
        if (StringUtils.isEmpty(reportId)) {
            apiResult.setId(UUID.randomUUID().toString());
        } else {
            apiResult.setId(reportId);
        }
        apiResult.setCreateTime(System.currentTimeMillis());
        apiResult.setStartTime(System.currentTimeMillis());
        apiResult.setEndTime(System.currentTimeMillis());
        apiResult.setTriggerMode(TriggerMode.BATCH.name());
        apiResult.setActuator("LOCAL");
        if (config != null && GenerateHashTreeUtil.isResourcePool(config.getResourcePoolId()).isPool()) {
            apiResult.setActuator(config.getResourcePoolId());
        }
        apiResult.setUserId(Objects.requireNonNull(SessionUtils.getUser()).getId());
        apiResult.setResourceId(resourceId);
        apiResult.setReportType(ReportTypeConstants.API_INDEPENDENT.name());
        apiResult.setStartTime(System.currentTimeMillis());
        apiResult.setType(ApiRunMode.DEFINITION.name());
        apiResult.setStatus(status);
        return apiResult;
    }

    public static ApiDefinitionExecResult addResult(BatchRunDefinitionRequest request, TestPlanApiCase key, String status,
                                                    Map<String, ApiTestCase> caseMap, String poolId) {
        ApiDefinitionExecResult apiResult = new ApiDefinitionExecResult();
        apiResult.setId(UUID.randomUUID().toString());
        apiResult.setCreateTime(System.currentTimeMillis());
        apiResult.setStartTime(System.currentTimeMillis());
        apiResult.setEndTime(System.currentTimeMillis());
        apiResult.setReportType(ReportTypeConstants.API_INDEPENDENT.name());
        ApiTestCase testCase = caseMap.get(key.getApiCaseId());
        if (testCase != null) {
            apiResult.setName(testCase.getName());
            apiResult.setProjectId(testCase.getProjectId());
            apiResult.setVersionId(testCase.getVersionId());
        }
        apiResult.setTriggerMode(request.getTriggerMode());
        apiResult.setActuator("LOCAL");
        if (StringUtils.isNotEmpty(poolId)) {
            apiResult.setActuator(poolId);
        }
        if (StringUtils.isEmpty(request.getUserId())) {
            if (SessionUtils.getUser() != null) {
                apiResult.setUserId(SessionUtils.getUser().getId());
            }
        } else {
            apiResult.setUserId(request.getUserId());
        }

        apiResult.setResourceId(key.getApiCaseId());
        apiResult.setStartTime(System.currentTimeMillis());
        apiResult.setType(ApiRunMode.API_PLAN.name());
        apiResult.setStatus(status);
        apiResult.setContent(request.getPlanReportId());
        return apiResult;
    }

    public static ApiDefinitionExecResult add(String resourceId, String status, String reportId) {
        ApiDefinitionExecResult apiResult = new ApiDefinitionExecResult();
        if (StringUtils.isEmpty(reportId)) {
            apiResult.setId(UUID.randomUUID().toString());
        } else {
            apiResult.setId(reportId);
        }
        apiResult.setReportType(ReportTypeConstants.API_INDEPENDENT.name());
        apiResult.setCreateTime(System.currentTimeMillis());
        apiResult.setStartTime(System.currentTimeMillis());
        apiResult.setEndTime(System.currentTimeMillis());
        apiResult.setTriggerMode(TriggerMode.BATCH.name());
        apiResult.setActuator("LOCAL");
        apiResult.setUserId(Objects.requireNonNull(SessionUtils.getUser()).getId());
        apiResult.setResourceId(resourceId);
        apiResult.setStartTime(System.currentTimeMillis());
        apiResult.setType(ApiRunMode.DEFINITION.name());
        apiResult.setStatus(status);
        return apiResult;
    }
}
