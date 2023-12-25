package com.supretest.api.controller;

import com.supretest.api.dto.definition.ApiModuleDTO;
import com.supretest.api.dto.definition.DragModuleRequest;
import com.supretest.api.service.ApiModuleService;
import com.supretest.base.domain.ApiModule;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.ApiDefinitionDefaultApiTypeUtil;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.CheckPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/api/module")
@RestController
public class ApiModuleController {

    @Resource
    ApiModuleService apiModuleService;
    @Resource
    private CheckPermissionService checkPermissionService;

    @GetMapping("/list/{projectId}/{protocol}")
    public List<ApiModuleDTO> getNodeByProjectId(@PathVariable String projectId, @PathVariable String protocol) {
        String userId = SessionUtils.getUserId();
        ApiDefinitionDefaultApiTypeUtil.addUserSelectApiType(userId, protocol);
        return apiModuleService.getNodeTreeByProjectId(projectId,null, protocol, null);
    }

    @GetMapping("/list/{projectId}/{subProjectId}/{protocol}")
    public List<ApiModuleDTO> getNodeByProjectIdAndSubProjectId(@PathVariable String projectId, @PathVariable String subProjectId, @PathVariable String protocol) {
        String userId = SessionUtils.getUserId();
        ApiDefinitionDefaultApiTypeUtil.addUserSelectApiType(userId, protocol);
        return apiModuleService.getNodeTreeByProjectId(projectId, subProjectId, protocol, null);
    }

//    @GetMapping("/list/{projectId}/{protocol}/{versionId}")
//    public List<ApiModuleDTO> getNodeByProjectId(@PathVariable String projectId, @PathVariable String protocol,
//                                                 @PathVariable String versionId) {
//        String userId = SessionUtils.getUserId();
//        ApiDefinitionDefaultApiTypeUtil.addUserSelectApiType(userId, protocol);
//        return apiModuleService.getNodeTreeByProjectId(projectId,null, protocol, versionId);
//    }

    @GetMapping("/trashCount/{projectId}/{protocol}")
    public long trashCount(@PathVariable String projectId, @PathVariable String protocol) {
        String userId = SessionUtils.getUserId();
        ApiDefinitionDefaultApiTypeUtil.addUserSelectApiType(userId, protocol);
        return apiModuleService.countTrashApiData(projectId, protocol);
    }

    @GetMapping("/getModuleByName/{projectId}/{protocol}")
    public ApiModule getModuleByName(@PathVariable String projectId, @PathVariable String protocol) {
//        checkPermissionService.checkProjectOwner(projectId);
        return apiModuleService.getModuleByName(projectId, protocol);
    }

    @GetMapping("/getUserDefaultApiType")
    public String getUserDefaultApiType() {
        String returnStr = ApiDefinitionDefaultApiTypeUtil.HTTP;
        try {
            String userId = SessionUtils.getUserId();
            returnStr = ApiDefinitionDefaultApiTypeUtil.getUserSelectedApiType(userId);
        } catch (Exception e) {

        }
        return returnStr;
    }

    @GetMapping("/list/plan/{planId}/{protocol}")
    public List<ApiModuleDTO> getNodeByPlanId(@PathVariable String planId, @PathVariable String protocol) {
        checkPermissionService.checkTestPlanOwner(planId);
        return apiModuleService.getNodeByPlanId(planId, protocol);
    }

    @PostMapping("/add")
    @MsAuditLog(module = OperLogModule.API_DEFINITION, type = OperLogConstants.CREATE, title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = ApiModuleService.class)
    public String addNode(@RequestBody ApiModule node) {
        return apiModuleService.addNode(node);
    }

    @PostMapping("/edit")
    @MsAuditLog(module = OperLogModule.API_DEFINITION, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#node)", title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = ApiModuleService.class)
    public int editNode(@RequestBody DragModuleRequest node) {
        return apiModuleService.editNode(node);
    }

    @PostMapping("/delete")
    @MsAuditLog(module = OperLogModule.API_DEFINITION, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#nodeIds)", msClass = ApiModuleService.class)
    public int deleteNode(@RequestBody List<String> nodeIds) {
        //nodeIds 包含删除节点ID及其所有子节点ID
        return apiModuleService.deleteNode(nodeIds);
    }

    @PostMapping("/drag")
    @MsAuditLog(module = OperLogModule.API_DEFINITION, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#node)", title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = ApiModuleService.class)
    public void dragNode(@RequestBody DragModuleRequest node) {
        apiModuleService.dragNode(node);
    }

    @PostMapping("/pos")
    public void treeSort(@RequestBody List<String> ids) {
        apiModuleService.sort(ids);
    }

}
