package com.supretest.api.dto.automation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.supretest.api.dto.definition.TestPlanApiCaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestPlanFailureApiDTO extends TestPlanApiCaseDTO {
    private String response;
    private String reportId;
}
