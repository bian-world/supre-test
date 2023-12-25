package com.supretest.controller.request;

import com.supretest.base.domain.CustomFunctionWithBLOBs;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CustomFunctionRequest extends CustomFunctionWithBLOBs {
    private Map<String, List<String>> filters;
    private List<OrderRequest> orders;

}
