package com.supretest.base.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class ZendaoProject implements Serializable {
    private String id;

    private String productId;

    private String zendaoProductId;

    private String zendaoProjectId;

    private String zendaoProjectName;

    private String zendaoProjectStatus;

    private Long createTime;

    private Long updateTime;

    private static final long serialVersionUID = 1L;
}