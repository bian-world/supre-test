package com.supretest.base.mapper;

import com.supretest.base.domain.UiScenario;
import com.supretest.base.domain.UiScenarioExample;
import com.supretest.base.domain.UiScenarioWithBLOBs;
import com.supretest.base.domain.UiScriptMapping;
import com.supretest.ui.dto.UiScriptMappingDTO;
import io.metersphere.plugin.core.api.UiScriptApi;
import io.metersphere.plugin.core.ui.UiScript;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UiScriptMappingMapper {
    UiScriptMapping selectByCondition(UiScriptMappingDTO uiScriptMappingDTO);
}