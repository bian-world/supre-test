package com.supretest.performance.request;

import com.supretest.base.domain.FileMetadata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class EditTestPlanRequest extends TestPlanRequest {
    private List<FileMetadata> updatedFileList;
    private Map<String, Integer> fileSorts;
}
