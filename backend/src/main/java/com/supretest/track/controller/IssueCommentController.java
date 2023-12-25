package com.supretest.track.controller;

import com.supretest.base.domain.IssueComment;
import com.supretest.commons.constants.NoticeConstants;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.constants.PermissionConstants;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.notice.annotation.SendNotice;
import com.supretest.track.dto.IssueCommentDTO;
import com.supretest.track.request.issues.IssuesRelevanceRequest;
import com.supretest.track.request.issues.SaveIssueCommentRequest;
import com.supretest.track.service.IssueCommentService;
import com.supretest.track.service.IssuesService;
import com.supretest.track.service.TestCaseCommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RequestMapping("/issues/comment")
@RestController
public class IssueCommentController {

    @Resource
    private IssueCommentService issueCommentService;

    @PostMapping("/save")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_REVIEW_READ_COMMENT)
    @SendNotice(taskType = NoticeConstants.TaskType.DEFECT_TASK, target = "#targetClass.get(#request.issuesId)", targetClass = IssuesService.class,
            event = NoticeConstants.Event.COMMENT, mailTemplate = "track/IssuesCommentUpdate", subject = "缺陷")
    public IssueComment saveComment(@RequestBody IssuesRelevanceRequest request) {
        request.setId(UUID.randomUUID().toString());
        return issueCommentService.saveComment(request);
    }

    @GetMapping("/list/{issueId}")
    public List<IssueCommentDTO> getComments(@PathVariable String issueId) {
        return issueCommentService.getComments(issueId);
    }

    @GetMapping("/delete/{commentId}")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_REVIEW_READ_COMMENT)
    @MsAuditLog(module = OperLogModule.TRACK_BUG, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#commentId)", msClass = TestCaseCommentService.class)
    public void deleteComment(@PathVariable String commentId) {
        issueCommentService.delete(commentId);
    }

    @PostMapping("/edit")
    @RequiresPermissions(PermissionConstants.PROJECT_TRACK_REVIEW_READ_COMMENT)
    @MsAuditLog(module = OperLogModule.TRACK_BUG, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#request.id)", content = "#msClass.getLogDetails(#request.id)", msClass = TestCaseCommentService.class)
    public IssueComment editComment(@RequestBody SaveIssueCommentRequest request) {
       return issueCommentService.edit(request);
    }
}
