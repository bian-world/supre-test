package com.supretest.dto;

import com.supretest.base.domain.ProjectWeekPlanProgress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class ProjectWeekPlanProgressDTO {

    private String id;
    private String projectId;
    private String subProjectId;
    private String planId;
    private String planName;
    private Double passRate;
    private int passNum;
    private int planCaseTotal;
    private String createDay;

}
