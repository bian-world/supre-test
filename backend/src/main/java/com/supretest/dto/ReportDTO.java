package com.supretest.dto;

import com.supretest.base.domain.LoadTestReportWithBLOBs;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO extends LoadTestReportWithBLOBs {

    private String content;
    private String projectName;
    private String userName;
    private String versionName;
}
