package com.supretest.dto;

import com.supretest.base.domain.TestCaseReviewLoad;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestReviewLoadCaseDTO extends TestCaseReviewLoad {
    private String userName;
    private String caseName;
    private String projectName;
    private String caseStatus;
    private String num;
}
