package com.supretest.base.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiDefinition implements Serializable {
    private String id;

    private String projectId;

    private String name;

    private String method;

    private String modulePath;

    private String environmentId;

    private String schedule;

    private String status;

    private String moduleId;

    private String userId;

    private Long createTime;

    private Long updateTime;

    private String protocol;

    private String path;

    private Integer num;

    private String tags;

    private String originalState;

    private String createUser;

    private String caseTotal;

    private String caseStatus;

    private String casePassingRate;

    private Long deleteTime;

    private String deleteUserId;

    private Long order;

    private String refId;

    private String versionId;

    private Boolean latest;

    private String subProjectId;

    private static final long serialVersionUID = 1L;
}