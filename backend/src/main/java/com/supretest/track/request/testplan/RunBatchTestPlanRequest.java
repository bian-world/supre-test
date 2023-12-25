package com.supretest.track.request.testplan;

import io.metersphere.dto.RunModeConfigDTO;
import com.supretest.performance.request.RunTestPlanRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RunBatchTestPlanRequest  {
    private List<RunTestPlanRequest> requests;
    private RunModeConfigDTO config;
    private String userId;
}
