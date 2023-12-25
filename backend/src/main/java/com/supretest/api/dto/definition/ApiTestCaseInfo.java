package com.supretest.api.dto.definition;

import com.supretest.base.domain.ApiTestCaseWithBLOBs;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiTestCaseInfo extends ApiTestCaseWithBLOBs {
    private String apiMethod;
    private String versionName;
    private Boolean versionEnable;
}
