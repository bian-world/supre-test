package com.supretest.base.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class ZendaoBranchTree implements Serializable {
    private String id;

    private String projectId;

    private String zendaoProductId;

    private String zendaoBranchId;

    private String zendaoBranchName;

    private String zendaoBranchStatus;

    private String zendaoModuleId;

    private String zendaoModuleName;

    private String zendaoParentModuleId;

    private String zendaoModulePath;

    private Long createTime;

    private Long updateTime;

    private static final long serialVersionUID = 1L;
}