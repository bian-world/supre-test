package com.supretest.api.dto.automation;

import com.supretest.base.domain.ApiScenarioWithBLOBs;
import io.metersphere.dto.RunModeConfigDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class RunTestPlanScenarioRequest extends ApiScenarioWithBLOBs {

    private String reportId;

    private String environmentId;

    private String triggerMode;

    private String executeType;

    private String runMode;

    /**
     * 测试情景和测试计划的关联ID
     */
    private String planScenarioId;

    private List<String> planCaseIds;

    private List<String> ids;

    private String reportUserID;

    private Map<String, String> scenarioTestPlanIdMap;

    private TestPlanScenarioRequest condition;

    private RunModeConfigDTO config;
}
