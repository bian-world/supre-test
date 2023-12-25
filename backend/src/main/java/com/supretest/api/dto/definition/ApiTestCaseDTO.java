package com.supretest.api.dto.definition;

import com.supretest.base.domain.ApiTestCase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiTestCaseDTO extends ApiTestCase {
    private String moduleId;
    private String path;
    private String protocol;
    private String updateUser;
    private String createUser;
    private String deleteUser;
    private String apiName;
    private String passRate;
    private String projectName;
    private String subProjectName;
    private String environment;
    private String execResult;
    private String versionName;
}
