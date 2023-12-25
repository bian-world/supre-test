package com.supretest.base.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UiElementOperation {
    private String id;

    private String type;

    private String operation;

    private Timestamp createTime;

    private Timestamp updateTime;
}
