package com.supretest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.service.ApiTestEnvironmentService;
import com.supretest.base.domain.FileMetadata;
import com.supretest.base.domain.Project;
import com.supretest.base.domain.ProjectWeekPlanProgress;
import com.supretest.base.domain.ZendaoProject;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.controller.request.AddProjectRequest;
import com.supretest.controller.request.ProjectRequest;
import com.supretest.dto.ProjectDTO;
import com.supretest.dto.ProjectWeekCaseDTO;
import com.supretest.dto.ProjectWeekPlanProgressDTO;
import com.supretest.dto.WorkspaceMemberDTO;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.CheckPermissionService;
import com.supretest.service.ProjectService;
import com.supretest.track.issue.ZentaoPlatform;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    @Resource
    private ProjectService projectService;
    @Resource
    private CheckPermissionService checkPermissionService;
    @Resource
    private ApiTestEnvironmentService apiTestEnvironmentService;

    @GetMapping("/listAll")
    public List<ProjectDTO> listAll() {
        String currentWorkspaceId = SessionUtils.getCurrentWorkspaceId();
        ProjectRequest request = new ProjectRequest();
        request.setWorkspaceId(currentWorkspaceId);
        return projectService.getProjectList(request);
    }

    @GetMapping("/projectWeekProgressSchedule/{enable}")
    public void scheduleTaskStart(@PathVariable Boolean enable) {
        projectService.projectWeekProgressSchedule(enable);
    }
    /*jenkins项目列表*/
    @GetMapping("/listAll/{workspaceId}")
    public List<ProjectDTO> jlistAll(@PathVariable String workspaceId) {
        ProjectRequest request = new ProjectRequest();
        request.setWorkspaceId(workspaceId);
        return projectService.getProjectList(request);
    }

    @GetMapping("/recent/{count}")
    public List<Project> recentProjects(@PathVariable int count) {
        String currentWorkspaceId = SessionUtils.getCurrentWorkspaceId();
        ProjectRequest request = new ProjectRequest();
        request.setWorkspaceId(currentWorkspaceId);
        // 最近 `count` 个项目
        PageHelper.startPage(1, count);
        return projectService.getRecentProjectList(request);
    }

    @GetMapping("/get/{id}")
    public Project getProject(@PathVariable String id) {
        return projectService.getProjectById(id);
    }
    @GetMapping("/member/size/{id}")
    public long getProjectMemberSize(@PathVariable String id) {
        return projectService.getProjectMemberSize(id);
    }

    @PostMapping("/add")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MANAGER, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#project.id)", msClass = ProjectService.class)
    public Project addProject(@RequestBody AddProjectRequest project, HttpServletRequest request) {
        Project returnModel = projectService.addProject(project);
        //创建项目的时候默认增加Mock环境
        apiTestEnvironmentService.getMockEnvironmentByProjectId(returnModel.getId());
        return returnModel;
    }

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<ProjectDTO>> getProjectList(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody ProjectRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, projectService.getProjectList(request));
    }

    /**
     * 在工作空间下与用户有关的项目
     *
     * @param request userId
     * @return List<ProjectDTO>
     */
    @PostMapping("/list/related")
    public List<ProjectDTO> getUserProject(@RequestBody ProjectRequest request) {
        return projectService.getUserProject(request);
    }

//    @GetMapping("/list/projectWeekPlanProgressesList")
//    public int projectWeekPlanProgressesList() {
//        return projectService.projectWeekPlanProgressesList();
//    }

    @GetMapping("/list/getAllProject")
    public List<ProjectDTO> getAllProject() {
        return projectService.getAllProject();
    }

    @GetMapping("/weekCaseList/related/{projectId}/{subProjectId}")
    public List<ProjectWeekCaseDTO> weekCaseList(@PathVariable String projectId, @PathVariable String subProjectId) {
        return projectService.getProjectWeekCase(projectId, subProjectId);
    }

    @GetMapping("/weekCaseList/related/{projectId}")
    public List<ProjectWeekCaseDTO> weekCaseList(@PathVariable String projectId) {
        return this.weekCaseList(projectId, null);
    }

    @GetMapping("/weekPlanProgress/related/{projectId}/{subProjectId}")
    public Map<String, List<?>> weekPlanProgressList(@PathVariable String projectId, @PathVariable String subProjectId) {
        return projectService.getProjectPlanProgress(projectId, subProjectId);
    }

    @GetMapping("/weekPlanProgress/related/{projectId}")
    public Map<String, List<?>> weekPlanProgressList(@PathVariable String projectId) {
        return this.weekPlanProgressList(projectId, null);
    }

    @GetMapping("/delete/{projectId}")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MANAGER, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#projectId)", msClass = ProjectService.class)
    public void deleteProject(@PathVariable(value = "projectId") String projectId) {
        projectService.deleteProject(projectId);
    }

    @PostMapping("/update")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MANAGER, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#Project.id)", content = "#msClass.getLogDetails(#Project.id)", msClass = ProjectService.class)
    public void updateProject(@RequestBody AddProjectRequest Project) {
        projectService.updateProject(Project);
    }

    @PostMapping(value = "upload/files/{projectId}", consumes = {"multipart/form-data"})
    @MsAuditLog(module = OperLogModule.PROJECT_FILE_MANAGEMENT, type = OperLogConstants.IMPORT, content = "#msClass.getLogDetails(#projectId)", msClass = ProjectService.class)
    public List<FileMetadata> uploadFiles(@PathVariable String projectId, @RequestPart(value = "file", required = false) List<MultipartFile> files) {
        return projectService.uploadFiles(projectId, files);
    }

    @PostMapping(value = "/update/file/{fileId}", consumes = {"multipart/form-data"})
    @MsAuditLog(module = OperLogModule.PROJECT_FILE_MANAGEMENT, type = OperLogConstants.IMPORT, content = "#msClass.getLogDetails(#fileId)", msClass = ProjectService.class)
    public FileMetadata updateFile(@PathVariable String fileId, @RequestPart(value = "file", required = false) MultipartFile file) {
        return projectService.updateFile(fileId, file);
    }

    @GetMapping(value = "delete/file/{fileId}")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MANAGER, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#fileId)", msClass = ProjectService.class)
    public void deleteFile(@PathVariable String fileId) {
        projectService.deleteFile(fileId);
    }

    @PostMapping("/member/update")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MEMBER, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#memberDTO)", content = "#msClass.getLogDetails(#memberDTO)", msClass = ProjectService.class)
    public void updateMember(@RequestBody WorkspaceMemberDTO memberDTO) {
        projectService.updateMember(memberDTO);
    }

    @GetMapping("/getOwnerProjectIds")
    public Collection<String> getOwnerProjectIds() {
        return checkPermissionService.getUserRelatedProjectIds();
    }

    @GetMapping("/getOwnerProjects")
    public List<ProjectDTO>  getOwnerProjects() {
        return checkPermissionService.getOwnerProjects();
    }

    @GetMapping("/genTcpMockPort/{id}")
    public String genTcpMockPort(@PathVariable String id){
        return projectService.genTcpMockPort(id);
    }

    @GetMapping("version/enable/{projectId}")
    public boolean isVersionEnable(@PathVariable String projectId) {
        return projectService.isVersionEnable(projectId);
    }

    @PostMapping("/check/third/project")
    public void checkThirdProjectExist(@RequestBody Project project) {
        projectService.checkThirdProjectExist(project);
    }

    @GetMapping("/syncZenTaoProducts")
    public void syncProducts() {
        new ZentaoPlatform().syncProducts();
    }

    @GetMapping("/syncZenTaoProduct/{productId}")
    public void syncProduct(@PathVariable String productId) {
        new ZentaoPlatform().syncProduct(productId);
    }

    @GetMapping("/syncZenTaoBranchAndTree/{productId}")
    public void syncBranchAndTree(@PathVariable String productId) {
        new ZentaoPlatform().syncBranchAndModules(productId);
    }

    @GetMapping("/syncProjects/{productId}")
    public void syncProjects(@PathVariable String productId) {
        new ZentaoPlatform().syncProjects(productId);
    }

    @GetMapping("/getSubProjects/{productId}")
    public List<ZendaoProject> getSubProjects(@PathVariable String productId) {
        return projectService.getZendaoProjects(productId);
    }

    @GetMapping("/getBugList/{productId}")
    public Map getBugList(@PathVariable String productId) {
        return  new ZentaoPlatform().getBugCount(productId, null);
    }

    @GetMapping("/getBugList/{productId}/{subProductId}")
    public Map getBugList(@PathVariable String productId, @PathVariable String subProductId) {
        return  new ZentaoPlatform().getBugCount(productId, subProductId);
    }

    @GetMapping("/getBugDuration/{productId}")
    public Map getBugDuration(@PathVariable String productId) {
        return  new ZentaoPlatform().getBugDuration(productId, null);
    }

    @GetMapping("/getBugDuration/{productId}/{subProductId}")
    public Map getBugDuration(@PathVariable String productId, @PathVariable String subProductId) {
        return  new ZentaoPlatform().getBugDuration(productId, subProductId);
    }
}
