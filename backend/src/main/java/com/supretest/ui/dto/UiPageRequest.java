package com.supretest.ui.dto;

import com.supretest.controller.request.BaseQueryRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UiPageRequest extends BaseQueryRequest {

    private String id;
    private String excludeId;
    private String moduleId;
    private String name;
    private String createUser;
    private String planId;
    private boolean recent = false;
    private boolean isSelectThisWeedData;
    private long createTime = 0;
    private String executeStatus;
    private boolean notInTestPlan;
    private String reviewId;
    private String versionId;
    private String refId;

    //操作人
    private String operator;
    //操作时间
    private Long operationTime;
    /**
     * 是否需要查询环境字段
     */
    private boolean selectEnvironment = false;
}
