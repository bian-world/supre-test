/**
 *
 */
package com.supretest.track.service.utils;

import com.supretest.base.domain.LoadTestReportWithBLOBs;
import com.supretest.base.domain.TestPlanLoadCaseWithBLOBs;
import com.supretest.base.mapper.LoadTestReportMapper;
import com.supretest.base.mapper.TestPlanLoadCaseMapper;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.LogUtil;
import com.supretest.performance.request.RunTestPlanRequest;
import com.supretest.performance.service.PerformanceTestService;

import java.util.concurrent.Callable;

public class SerialExecTask<T> implements Callable<T> {
    private RunTestPlanRequest request;
    private PerformanceTestService performanceTestService;
    private TestPlanLoadCaseMapper testPlanLoadCaseMapper;
    private LoadTestReportMapper loadTestReportMapper;

    public SerialExecTask(PerformanceTestService performanceTestService, TestPlanLoadCaseMapper testPlanLoadCaseMapper,LoadTestReportMapper loadTestReportMapper, RunTestPlanRequest request) {
        this.performanceTestService = performanceTestService;
        this.testPlanLoadCaseMapper = testPlanLoadCaseMapper;
        this.loadTestReportMapper = loadTestReportMapper;
        this.request = request;
    }

    @Override
    public T call() {
        try {
            // 串行，开启轮询等待
            String reportId = performanceTestService.run(request);
            TestPlanLoadCaseWithBLOBs testPlanLoadCase = new TestPlanLoadCaseWithBLOBs();
            testPlanLoadCase.setId(request.getTestPlanLoadId());
            testPlanLoadCase.setLoadReportId(reportId);
            testPlanLoadCase.setStatus("run");
            testPlanLoadCaseMapper.updateByPrimaryKeySelective(testPlanLoadCase);
            LoadTestReportWithBLOBs report = null;
            // 轮询查看报告状态，最多200次，防止死循环
            int index = 1;
            while (index < 200) {
                Thread.sleep(3000);
                index++;
                report = loadTestReportMapper.selectByPrimaryKey(reportId);
                if (report != null && (report.getStatus().equals("Completed") || report.getStatus().equals("Error") || report.getStatus().equals("Saved"))) {
                    break;
                }
            }
            return (T) report;

        } catch (Exception ex) {
            LogUtil.error(ex);
            MSException.throwException(ex.getMessage());
            return null;
        }
    }
}
