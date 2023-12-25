/**
 *
 */
package com.supretest.track.service.utils;

import com.supretest.base.domain.TestPlanLoadCaseWithBLOBs;
import com.supretest.base.mapper.TestPlanLoadCaseMapper;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.LogUtil;
import com.supretest.performance.request.RunTestPlanRequest;
import com.supretest.performance.service.PerformanceTestService;

import java.util.concurrent.Callable;

public class ParallelExecTask<T> implements Callable<T> {
    private RunTestPlanRequest request;
    private PerformanceTestService performanceTestService;
    private TestPlanLoadCaseMapper testPlanLoadCaseMapper;

    public ParallelExecTask(PerformanceTestService performanceTestService, TestPlanLoadCaseMapper testPlanLoadCaseMapper, RunTestPlanRequest request) {
        this.performanceTestService = performanceTestService;
        this.testPlanLoadCaseMapper = testPlanLoadCaseMapper;
        this.request = request;
    }

    @Override
    public T call() {
        try {
            String reportId = performanceTestService.run(request);
            TestPlanLoadCaseWithBLOBs testPlanLoadCase = new TestPlanLoadCaseWithBLOBs();
            testPlanLoadCase.setId(request.getTestPlanLoadId());
            testPlanLoadCase.setLoadReportId(reportId);
            testPlanLoadCase.setStatus("run");
            testPlanLoadCaseMapper.updateByPrimaryKeySelective(testPlanLoadCase);
            return (T) reportId;

        } catch (Exception ex) {
            LogUtil.error(ex);
            MSException.throwException(ex.getMessage());
            return null;
        }
    }
}
