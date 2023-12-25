package com.supretest.api.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.dto.APIReportBatchRequest;
import com.supretest.api.dto.DeleteAPIReportRequest;
import com.supretest.api.dto.QueryAPIReportRequest;
import com.supretest.api.dto.automation.APIScenarioReportResult;
import com.supretest.api.dto.automation.ExecuteType;
import com.supretest.api.service.ApiScenarioReportService;
import com.supretest.base.domain.ApiScenarioReport;
import com.supretest.commons.constants.NoticeConstants;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import io.metersphere.dto.RequestResult;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.notice.annotation.SendNotice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/scenario/report")
public class APIScenarioReportController {

    @Resource
    private ApiScenarioReportService apiReportService;

    @GetMapping("/get/{reportId}")
    public APIScenarioReportResult get(@PathVariable String reportId) {
        return apiReportService.get(reportId,false);
    }

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<APIScenarioReportResult>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody QueryAPIReportRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        request.setLimit("LIMIT " + (goPage - 1) * pageSize + "," + pageSize * 50);
        return PageUtils.setPageInfo(page, apiReportService.list(request));
    }

    @PostMapping("/update")
    public String update(@RequestBody APIScenarioReportResult node) {
        node.setExecuteType(ExecuteType.Saved.name());
        return apiReportService.update(node);
    }

    @GetMapping("/selectReportContent/{stepId}")
    public RequestResult selectReportContent(@PathVariable String stepId) {
        return apiReportService.selectReportContent(stepId);
    }

    @PostMapping("/delete")
    @MsAuditLog(module = OperLogModule.API_AUTOMATION_REPORT, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#request.id)", msClass = ApiScenarioReportService.class)
    @SendNotice(taskType = NoticeConstants.TaskType.API_REPORT_TASK, event = NoticeConstants.Event.DELETE, target = "#targetClass.get(#request.id)", targetClass = ApiScenarioReportService.class,
            mailTemplate = "api/ReportDelete", subject = "接口报告通知")
    public void delete(@RequestBody DeleteAPIReportRequest request) {
        apiReportService.delete(request);
    }

    @PostMapping("/batch/delete")
    @MsAuditLog(module = OperLogModule.API_AUTOMATION_REPORT, type = OperLogConstants.BATCH_DEL, beforeEvent = "#msClass.getLogDetails(#reportRequest.ids)", msClass = ApiScenarioReportService.class)
    @SendNotice(taskType = NoticeConstants.TaskType.API_REPORT_TASK, event = NoticeConstants.Event.DELETE, target = "#targetClass.getByIds(#request.ids)", targetClass = ApiScenarioReportService.class,
            mailTemplate = "api/ReportDelete", subject = "接口报告通知")
    public void deleteAPIReportBatch(@RequestBody APIReportBatchRequest request) {
        apiReportService.deleteAPIReportBatch(request);
    }

    @PostMapping("/reName")
    public void reName(@RequestBody ApiScenarioReport reportRequest) {
        apiReportService.reName(reportRequest);
    }
}
