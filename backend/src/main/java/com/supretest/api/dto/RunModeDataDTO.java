package com.supretest.api.dto;

import com.supretest.api.dto.automation.APIScenarioReportResult;
import com.supretest.base.domain.ApiScenarioWithBLOBs;
import com.supretest.base.domain.UiScenarioWithBLOBs;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RunModeDataDTO {
    // 执行HashTree
    private ApiScenarioWithBLOBs scenario;

    // 执行HashTree
    private UiScenarioWithBLOBs UiScenario;
    // 测试场景/测试用例
    private String testId;

    private String reportId;
    // 初始化报告
    private APIScenarioReportResult report;
    //
    private String apiCaseId;

    private Map<String, String> planEnvMap;

    public RunModeDataDTO() {

    }

    public RunModeDataDTO(APIScenarioReportResult report, String testId) {
        this.report = report;
        this.testId = testId;
    }
}
