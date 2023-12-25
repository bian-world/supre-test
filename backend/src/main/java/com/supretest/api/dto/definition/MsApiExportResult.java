package com.supretest.api.dto.definition;

import com.supretest.api.dto.mockconfig.MockConfigImportDTO;
import com.supretest.base.domain.ApiDefinitionWithBLOBs;
import com.supretest.base.domain.ApiTestCaseWithBLOBs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MsApiExportResult extends ApiExportResult {
    private String projectName;
    private String protocol;
    private String projectId;
    private String subProjectId;
    private String subProjectName;
    private String version;
    private List<ApiDefinitionWithBLOBs> data;
    private List<ApiTestCaseWithBLOBs> cases;
    private List<MockConfigImportDTO> mocks;
}
