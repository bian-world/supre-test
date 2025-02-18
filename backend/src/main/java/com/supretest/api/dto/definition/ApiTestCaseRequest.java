package com.supretest.api.dto.definition;

import com.supretest.controller.request.BaseQueryRequest;
import com.supretest.controller.request.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ApiTestCaseRequest extends BaseQueryRequest {
    private String id;
    private List<String> ids;
    private String planId;
    private String projectId;
    private String priority;
    private String name;
    private String environmentId;
    private String workspaceId;
    private String apiDefinitionId;
    private String status;
    private String protocol;
    private String moduleId;
    private List<String> moduleIds;
    private List<OrderRequest> orders;
    private Map<String, List<String>> filters;
    private Map<String, Object> combine;
    private boolean isSelectThisWeedData;
    private long createTime = 0;
    private long updateTime = 0;
    private String reviewId;
    private String deleteUserId;
    private long deleteTime;
    /**
     * 检查待更新的（近三天有更新的或者状态为error的）
     */
    private boolean toUpdate;
    /**
     * 是否需要查询环境字段
     */
    private boolean selectEnvironment = false;

    /**
     * 查询排除一些接口
     */
    private List<String> notInIds;
}
