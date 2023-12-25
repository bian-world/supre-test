package com.supretest.api.dto.automation;

import com.supretest.base.domain.ApiScenario;
import com.supretest.track.dto.TestPlanDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReferenceDTO {
    List<ApiScenario> scenarioList;

    List<TestPlanDTO> testPlanList;
}
