package com.supretest.track.domain;

import com.supretest.commons.constants.TestPlanTestCaseStatus;
import com.supretest.track.dto.TestCaseReportMetricDTO;
import com.supretest.track.dto.TestPlanCaseDTO;
import com.supretest.track.dto.TestPlanDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReportFailureResultComponent extends ReportComponent {
    private List<TestPlanCaseDTO> failureTestCases = new ArrayList<>();

    public ReportFailureResultComponent(TestPlanDTO testPlan) {
        super(testPlan);
        componentId = "4";
    }

    @Override
    public void readRecord(TestPlanCaseDTO testCase) {
        if (StringUtils.equals(testCase.getStatus(), TestPlanTestCaseStatus.Failure.name())) {
            this.failureTestCases.add(testCase);
        }
    }

    @Override
    public void afterBuild(TestCaseReportMetricDTO testCaseReportMetric) {
//        testCaseReportMetric.setFailureTestCases(failureTestCases);
    }
}
