package com.supretest.api.dto.automation;

import com.supretest.base.domain.ApiScenarioWithBLOBs;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiScenrioExportResult {
    private String projectId;
    private String version;
    private List<ApiScenarioWithBLOBs> data;
}
