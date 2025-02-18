package com.supretest.track.request.issues;

import com.supretest.track.request.testcase.QueryTestCaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IssuesRelevanceRequest {
    private String id;

    /**
     * 缺陷ID
     */
    private String issuesId;

    private String caseId;

    /**
     * 当选择关联全部用例时把加载条件送到后台，从后台查询
     */
    private QueryTestCaseRequest request;

    /**
     * 具体要关联的用例
     */
    private List<String> testCaseIds = new ArrayList<>();

    private List<String> issueIds;

    private Boolean checked;

    private String description;

    private String caseResourceId;
    private List<String> caseResourceIds;
    private Boolean isPlanEdit = false;
    private String refId;
}
