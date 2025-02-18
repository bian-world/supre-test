package com.supretest.track.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.dto.automation.TestPlanFailureApiDTO;
import com.supretest.api.dto.definition.ApiTestCaseDTO;
import com.supretest.api.dto.definition.ApiTestCaseRequest;
import com.supretest.api.dto.definition.BatchRunDefinitionRequest;
import com.supretest.api.dto.definition.TestPlanApiCaseDTO;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.constants.PermissionConstants;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.controller.request.ResetOrderRequest;
import io.metersphere.dto.MsExecResponseDTO;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.track.request.testcase.TestPlanApiCaseBatchRequest;
import com.supretest.track.service.TestPlanApiCaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/test/plan/api/case")
@RestController
public class TestPlanApiCaseController {

    @Resource
    TestPlanApiCaseService testPlanApiCaseService;

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<TestPlanApiCaseDTO>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody ApiTestCaseRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, testPlanApiCaseService.list(request));
    }

    @GetMapping("/list/failure/{planId}")
    public List<TestPlanFailureApiDTO> getFailureList(@PathVariable String planId) {
        return testPlanApiCaseService.getFailureCases(planId);
    }

    @GetMapping("/list/errorReport/{planId}")
    public List<TestPlanFailureApiDTO> getErrorReportList(@PathVariable String planId) {
        return testPlanApiCaseService.getErrorReportCases(planId);
    }
    @GetMapping("/list/unExecute/{planId}")
    public List<TestPlanFailureApiDTO> getUnExecuteCases(@PathVariable String planId) {
        return testPlanApiCaseService.getUnExecuteCases(planId);
    }

    @GetMapping("/list/all/{planId}")
    public List<TestPlanFailureApiDTO> getAllList(@PathVariable String planId) {
        return testPlanApiCaseService.getAllCases(planId);
    }

    @PostMapping("/selectAllTableRows")
    public List<TestPlanApiCaseDTO> selectAllTableRows(@RequestBody TestPlanApiCaseBatchRequest request) {
        return testPlanApiCaseService.selectAllTableRows(request);
    }


    @PostMapping("/relevance/list/{goPage}/{pageSize}")
    public Pager<List<ApiTestCaseDTO>> relevanceList(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody ApiTestCaseRequest request) {
        return testPlanApiCaseService.relevanceList(goPage, pageSize, request);
    }

    @GetMapping("/delete/{id}")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_PLAN_READ_RELEVANCE_OR_CANCEL)
    @MsAuditLog(module = OperLogModule.TRACK_TEST_CASE_REVIEW, type = OperLogConstants.UN_ASSOCIATE_CASE, beforeEvent = "#msClass.getLogDetails(#id)", msClass = TestPlanApiCaseService.class)
    public int deleteTestCase(@PathVariable String id) {
        return testPlanApiCaseService.delete(id);
    }

    @PostMapping("/batch/delete")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_PLAN_READ_RELEVANCE_OR_CANCEL)
    @MsAuditLog(module = OperLogModule.TRACK_TEST_PLAN, type = OperLogConstants.UN_ASSOCIATE_CASE, beforeEvent = "#msClass.getLogDetails(#request.ids)", msClass = TestPlanApiCaseService.class)
    public void deleteApiCaseBath(@RequestBody TestPlanApiCaseBatchRequest request) {
        testPlanApiCaseService.deleteApiCaseBath(request);
    }

    @PostMapping("/batch/update/env")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_PLAN_READ_RELEVANCE_OR_CANCEL)
    @MsAuditLog(module = OperLogModule.TRACK_TEST_PLAN, type = OperLogConstants.BATCH_UPDATE, beforeEvent = "#msClass.batchLogDetails(#request.ids)", content = "#msClass.getLogDetails(#request.ids)", msClass = TestPlanApiCaseService.class)
    public void batchUpdateEnv(@RequestBody TestPlanApiCaseBatchRequest request) {
        testPlanApiCaseService.batchUpdateEnv(request);
    }

    @PostMapping(value = "/run")
    @MsAuditLog(module = OperLogModule.TRACK_TEST_PLAN, type = OperLogConstants.EXECUTE, content = "#msClass.getLogDetails(#request.planIds)", msClass = TestPlanApiCaseService.class)
    public List<MsExecResponseDTO> run(@RequestBody BatchRunDefinitionRequest request) {
        return testPlanApiCaseService.run(request);
    }

    @PostMapping("/edit/order")
    public void orderCase(@RequestBody ResetOrderRequest request) {
        testPlanApiCaseService.updateOrder(request);
    }
}
