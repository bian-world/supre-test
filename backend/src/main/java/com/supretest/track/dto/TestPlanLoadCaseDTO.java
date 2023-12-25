package com.supretest.track.dto;

import com.supretest.base.domain.LoadTestReportWithBLOBs;
import com.supretest.base.domain.TestPlanLoadCase;
import com.supretest.base.domain.TestPlanLoadCaseWithBLOBs;
import com.supretest.dto.LogDetailDTO;
import com.supretest.performance.base.*;
import com.supretest.performance.dto.LoadTestExportJmx;
import com.supretest.performance.dto.MetricData;
import com.supretest.performance.dto.Monitor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TestPlanLoadCaseDTO extends TestPlanLoadCaseWithBLOBs {
    private String userName;
    private String caseName;
    private String projectName;
    private String caseStatus;
    private String num;
    private String name;
    private ResponseDTO response;
    private String reportId;
    private String versionId;
    private String versionName;

    @Getter
    @Setter
    public static class ResponseDTO extends LoadTestReportWithBLOBs {
        private long duration;
        private long startTime;
        private long endTime;
        private String fixLoadConfiguration;
//        private LoadTestExportJmx jmxContent;
        private List<LoadTestExportJmx> fixJmxContent;
        private TestOverview testOverview;
        private List<ChartsData> loadChartData;
        private List<ChartsData> responseTimeChartData;
        private List<ChartsData> errorChartData;
        private List<ChartsData> responseCodeChartData;
        private Map<String, List<ChartsData>> checkOptions;
        private List<Statistics> reportStatistics;
        private List<Errors> reportErrors;
        private List<ErrorsTop5> reportErrorsTop5;
        private List<LogDetailDTO> reportLogResource;
        private List<Monitor> reportResource;
        private  List<MetricData> metricData;
    }
}
