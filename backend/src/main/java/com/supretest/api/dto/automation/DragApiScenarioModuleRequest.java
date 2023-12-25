package com.supretest.api.dto.automation;

import com.supretest.base.domain.ApiScenarioModule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DragApiScenarioModuleRequest extends ApiScenarioModule {

    List<String> nodeIds;
    ApiScenarioModuleDTO nodeTree;
}
