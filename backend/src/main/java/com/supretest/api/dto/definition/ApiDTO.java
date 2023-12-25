package com.supretest.api.dto.definition;

import com.supretest.base.domain.TestCaseWithBLOBs;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiDTO extends TestCaseWithBLOBs {

    private String maintainerName;
    private String apiName;
    private String performName;
}
