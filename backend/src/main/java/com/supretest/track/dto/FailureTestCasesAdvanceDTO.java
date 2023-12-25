package com.supretest.track.dto;

import com.supretest.api.dto.automation.ApiScenarioDTO;
import com.supretest.api.dto.definition.TestPlanApiCaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FailureTestCasesAdvanceDTO {
    private List<TestPlanCaseDTO> functionalTestCases;
    private List<TestPlanApiCaseDTO> apiTestCases;
    private List<ApiScenarioDTO> scenarioTestCases;
    private List<TestPlanLoadCaseDTO> loadTestCases;
}
