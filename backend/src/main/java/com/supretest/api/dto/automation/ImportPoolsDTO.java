package com.supretest.api.dto.automation;

import com.supretest.api.dto.scenario.DatabaseConfig;
import com.supretest.api.dto.scenario.environment.EnvironmentConfig;
import com.supretest.base.domain.ApiTestEnvironmentWithBLOBs;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ImportPoolsDTO {
    private String envId;

    private Boolean isCreate;

    private EnvironmentConfig envConfig;

    private ApiTestEnvironmentWithBLOBs testEnvironmentWithBLOBs;

    private Map<String, DatabaseConfig> dataSources;
}
