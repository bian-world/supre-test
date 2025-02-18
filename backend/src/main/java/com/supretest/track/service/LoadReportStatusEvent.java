package com.supretest.track.service;

import com.supretest.base.domain.LoadTestReport;
import com.supretest.base.mapper.ext.ExtTestPlanLoadCaseMapper;
import com.supretest.commons.constants.PerformanceTestStatus;
import com.supretest.commons.constants.ReportTriggerMode;
import com.supretest.commons.constants.TestPlanLoadCaseStatus;
import com.supretest.commons.consumer.LoadTestFinishEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional(rollbackFor = Exception.class)
public class LoadReportStatusEvent implements LoadTestFinishEvent {

    @Resource
    private ExtTestPlanLoadCaseMapper extTestPlanLoadCaseMapper;

    private void updateLoadCaseStatus(LoadTestReport loadTestReport) {
        String reportId = loadTestReport.getId();
        String status = loadTestReport.getStatus();
        if (StringUtils.isNotBlank(reportId)) {
            String result = "";
            if (StringUtils.equals(PerformanceTestStatus.Error.name(), status)) {
                result = TestPlanLoadCaseStatus.error.name();
            }
            if (StringUtils.equals(PerformanceTestStatus.Completed.name(), status)) {
                result = TestPlanLoadCaseStatus.success.name();
            }
            extTestPlanLoadCaseMapper.updateCaseStatus(reportId, result);
        }
    }

    @Override
    public void execute(LoadTestReport loadTestReport) {
        if (StringUtils.equals(ReportTriggerMode.MANUAL.name(), loadTestReport.getTriggerMode())
                || StringUtils.equals(ReportTriggerMode.BATCH.name(), loadTestReport.getTriggerMode())
                || StringUtils.equals(ReportTriggerMode.TEST_PLAN_SCHEDULE.name(), loadTestReport.getTriggerMode())
                || StringUtils.equals(ReportTriggerMode.TEST_PLAN_API.name(), loadTestReport.getTriggerMode())) {
            if (StringUtils.equalsAny(loadTestReport.getStatus(),
                    PerformanceTestStatus.Completed.name(), PerformanceTestStatus.Error.name())) {
                updateLoadCaseStatus(loadTestReport);
            }
        }
    }
}
