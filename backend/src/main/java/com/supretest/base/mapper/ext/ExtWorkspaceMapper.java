package com.supretest.base.mapper.ext;

import com.supretest.base.domain.Workspace;
import com.supretest.controller.request.WorkspaceRequest;
import com.supretest.dto.WorkspaceDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtWorkspaceMapper {

    List<WorkspaceDTO> getWorkspaces(@Param("request") WorkspaceRequest request);

    List<Workspace> getWorkspaceByUserId(@Param("userId")String userId);
}
