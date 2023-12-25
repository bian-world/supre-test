package com.supretest.api.dto.definition;

import com.supretest.base.domain.ApiDefinitionWithBLOBs;
import com.supretest.controller.request.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiBatchRequest extends ApiDefinitionWithBLOBs {
    private List<String> ids;
    private String name;
    private List<OrderRequest> orders;
    private String projectId;
    private String moduleId;
    private String protocol;

    private ApiDefinitionRequest condition;
}
