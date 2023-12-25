package com.supretest.track.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.base.domain.TestCaseReviewLoad;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.dto.LoadTestDTO;
import com.supretest.dto.TestReviewLoadCaseDTO;
import com.supretest.performance.request.RunTestPlanRequest;
import com.supretest.track.request.testplan.LoadCaseReportRequest;
import com.supretest.track.request.testreview.TestReviewRequest;
import com.supretest.track.service.TestCaseReviewLoadService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test/review/load/case")
public class TestReviewLoadCaseController {
    @Resource
    private TestCaseReviewLoadService testCaseReviewLoadService;

    @PostMapping("/relevance/list/{goPage}/{pageSize}")
    public Pager<List<LoadTestDTO>> relevanceList(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody TestReviewRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, testCaseReviewLoadService.relevanceList(request));
    }

    @PostMapping("/relevance")
    public void relevanceCase(@RequestBody TestReviewRequest request) {
        testCaseReviewLoadService.relevanceCase(request);
    }

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<TestReviewLoadCaseDTO>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody TestReviewRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, testCaseReviewLoadService.list(request));
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        testCaseReviewLoadService.delete(id);
    }

    @PostMapping("/run")
    public String run(@RequestBody RunTestPlanRequest request) {
        return testCaseReviewLoadService.run(request);
    }

    @PostMapping("/report/exist")
    public Boolean isExistReport(@RequestBody LoadCaseReportRequest request) {
        return testCaseReviewLoadService.isExistReport(request);
    }

    @PostMapping("/batch/delete")
    public void batchDelete(@RequestBody List<String> ids) {
        testCaseReviewLoadService.batchDelete(ids);
    }

    @PostMapping("/update")
    public void update(@RequestBody TestCaseReviewLoad testCaseReviewLoad) {
        testCaseReviewLoadService.update(testCaseReviewLoad);
    }
}
