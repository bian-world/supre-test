package com.supretest.base.mapper.ext;

import com.supretest.api.dto.automation.ApiScenarioModuleDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtApiScenarioModuleMapper {
    List<ApiScenarioModuleDTO> getNodeTreeByProjectId(@Param("projectId") String projectId);

    void updatePos(String id, Double pos);
}
