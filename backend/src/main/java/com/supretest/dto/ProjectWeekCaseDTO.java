package com.supretest.dto;

import com.supretest.base.domain.TestCase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectWeekCaseDTO{
    private String projectId;
    private String projectName;
    private String caseCreateDay;
    private String caseNum;
//    private List<TestCase> testCase
}
