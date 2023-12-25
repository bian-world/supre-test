package com.supretest.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BugCountWithDevChartDTO {

    private String projectId;
    private String subProjectId;
    private String status;
    private List<BugCountBaseDTO> bugCountList;

}
