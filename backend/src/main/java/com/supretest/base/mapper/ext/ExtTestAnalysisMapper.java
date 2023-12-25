package com.supretest.base.mapper.ext;


import com.supretest.reportstatistics.dto.TestAnalysisChartRequest;
import com.supretest.reportstatistics.dto.TestAnalysisChartResult;

import java.util.List;

public interface ExtTestAnalysisMapper {

    List<TestAnalysisChartResult> getCraeteCaseReport(TestAnalysisChartRequest request);

    List<TestAnalysisChartResult> getUpdateCaseReport(TestAnalysisChartRequest request);
}
