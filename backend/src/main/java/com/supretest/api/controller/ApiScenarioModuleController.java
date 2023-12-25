package com.supretest.api.controller;

import com.supretest.api.dto.automation.ApiScenarioModuleDTO;
import com.supretest.api.dto.automation.DragApiScenarioModuleRequest;
import com.supretest.api.service.ApiScenarioModuleService;
import com.supretest.base.domain.ApiScenarioModule;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.CheckPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/api/automation/module")
@RestController
public class ApiScenarioModuleController {

    @Resource
    ApiScenarioModuleService apiScenarioModuleService;
    @Resource
    private CheckPermissionService checkPermissionService;

    @GetMapping("/list/{projectId}")
    public List<ApiScenarioModuleDTO> getNodeByProjectId(@PathVariable String projectId) {
        return this.getNodeByProjectId(projectId, null);
    }

    @GetMapping("/list/{projectId}/{subProjectId}")
    public List<ApiScenarioModuleDTO> getNodeByProjectId(@PathVariable String projectId, @PathVariable String subProjectId) {
        checkPermissionService.checkProjectOwner(projectId);
        return apiScenarioModuleService.getNodeTreeByProjectId(projectId, subProjectId);
    }

    @PostMapping("/add")
    @MsAuditLog(module = "api_automation", type = OperLogConstants.CREATE, title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = ApiScenarioModuleService.class)
    public String addNode(@RequestBody ApiScenarioModule node) {
        return apiScenarioModuleService.addNode(node);
    }

    @PostMapping("/edit")
    @MsAuditLog(module = "api_automation", type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#node)", title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = ApiScenarioModuleService.class)
    public int editNode(@RequestBody DragApiScenarioModuleRequest node) {
        return apiScenarioModuleService.editNode(node);
    }

    @GetMapping("/list/plan/{planId}")
    public List<ApiScenarioModuleDTO> getNodeByPlanId(@PathVariable String planId) {
        checkPermissionService.checkTestPlanOwner(planId);
        return apiScenarioModuleService.getNodeByPlanId(planId);
    }

    @PostMapping("/delete")
    @MsAuditLog(module = OperLogModule.API_AUTOMATION, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#nodeIds)", msClass = ApiScenarioModuleService.class)
    public int deleteNode(@RequestBody List<String> nodeIds) {
        //nodeIds 包含删除节点ID及其所有子节点ID
        return apiScenarioModuleService.deleteNode(nodeIds);
    }

    @PostMapping("/drag")
    @MsAuditLog(module = OperLogModule.API_AUTOMATION, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#node)", title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = ApiScenarioModuleService.class)
    public void dragNode(@RequestBody DragApiScenarioModuleRequest node) {
        apiScenarioModuleService.dragNode(node);
    }

    @PostMapping("/pos")
    public void treeSort(@RequestBody List<String> ids) {
        apiScenarioModuleService.sort(ids);
    }

}
