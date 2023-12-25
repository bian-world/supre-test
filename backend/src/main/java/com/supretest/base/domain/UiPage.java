package com.supretest.base.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class UiPage implements Serializable {
    private String id;

    private Integer num;

    private String parentId;

    private String moduleId;

    private String modulePath;

    private String projectId;

    private String name;

    private String pageLevel;

    private String createUser;

    private String description;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String subProjectId;

}
