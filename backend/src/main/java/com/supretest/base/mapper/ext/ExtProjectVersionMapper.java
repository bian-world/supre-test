package com.supretest.base.mapper.ext;

import com.supretest.controller.request.ProjectVersionRequest;
import com.supretest.dto.ProjectVersionDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtProjectVersionMapper {
    List<ProjectVersionDTO> selectProjectVersionList(@Param("request") ProjectVersionRequest request);

    String getDefaultVersion(@Param("projectId") String projectId);

    void updateLatestToFalse(@Param("projectId") String projectId);

    boolean isVersionEnable(@Param("projectId") String projectId);

    void changeVersionEnable(@Param("projectId") String projectId, @Param("status") boolean status);

    boolean checkForDelete(String id);
}
