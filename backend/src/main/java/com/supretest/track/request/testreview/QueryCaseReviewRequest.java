package com.supretest.track.request.testreview;

import com.supretest.base.domain.TestCaseReviewTestCase;
import com.supretest.controller.request.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QueryCaseReviewRequest extends TestCaseReviewTestCase {
    private List<String> nodeIds;

    private List<String> nodePaths;

    private List<OrderRequest> orders;

    private Map<String, List<String>> filters;

    private List<String> reviewIds;

    private List<String> projectIds;

    private String workspaceId;

    private String name;

    private String status;

    private String node;

    private String nodeId;

    private String method;

    private Map<String, Object> combine;

    private String projectId;

    private String subProjectId;

    private String projectName;

    private String reviewerId;

    private String versionId;

    private String refId;
}
