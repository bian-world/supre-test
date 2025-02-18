package com.supretest.base.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class ApiScenarioReferenceId implements Serializable {
    private String id;

    private String apiScenarioId;

    private Long createTime;

    private String createUserId;

    private String referenceId;

    private String referenceType;

    private String dataType;

    private String url;

    private String method;

    private static final long serialVersionUID = 1L;
}