package com.supretest.api.dto;

import com.supretest.api.dto.definition.ApiTestCaseRequest;
import com.supretest.base.domain.ApiTestCaseWithBLOBs;
import com.supretest.controller.request.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiCaseEditRequest extends ApiTestCaseWithBLOBs {
    private List<String> ids;
    private List<OrderRequest> orders;
    private String projectId;
    private String environmentId;
    private ApiTestCaseRequest condition;
}
