package com.supretest.dto;

import com.supretest.base.domain.ApiTestEnvironmentWithBLOBs;
import com.supretest.base.domain.EnvironmentGroupProject;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class EnvironmentGroupProjectDTO extends EnvironmentGroupProject {
    private List<ApiTestEnvironmentWithBLOBs> environments;
}
