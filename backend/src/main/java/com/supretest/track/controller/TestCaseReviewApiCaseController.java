package com.supretest.track.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.dto.definition.ApiTestCaseDTO;
import com.supretest.api.dto.definition.ApiTestCaseRequest;
import com.supretest.api.dto.definition.TestPlanApiCaseDTO;
import com.supretest.commons.constants.PermissionConstants;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.track.request.testcase.TestPlanApiCaseBatchRequest;
import com.supretest.track.request.testreview.TestReviewApiCaseBatchRequest;
import com.supretest.track.service.TestCaseReviewApiCaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test/case/review/api/case")
public class TestCaseReviewApiCaseController {
    @Resource
    private TestCaseReviewApiCaseService testCaseReviewApiCaseService;

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<TestPlanApiCaseDTO>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody ApiTestCaseRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, testCaseReviewApiCaseService.list(request));
    }

    @PostMapping("/relevance/list/{goPage}/{pageSize}")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_REVIEW_READ_RELEVANCE_OR_CANCEL)
    public Pager<List<ApiTestCaseDTO>> relevanceList(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody ApiTestCaseRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, testCaseReviewApiCaseService.relevanceList(request));
    }

    @GetMapping("/delete/{id}")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_REVIEW_READ_RELEVANCE_OR_CANCEL)
    public int deleteTestCase(@PathVariable String id) {
        return testCaseReviewApiCaseService.delete(id);
    }

    @PostMapping("/batch/delete")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_REVIEW_READ_RELEVANCE_OR_CANCEL)
    public void deleteApiCaseBath(@RequestBody TestReviewApiCaseBatchRequest request) {
        testCaseReviewApiCaseService.deleteApiCaseBath(request);
    }

    @PostMapping("/batch/update/env")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_REVIEW_READ_RELEVANCE_OR_CANCEL)
    public void batchUpdateEnv(@RequestBody TestPlanApiCaseBatchRequest request) {
        testCaseReviewApiCaseService.batchUpdateEnv(request);
    }
}
