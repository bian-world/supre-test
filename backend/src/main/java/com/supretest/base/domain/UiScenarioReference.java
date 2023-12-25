package com.supretest.base.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class UiScenarioReference implements Serializable {
    private String id;

    private String uiScenarioId;

    private Timestamp createTime;

    private String createUserId;

    private String referenceId;

    private String referenceType;

    private String dataType;

    private static final long serialVersionUID = 1L;
}