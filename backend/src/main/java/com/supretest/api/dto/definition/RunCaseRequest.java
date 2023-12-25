package com.supretest.api.dto.definition;

import com.supretest.base.domain.ApiDefinitionExecResult;
import com.supretest.base.domain.ApiTestCaseWithBLOBs;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RunCaseRequest {
    private String reportId;

    private String id;

    private String caseId;

    private String runMode;

    private String environmentId;

    private String testPlanId;

    private ApiTestCaseWithBLOBs bloBs;

    private ApiDefinitionExecResult report;
}
