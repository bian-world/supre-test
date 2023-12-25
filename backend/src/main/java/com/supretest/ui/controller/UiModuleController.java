package com.supretest.ui.controller;

import com.supretest.api.dto.definition.ApiModuleDTO;
import com.supretest.api.dto.definition.DragModuleRequest;
import com.supretest.api.service.ApiModuleService;
import com.supretest.base.domain.ApiModule;
import com.supretest.base.domain.UiPageModule;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.ApiDefinitionDefaultApiTypeUtil;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.CheckPermissionService;
import com.supretest.ui.controller.request.DragUiModuleRequest;
import com.supretest.ui.dto.UiPageModuleDTO;
import com.supretest.ui.service.UiModuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/ui")
@RestController
public class UiModuleController {

    @Resource
    UiModuleService uiModuleService;
    @Resource
    private CheckPermissionService checkPermissionService;

    @GetMapping("/page/module/list/{projectId}")
    public List<UiPageModuleDTO> getPageNodeByProjectId(@PathVariable String projectId) {

        return uiModuleService.getNodeTreeByProjectId(projectId,null, null, null);
    }

    @GetMapping("/page/module/list/{projectId}/{subProjectId}")
    public List<UiPageModuleDTO> getPageNodeByProjectIdAndSubProjectId(@PathVariable String projectId, @PathVariable String subProjectId) {
//        String userId = SessionUtils.getUserId();
//        ApiDefinitionDefaultApiTypeUtil.addUserSelectApiType(userId, protocol);
        return uiModuleService.getNodeTreeByProjectId(projectId, subProjectId, null, null);
    }

    @GetMapping("/scenario/module/list/{projectId}")
    public List<UiPageModuleDTO> getScenarioNodeByProjectId(@PathVariable String projectId) {

        return uiModuleService.getNodeTreeByProjectId(projectId,null, "UIScenario", null);
    }

    @GetMapping("/scenario/module/list/{projectId}/{subProjectId}")
    public List<UiPageModuleDTO> getScenarioNodeByProjectIdAndSubProjectId(@PathVariable String projectId, @PathVariable String subProjectId) {
//        String userId = SessionUtils.getUserId();
//        ApiDefinitionDefaultApiTypeUtil.addUserSelectApiType(userId, protocol);
        return uiModuleService.getNodeTreeByProjectId(projectId, subProjectId, "UIScenario", null);
    }

//    @GetMapping("/list/{projectId}/{protocol}/{versionId}")
//    public List<ApiModuleDTO> getNodeByProjectId(@PathVariable String projectId, @PathVariable String protocol,
//                                                 @PathVariable String versionId) {
//        String userId = SessionUtils.getUserId();
//        ApiDefinitionDefaultApiTypeUtil.addUserSelectApiType(userId, protocol);
//        return apiModuleService.getNodeTreeByProjectId(projectId,null, protocol, versionId);
//    }


    @GetMapping("/module/getModuleByName/{projectId}")
    public UiPageModule getModuleByName(@PathVariable String projectId) {
//        checkPermissionService.checkProjectOwner(projectId);
        return uiModuleService.getModuleByName(projectId);
    }


    @PostMapping("/module/add")
    @MsAuditLog(module = OperLogModule.UI_PAGE, type = OperLogConstants.CREATE, title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = UiModuleService.class)
    public String addNode(@RequestBody UiPageModule node) {
        return uiModuleService.addNode(node);
    }

    @PostMapping("/module/edit")
    @MsAuditLog(module = OperLogModule.UI_PAGE, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#node)", title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = UiModuleService.class)
    public int editNode(@RequestBody DragUiModuleRequest node) {
        return uiModuleService.editNode(node);
    }

    @PostMapping("/module/delete")
    @MsAuditLog(module = OperLogModule.UI_PAGE, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#nodeIds)", msClass = UiModuleService.class)
    public int deleteNode(@RequestBody List<String> nodeIds) {
        //nodeIds 包含删除节点ID及其所有子节点ID
//        return uiModuleService.deleteNode(nodeIds);
        return 0;
    }

    @PostMapping("/module/drag")
    @MsAuditLog(module = OperLogModule.UI_PAGE, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#node)", title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = UiModuleService.class)
    public void dragNode(@RequestBody DragUiModuleRequest node) {
        uiModuleService.dragNode(node);
    }

    @PostMapping("/module/pos")
    public void treeSort(@RequestBody List<String> ids) {
        uiModuleService.sort(ids);
    }

}
