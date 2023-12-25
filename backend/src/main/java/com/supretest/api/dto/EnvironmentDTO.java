package com.supretest.api.dto;

import com.supretest.api.dto.scenario.DatabaseConfig;
import lombok.Data;

@Data
public class EnvironmentDTO {
    private String environmentId;
    private DatabaseConfig databaseConfig;
}
