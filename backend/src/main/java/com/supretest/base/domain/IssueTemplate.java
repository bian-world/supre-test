package com.supretest.base.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class IssueTemplate implements Serializable {
    private String id;

    private String name;

    private String platform;

    private String description;

    private String title;

    private Boolean system;

    private Boolean global;

    private Long createTime;

    private Long updateTime;

    private String createUser;

    private String projectId;

    private String content;

    private static final long serialVersionUID = 1L;
}