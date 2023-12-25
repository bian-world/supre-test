package com.supretest.base.mapper.ext;

import com.supretest.base.domain.ApiScenarioReferenceId;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtApiScenarioReferenceIdMapper {
    List<ApiScenarioReferenceId> selectUrlByProjectId(String projectId, String subProjectId);
}
