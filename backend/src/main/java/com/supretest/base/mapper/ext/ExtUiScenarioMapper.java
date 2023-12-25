package com.supretest.base.mapper.ext;

import com.supretest.api.dto.automation.ApiScenarioDTO;
import com.supretest.api.dto.automation.ApiScenarioRequest;
import com.supretest.api.dto.datacount.ApiDataCountResult;
import com.supretest.base.domain.ApiScenario;
import com.supretest.base.domain.ApiScenarioExampleWithOperation;
import com.supretest.base.domain.ApiScenarioWithBLOBs;
import com.supretest.controller.request.BaseQueryRequest;
import com.supretest.dto.RelationshipGraphData;
import com.supretest.ui.dto.UiScenarioDTO;
import com.supretest.ui.dto.UiScenarioRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ExtUiScenarioMapper {

    List<UiScenarioDTO> selectIds(@Param("ids") List<String> ids);

    List<String> selectIdsByQuery(@Param("request") BaseQueryRequest request);

    UiScenarioDTO selectById(@Param("id") String id);

    List<UiScenarioDTO> list(@Param("request") UiScenarioRequest request);

}
