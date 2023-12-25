package com.supretest.base.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class UiPageModule implements Serializable {
    private String id;

    private String projectId;

    private String name;

    private String parentId;

    private Integer level;

    private Double pos;

    private Long createTime;

    private Long updateTime;

    private String createUser;

    private String zentaoTreeId;

    private String zentaoBranchId;

    private String zentaoBranchName;

    private static final long serialVersionUID = 1L;
}