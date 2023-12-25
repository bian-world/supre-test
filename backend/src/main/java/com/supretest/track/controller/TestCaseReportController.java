package com.supretest.track.controller;

import com.supretest.base.domain.TestCaseReport;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.track.request.testCaseReport.CreateReportRequest;
import com.supretest.track.service.TestCaseReportService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RequestMapping("/case/report")
@RestController
public class TestCaseReportController {

    @Resource
    TestCaseReportService testCaseReportService;

    @PostMapping("/list")
    public List<TestCaseReport> list(@RequestBody TestCaseReport request) {
        return testCaseReportService.listTestCaseReport(request);
    }

    @GetMapping("/get/{id}")
    public TestCaseReport get(@PathVariable String id) {
        return testCaseReportService.getTestCaseReport(id);
    }

    @PostMapping("/add")
    @MsAuditLog(module = OperLogModule.TRACK_TEST_PLAN, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#request.id)", msClass = TestCaseReportService.class)
    public String addByTemplateId(@RequestBody CreateReportRequest request) {
        request.setId(UUID.randomUUID().toString());
        return testCaseReportService.addTestCaseReportByTemplateId(request);
    }

    @PostMapping("/edit")
    @MsAuditLog(module = OperLogModule.TRACK_TEST_CASE_REVIEW, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#TestCaseReport.id)", content = "#msClass.getLogDetails(#TestCaseReport.id)", msClass = TestCaseReportService.class)
    public void edit(@RequestBody TestCaseReport TestCaseReport) {
        testCaseReportService.editTestCaseReport(TestCaseReport);
    }

    @PostMapping("/delete/{id}")
    @MsAuditLog(module = OperLogModule.TRACK_TEST_PLAN, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#id)", msClass = TestCaseReportService.class)
    public int delete(@PathVariable String id) {
        return testCaseReportService.deleteTestCaseReport(id);
    }

}
