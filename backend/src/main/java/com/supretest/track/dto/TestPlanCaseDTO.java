package com.supretest.track.dto;

import com.supretest.base.domain.IssuesDao;
import com.supretest.base.domain.TestCaseWithBLOBs;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class TestPlanCaseDTO extends TestCaseWithBLOBs {
    private String executor;
    private String executorName;
    private String results;
    private String planId;
    private String planName;
    private String caseId;
    private String issues;
    private String reportId;
    private String model;
    private String projectName;
    private String actualResult;
    private String maintainerName;
    private Boolean isCustomNum;
    private int issuesCount;
    private String versionName;

    private List<TestCaseTestDTO> list;
    private List<IssuesDao> issueList;
}
