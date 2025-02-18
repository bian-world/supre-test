package com.supretest.dto;

import com.supretest.api.dto.automation.TestPlanFailureApiDTO;
import com.supretest.api.dto.automation.TestPlanFailureScenarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class TestPlanExecuteReportDTO {
    private Map<String,String> testPlanApiCaseIdAndReportIdMap;
    private Map<String,String> testPlanScenarioIdAndReportIdMap;
    private Map<String,String> testPlanLoadCaseIdAndReportIdMap;
    private List<TestPlanFailureApiDTO> apiCaseInfoDTOList;
    private List<TestPlanFailureScenarioDTO> scenarioInfoDTOList;
}
