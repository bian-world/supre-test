package com.supretest.service;

import com.supretest.base.domain.ProjectVersion;
import com.supretest.controller.request.ProjectVersionRequest;
import com.supretest.dto.ProjectVersionDTO;

import java.util.List;

public interface ProjectVersionService {
    List<ProjectVersionDTO> getVersionList(ProjectVersionRequest request);

    ProjectVersion addProjectVersion(ProjectVersion projectVersion);

    ProjectVersion getProjectVersion(String id);

    ProjectVersion editProjectVersion(ProjectVersion projectVersion);

    void deleteProjectVersion(String id);

    List<ProjectVersionDTO> getProjectVersions(String projectId);

    void changeStatus(String id, String status);

    List<ProjectVersion> getProjectVersionByIds(List<String> versionIds);
}
