package com.supretest.track.dto;

import com.supretest.api.dto.automation.TestPlanFailureApiDTO;
import com.supretest.api.dto.automation.TestPlanFailureScenarioDTO;
import com.supretest.base.domain.IssuesDao;
import com.supretest.base.domain.TestPlanReportContent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TestPlanSimpleReportDTO extends TestPlanReportContent {
    private String name;
    private String projectName;
    private int executeCount;
    private int passCount;
    private String summary;
    private String config;

    /**
     * 导出保存国际化
     */
    private String lang;

    private TestPlanFunctionResultReportDTO functionResult;
    private TestPlanApiResultReportDTO apiResult;
    private TestPlanLoadResultReportDTO loadResult;

    List<TestPlanCaseDTO> functionFailureCases;
    List<TestPlanCaseDTO> functionAllCases;
    List<IssuesDao> issueList;
    List<TestPlanFailureApiDTO> apiFailureCases;
    List<TestPlanFailureApiDTO> apiAllCases;
    List<TestPlanFailureScenarioDTO> scenarioFailureCases;
    List<TestPlanFailureScenarioDTO> scenarioAllCases;
    List<TestPlanLoadCaseDTO> loadAllCases;
    List<TestPlanLoadCaseDTO> loadFailureCases;
    List<TestPlanFailureApiDTO> errorReportCases;
    List<TestPlanFailureScenarioDTO> errorReportScenarios;
    List<TestPlanFailureApiDTO> unExecuteCases;
    List<TestPlanFailureScenarioDTO> unExecuteScenarios;
    List<TestPlanPublishedModuleDTO> publishedModule;
}
