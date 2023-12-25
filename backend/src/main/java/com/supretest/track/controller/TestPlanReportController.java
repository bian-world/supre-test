package com.supretest.track.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.base.domain.TestPlanReport;
import com.supretest.commons.constants.NoticeConstants;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.constants.ReportTriggerMode;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.notice.annotation.SendNotice;
import com.supretest.track.dto.TestPlanReportDTO;
import com.supretest.track.dto.TestPlanScheduleReportInfoDTO;
import com.supretest.track.dto.TestPlanSimpleReportDTO;
import com.supretest.track.request.report.QueryTestPlanReportRequest;
import com.supretest.track.request.report.TestPlanReportSaveRequest;
import com.supretest.track.service.TestPlanReportService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author song.tianyang
 * @Date 2021/1/8 2:38 下午
 * @Description
 */
@RequestMapping("/test/plan/report")
@RestController
public class TestPlanReportController {

    @Resource
    private TestPlanReportService testPlanReportService;

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<TestPlanReportDTO>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody QueryTestPlanReportRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, testPlanReportService.list(request));
    }

    @GetMapping("/getMetric/{planId}")
    public TestPlanReportDTO getMetric(@PathVariable String planId) {
        return testPlanReportService.getMetric(planId);
    }

    @GetMapping("/db/{reportId}")
    public TestPlanSimpleReportDTO getReport(@PathVariable String reportId) {
        return testPlanReportService.getReport(reportId);
    }

    @GetMapping("/sendTask/{planId}")
    public String sendTask(@PathVariable String planId) {
        TestPlanReport report = testPlanReportService.getTestPlanReport(planId);
        testPlanReportService.update(report);
        return "sucess";
    }

    @GetMapping("/status/{planId}")
    public String getStatus(@PathVariable String planId) {
        TestPlanReport report = testPlanReportService.getTestPlanReport(planId);
        String status = report.getStatus();
        return status;
    }

    @PostMapping("/delete")
    @MsAuditLog(module = OperLogModule.TRACK_REPORT, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#testPlanReportIdList)", msClass = TestPlanReportService.class)
    @SendNotice(taskType = NoticeConstants.TaskType.TRACK_REPORT_TASK, target = "#targetClass.getReports(#testPlanReportIdList)", targetClass = TestPlanReportService.class,
            event = NoticeConstants.Event.DELETE, mailTemplate = "track/ReportDelete", subject = "报告通知")
    public void delete(@RequestBody List<String> testPlanReportIdList) {
        testPlanReportService.delete(testPlanReportIdList);
    }

    @PostMapping("/deleteBatchByParams")
    public void deleteBatchByParams(@RequestBody QueryTestPlanReportRequest request) {
        testPlanReportService.delete(request);
    }


    @GetMapping("/apiExecuteFinish/{planId}/{userId}")
    public void apiExecuteFinish(@PathVariable String planId, @PathVariable String userId) {
        String reportId = UUID.randomUUID().toString();
        TestPlanReportSaveRequest saveRequest = new TestPlanReportSaveRequest(reportId, planId, userId, ReportTriggerMode.API.name());
        TestPlanScheduleReportInfoDTO report = testPlanReportService.genTestPlanReport(saveRequest);
        testPlanReportService.countReportByTestPlanReportId(report.getTestPlanReport().getId(), null, ReportTriggerMode.API.name());
    }

    @GetMapping("/saveTestPlanReport/{planId}/{triggerMode}")
    public String saveTestPlanReport(@PathVariable String planId, @PathVariable String triggerMode) {
        String userId = SessionUtils.getUser().getId();
        String reportId = UUID.randomUUID().toString();
        TestPlanReportSaveRequest saveRequest = new TestPlanReportSaveRequest(reportId, planId, userId, triggerMode);
        TestPlanScheduleReportInfoDTO report = testPlanReportService.genTestPlanReport(saveRequest);
        testPlanReportService.genTestPlanReportContent(report);
        testPlanReportService.countReportByTestPlanReportId(report.getTestPlanReport().getId(), null, triggerMode);
        return "success";
    }

    @PostMapping("/reName")
    public void reName(@RequestBody TestPlanReport request) {
        testPlanReportService.reName(request.getId(), request.getName());
    }
}
