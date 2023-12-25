package com.supretest.track.dto;

import com.supretest.base.domain.TestCaseReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestCaseReportDTO extends TestCaseReport {
    private String createName;
}
