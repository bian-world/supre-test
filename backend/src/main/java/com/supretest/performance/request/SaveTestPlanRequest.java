package com.supretest.performance.request;

import com.supretest.base.domain.ApiLoadTest;
import com.supretest.base.domain.FileMetadata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class SaveTestPlanRequest extends TestPlanRequest {
    private List<FileMetadata> updatedFileList;
    private Map<String, Integer> fileSorts;
    private List<String> conversionFileIdList;
    private List<ApiLoadTest> apiList;
}
