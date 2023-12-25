package com.supretest.performance.dto;

import com.supretest.base.domain.LoadTestWithBLOBs;
import com.supretest.controller.request.OrderRequest;
import lombok.Data;

import java.util.List;

@Data
public class LoadTestBatchRequest extends LoadTestWithBLOBs {
    private List<String> ids;
    private String name;
    private List<OrderRequest> orders;
    private String projectId;
    private String moduleId;
    private String protocol;

    private LoadTestRequest condition;
}
