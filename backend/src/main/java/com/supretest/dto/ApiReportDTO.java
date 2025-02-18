package com.supretest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiReportDTO {

    private String id;
    private String testId;
    private String name;
    private String description;
    private Long createTime;
    private Long updateTime;
    private String status;
    private String content;
    private String testName;
    private String projectId;
    private String projectName;
}
