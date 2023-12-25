package com.supretest.track.request.testCaseReport;

import com.supretest.base.domain.TestCaseReportTemplate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryTemplateRequest extends TestCaseReportTemplate {
    Boolean queryDefault;
}
