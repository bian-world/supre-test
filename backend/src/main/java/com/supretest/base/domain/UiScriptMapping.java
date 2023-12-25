package com.supretest.base.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UiScriptMapping implements Serializable {

    private String id;

    private String locationType;

    private String operation;

    private String languageType;

    private String script;

    private Long createTime;

    private Long updateTime;

    private static final long serialVersionUID = 1L;

}
