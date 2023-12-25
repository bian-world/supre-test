package com.supretest.performance.controller.request;

import com.supretest.track.request.testplancase.TestPlanFuncCaseConditions;
import lombok.Data;

import java.util.List;

@Data
public class DeleteReportRequest {
    private List<String> ids;
    private String projectId;
    private TestPlanFuncCaseConditions condition;
}
