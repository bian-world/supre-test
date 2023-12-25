package com.supretest.base.mapper.ext;

import com.supretest.base.domain.Project;
import com.supretest.base.domain.ProjectWeekPlanProgress;
import com.supretest.controller.request.ProjectRequest;
import com.supretest.dto.ProjectDTO;
import com.supretest.dto.ProjectWeekCaseDTO;
import com.supretest.dto.ProjectWeekPlanProgressDTO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExtProjectMapper {

    List<ProjectDTO> getProjectWithWorkspace(@Param("proRequest") ProjectRequest request);

    List<String> getProjectIdByWorkspaceId(String workspaceId);

    int removeIssuePlatform(@Param("platform") String platform, @Param("workspaceId") String workspaceId);

    List<ProjectDTO> getUserProject(@Param("proRequest") ProjectRequest request);

    List<ProjectDTO> getAllProject();

    List<ProjectWeekCaseDTO> getProjectWeekCase(@Param("projectId") String projectId, @Param("subProjectId") String subProjectId);

    List<ProjectWeekPlanProgressDTO> getProjectPlanProgress(@Param("projectId") String projectId, @Param("subProjectId") String subProjectId);

    int insertProjectWeekPlanProgress(@Param("list") List<ProjectWeekPlanProgressDTO> projectWeekPlanProgressDTOList );
    String getSystemIdByProjectId(String projectId);

    List<String> getProjectIds();

    String getMaxSystemId();

    @MapKey("id")
    Map<String, Project> queryNameByIds(@Param("ids") List<String> ids);

    @MapKey("zentaoId")
    Map<String, Project> queryAllZenTaoProjects();

    Project selectProjectByResourceId(@Param("resourceId") String resourceId);

    long getProjectMemberSize(@Param("projectId") String projectId);

    List<Project> getProjectByUserId(@Param("userId") String userId);

    int getProjectPlanBugSize(@Param("projectId") String projectId);

    void setDefaultMessageTask(@Param("projectId") String projectId);

    List<ProjectDTO> queryListByIds(@Param("ids") List<String> ids);

    void updateUseDefaultCaseTemplateProject(@Param("originId") String originId,@Param("templateId") String templateId,@Param("projectId") String projectId);
}
