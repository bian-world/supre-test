package com.supretest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.base.domain.Workspace;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.controller.request.WorkspaceRequest;
import com.supretest.dto.WorkspaceDTO;
import com.supretest.dto.WorkspaceMemberDTO;
import com.supretest.dto.WorkspaceResource;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.UserService;
import com.supretest.service.WorkspaceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("workspace")
@RestController
public class WorkspaceController {
    @Resource
    private WorkspaceService workspaceService;
    @Resource
    private UserService userService;

    @PostMapping("add")
    @MsAuditLog(module = OperLogModule.SYSTEM_WORKSPACE, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#workspace.id)", msClass = WorkspaceService.class)
    public Workspace addWorkspace(@RequestBody Workspace workspace) {
        return workspaceService.saveWorkspace(workspace);
    }

    @GetMapping("/list")
    public List<Workspace> getWorkspaceList() {
        return workspaceService.getWorkspaceList(new WorkspaceRequest());
    }

    @PostMapping("special/add")
    @MsAuditLog(module = OperLogModule.SYSTEM_WORKSPACE, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#workspace.id)", msClass = WorkspaceService.class)
    public Workspace addWorkspaceByAdmin(@RequestBody Workspace workspace) {
        return workspaceService.addWorkspaceByAdmin(workspace);
    }

    @PostMapping("update")
    @MsAuditLog(module = OperLogModule.SYSTEM_WORKSPACE, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#workspace.id)", content = "#msClass.getLogDetails(#workspace.id)", msClass = WorkspaceService.class)
    public Workspace updateWorkspace(@RequestBody Workspace workspace) {
//        workspaceService.checkWorkspaceOwnerByOrgAdmin(workspace.getId());
        return workspaceService.saveWorkspace(workspace);
    }

    @PostMapping("special/update")
    @MsAuditLog(module = OperLogModule.SYSTEM_WORKSPACE, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#workspace.id)", content = "#msClass.getLogDetails(#workspace.id)", msClass = WorkspaceService.class)
    public void updateWorkspaceByAdmin(@RequestBody Workspace workspace) {
        workspaceService.updateWorkspaceByAdmin(workspace);
    }

    @GetMapping("special/delete/{workspaceId}")
    @MsAuditLog(module = OperLogModule.SYSTEM_WORKSPACE, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#workspaceId)", msClass = WorkspaceService.class)
    public void deleteWorkspaceByAdmin(@PathVariable String workspaceId) {
        userService.refreshSessionUser("workspace", workspaceId);
        workspaceService.deleteWorkspace(workspaceId);
    }

    @GetMapping("delete/{workspaceId}")
    @MsAuditLog(module = OperLogModule.SYSTEM_WORKSPACE, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#workspaceId)", msClass = WorkspaceService.class)
    public void deleteWorkspace(@PathVariable String workspaceId) {
//        workspaceService.checkWorkspaceOwnerByOrgAdmin(workspaceId);
        userService.refreshSessionUser("workspace", workspaceId);
        workspaceService.deleteWorkspace(workspaceId);
    }

    @PostMapping("list/{goPage}/{pageSize}")
    public Pager<List<Workspace>> getWorkspaceList(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody WorkspaceRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, workspaceService.getWorkspaceList(request));
    }

    @PostMapping("list/all/{goPage}/{pageSize}")
    public Pager<List<WorkspaceDTO>> getAllWorkspaceList(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody WorkspaceRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, workspaceService.getAllWorkspaceList(request));
    }

    @GetMapping("/list/userworkspace/{userId}")
    public List<Workspace> getWorkspaceListByUserId(@PathVariable String userId) {
        return workspaceService.getWorkspaceListByUserId(userId);
    }

    @PostMapping("/member/update")
    @MsAuditLog(module = OperLogModule.WORKSPACE_MEMBER, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#memberDTO)", content = "#msClass.getLogDetails(#memberDTO)", msClass = WorkspaceService.class)
    public void updateOrgMember(@RequestBody WorkspaceMemberDTO memberDTO) {
        workspaceService.updateWorkspaceMember(memberDTO);
    }

    @GetMapping("/list/resource/{groupId}/{type}")
    public WorkspaceResource listResource(@PathVariable String groupId, @PathVariable String type) {
        return workspaceService.listResource(groupId, type);
    }
}
