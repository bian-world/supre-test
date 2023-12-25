package com.supretest.api.dto.definition.parse;

import com.supretest.api.dto.definition.parse.ms.NodeTree;
import com.supretest.api.dto.mockconfig.MockConfigImportDTO;
import com.supretest.base.domain.ApiDefinitionWithBLOBs;
import com.supretest.base.domain.ApiTestCaseWithBLOBs;
import com.supretest.base.domain.EsbApiParamsWithBLOBs;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ApiDefinitionImport {
    private String projectName;
    private String protocol;
    private List<ApiDefinitionWithBLOBs> data;

    // 新版本带用例导出
    private List<ApiTestCaseWithBLOBs> cases = new ArrayList<>();

    //ESB文件导入的附属数据类
    private Map<String,EsbApiParamsWithBLOBs> esbApiParamsMap;

    //Mock数据相关
    private List<MockConfigImportDTO> mocks;

    private List<NodeTree> nodeTree;
}
