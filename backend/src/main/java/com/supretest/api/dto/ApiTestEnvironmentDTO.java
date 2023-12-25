package com.supretest.api.dto;

import com.supretest.base.domain.ApiTestEnvironmentWithBLOBs;
import lombok.Data;

import java.util.List;

@Data
public class ApiTestEnvironmentDTO extends ApiTestEnvironmentWithBLOBs {
    private List<String> uploadIds;
}
