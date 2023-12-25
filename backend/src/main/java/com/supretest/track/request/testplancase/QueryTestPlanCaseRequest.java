package com.supretest.track.request.testplancase;

import com.supretest.base.domain.TestPlanTestCase;
import com.supretest.controller.request.BaseQueryRequest;
import com.supretest.controller.request.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QueryTestPlanCaseRequest extends BaseQueryRequest {

    private List<String> nodePaths;

    private List<String> planIds;

    private List<String> projectIds;

    private String workspaceId;

    private String status;

    private String node;

    private String method;

    private String nodeId;

    private String planId;

    private String executor;

    private String id;

    private Map<String, Object> combine;
}
