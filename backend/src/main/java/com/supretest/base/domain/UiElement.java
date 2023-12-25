package com.supretest.base.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class UiElement implements Serializable {
    private String id;

    private Integer num;

    private String pageId;

    private String moduleId;

    private String projectId;

    private String name;

    private String locationType;

    private String location;

    private String createUser;

    private String versionId;

    private String refId;

    private Long order;

    private Boolean latest;

    private String description;

    private Timestamp createTime;

    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;
}