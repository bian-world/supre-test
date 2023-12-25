package com.supretest.base.mapper.ext;

import com.supretest.base.domain.EnvironmentGroup;
import com.supretest.controller.request.EnvironmentGroupRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtEnvironmentGroupMapper {

    List<EnvironmentGroup> getList(@Param("request") EnvironmentGroupRequest request);

    List<EnvironmentGroup> getRelateProject(@Param("wsId") String workspaceId, @Param("pId") String projectId);
}
