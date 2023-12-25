package com.supretest.track.domain;

import com.supretest.api.dto.automation.ApiScenarioDTO;
import com.supretest.api.dto.definition.TestPlanApiCaseDTO;
import com.supretest.commons.constants.APITestStatus;
import com.supretest.track.dto.TestCaseReportMetricDTO;
import com.supretest.track.dto.TestPlanCaseDTO;
import com.supretest.track.dto.TestPlanDTO;
import com.supretest.track.dto.TestPlanLoadCaseDTO;
import org.apache.commons.lang3.StringUtils;

public abstract class ReportComponent {
    protected String componentId;
    protected TestPlanDTO testPlan;

    public ReportComponent(TestPlanDTO testPlan) {
        this.testPlan = testPlan;
    }

    public abstract void readRecord(TestPlanCaseDTO testCase);

    public abstract void afterBuild(TestCaseReportMetricDTO testCaseReportMetric);

    public void readRecord(TestPlanApiCaseDTO testCase) {
    }

    public void readRecord(ApiScenarioDTO testCase) {
    }

    public void readRecord(TestPlanLoadCaseDTO testCase) {
    }

}
