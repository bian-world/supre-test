package com.supretest.api.dto;

import com.supretest.api.dto.definition.ApiTestCaseRequest;
import com.supretest.controller.request.OrderRequest;
import io.metersphere.dto.RunModeConfigDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiCaseRunRequest {
    private String reportId;
    private String triggerMode;
    private String id;
    private List<String> ids;
    private List<OrderRequest> orders;
    private String projectId;
    private String environmentId;
    private RunModeConfigDTO config;
    private ApiTestCaseRequest condition;
}
