package com.supretest.api.dto.automation.parse;

import com.supretest.api.dto.definition.parse.ms.NodeTree;
import com.supretest.base.domain.ApiScenarioWithBLOBs;
import lombok.Data;

import java.util.List;

@Data
public class ScenarioImport {
    private String projectId;
    private List<ApiScenarioWithBLOBs> data;
    private List<NodeTree> nodeTree;
}
