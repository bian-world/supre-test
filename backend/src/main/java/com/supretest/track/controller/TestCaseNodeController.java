package com.supretest.track.controller;

import com.supretest.base.domain.TestCaseNode;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.CheckPermissionService;
import com.supretest.track.dto.TestCaseNodeDTO;
import com.supretest.track.request.testcase.DragNodeRequest;
import com.supretest.track.request.testcase.QueryNodeRequest;
import com.supretest.track.request.testplancase.QueryTestPlanCaseRequest;
import com.supretest.track.service.TestCaseNodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/case/node")
@RestController
public class TestCaseNodeController {

    @Resource
    TestCaseNodeService testCaseNodeService;
    @Resource
    private CheckPermissionService checkPermissionService;

    @GetMapping("/list/{projectId}")
    public List<TestCaseNodeDTO> getNodeByProjectId(@PathVariable String projectId) {
        checkPermissionService.checkProjectOwner(projectId);
        return testCaseNodeService.getNodeTreeByProjectId(projectId, null);
    }

    @GetMapping("/list/{projectId}/{subProjectId}")
    public List<TestCaseNodeDTO> getNodeByProjectId(@PathVariable String projectId, @PathVariable String subProjectId) {
        return testCaseNodeService.getNodeTreeByProjectId(projectId, subProjectId);
    }

    @GetMapping("/trashCount/{projectId}")
    public long trashCount(@PathVariable String projectId) {
        checkPermissionService.checkProjectOwner(projectId);
        return testCaseNodeService.trashCount(projectId);
    }

    @GetMapping("/publicCount/{workSpaceId}")
    public long publicCount(@PathVariable String workSpaceId) {
        return testCaseNodeService.publicCount(workSpaceId);
    }

    /*模块列表列表*/
    @PostMapping("/list/all/plan")
    public List<TestCaseNodeDTO> getAllNodeByPlanId(@RequestBody QueryNodeRequest request) {
        return testCaseNodeService.getAllNodeByPlanId(request);
    }

    /*模块列表列表*/
    @PostMapping("/list/project")
    public List<TestCaseNodeDTO> getAllNodeByProjectId(@RequestBody QueryNodeRequest request) {
        return testCaseNodeService.getAllNodeByProjectId(request);
    }

    @PostMapping("/list/all/review")
    public List<TestCaseNodeDTO> getAllNodeByReviewId(@RequestBody QueryNodeRequest request) {
        return testCaseNodeService.getAllNodeByReviewId(request);
    }

    @GetMapping("/list/plan/{planId}")
    public List<TestCaseNodeDTO> getNodeByPlanId(@PathVariable String planId) {
        checkPermissionService.checkTestPlanOwner(planId);
        return testCaseNodeService.getNodeByPlanId(planId);
    }

    @GetMapping("/list/plan/{planId}/{runResult}")
    public List<TestCaseNodeDTO> getNodeByPlanIdAndRunResult(@PathVariable String planId,@PathVariable String runResult) {
        checkPermissionService.checkTestPlanOwner(planId);
        QueryTestPlanCaseRequest request = new QueryTestPlanCaseRequest();
        request.setPlanId(planId);
        request.setStatus(runResult);
        return testCaseNodeService.getNodeByQueryRequest(request);
    }

    @GetMapping("/list/review/{reviewId}")
    public List<TestCaseNodeDTO> getNodeByReviewId(@PathVariable String reviewId) {
        checkPermissionService.checkTestReviewOwner(reviewId);
        return testCaseNodeService.getNodeByReviewId(reviewId);
    }

    @PostMapping("/add")
    @MsAuditLog(module = OperLogModule.TRACK_TEST_CASE, type = OperLogConstants.CREATE, title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = TestCaseNodeService.class)
    public String addNode(@RequestBody TestCaseNode node) {
        return testCaseNodeService.addNode(node);
    }

    @PostMapping("/edit")
    @MsAuditLog(module = OperLogModule.TRACK_TEST_CASE, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#node)", title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = TestCaseNodeService.class)
    public int editNode(@RequestBody DragNodeRequest node) {
        return testCaseNodeService.editNode(node);
    }

    @PostMapping("/delete")
    @MsAuditLog(module = OperLogModule.TRACK_TEST_CASE, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#nodeIds)", msClass = TestCaseNodeService.class)
    public int deleteNode(@RequestBody List<String> nodeIds) {
        //nodeIds 包含删除节点ID及其所有子节点ID
        return testCaseNodeService.deleteNode(nodeIds);
    }

    @PostMapping("/drag")
    @MsAuditLog(module = OperLogModule.TRACK_TEST_CASE, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#node)", title = "#node.name", content = "#msClass.getLogDetails(#node)", msClass = TestCaseNodeService.class)
    public void dragNode(@RequestBody DragNodeRequest node) {
        testCaseNodeService.dragNode(node);
    }

    @PostMapping("/pos")
    public void treeSort(@RequestBody List<String> ids) {
        testCaseNodeService.sort(ids);
    }
}
