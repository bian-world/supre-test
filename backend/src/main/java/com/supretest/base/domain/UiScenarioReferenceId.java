package com.supretest.base.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UiScenarioReferenceId implements Serializable {
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