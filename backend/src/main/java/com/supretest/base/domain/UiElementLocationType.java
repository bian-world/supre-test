package com.supretest.base.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UiElementLocationType {

    private String id;

    private String locationType;

    private Timestamp createTime;

    private Timestamp updateTime;
}
