package com.supretest.base.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class TestPlanPublishedModule implements Serializable {
    private String id;

    private String planId;

    private String nodeId;

    private String createUser;

    private Long createTime;

    private Long updateTime;

    private static final long serialVersionUID = 1L;
}