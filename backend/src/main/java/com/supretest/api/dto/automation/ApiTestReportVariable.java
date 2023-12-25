package com.supretest.api.dto.automation;

import com.supretest.base.domain.ApiTestReport;
import lombok.Data;

@Data
public class ApiTestReportVariable extends ApiTestReport {
    public String executionTime;
    public String executor;
    public String environment;
    public String principal;
    private String projectId;
}
