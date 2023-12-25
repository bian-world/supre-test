package com.supretest.track.service;

import com.alibaba.fastjson.JSON;
import com.supretest.base.domain.IssueComment;
import com.supretest.base.domain.Issues;
import com.supretest.base.mapper.IssueCommentMapper;
import com.supretest.base.mapper.IssuesMapper;
import com.supretest.base.mapper.ext.ExtIssueCommentMapper;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.i18n.Translator;
import com.supretest.log.utils.ReflexObjectUtil;
import com.supretest.log.vo.DetailColumn;
import com.supretest.log.vo.OperatingLogDetails;
import com.supretest.log.vo.track.TestCaseReviewReference;
import com.supretest.track.dto.IssueCommentDTO;
import com.supretest.track.request.issues.IssuesRelevanceRequest;
import com.supretest.track.request.issues.SaveIssueCommentRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class IssueCommentService {
    @Resource
    private IssuesMapper issuesMapper;
    @Resource
    private ExtIssueCommentMapper extIssueCommentMapper;
    @Resource
    private IssueCommentMapper issueCommentMapper;

    public IssueComment saveComment(IssuesRelevanceRequest request) {
        IssueComment issueComment = new IssueComment();
        issueComment.setId(request.getId());
        issueComment.setAuthor(SessionUtils.getUser().getId());
        issueComment.setIssueId(request.getIssuesId());
        issueComment.setCreateTime(System.currentTimeMillis());
        issueComment.setUpdateTime(System.currentTimeMillis());
        issueComment.setDescription(request.getDescription());
        issueCommentMapper.insert(issueComment);
        return issueComment;
    }

    public List<IssueCommentDTO> getComments(String caseId) {
        return extIssueCommentMapper.getComments(caseId);
    }

    public void delete(String commentId) {
        checkCommentOwner(commentId);
        issueCommentMapper.deleteByPrimaryKey(commentId);
    }

    public IssueComment edit(SaveIssueCommentRequest request) {
        checkCommentOwner(request.getId());
        issueCommentMapper.updateByPrimaryKeySelective(request);
        return issueCommentMapper.selectByPrimaryKey(request.getId());
    }

    private void checkCommentOwner(String commentId) {
        IssueComment comment = issueCommentMapper.selectByPrimaryKey(commentId);
        if (!StringUtils.equals(comment.getAuthor(), SessionUtils.getUser().getId())) {
            MSException.throwException(Translator.get("check_owner_comment"));
        }
    }

    public String getLogDetails(String id) {
        IssueComment issueComment = issueCommentMapper.selectByPrimaryKey(id);
        if (issueComment != null) {
            Issues review = issuesMapper.selectByPrimaryKey(issueComment.getIssueId());
            List<DetailColumn> columns = ReflexObjectUtil.getColumns(issueComment, TestCaseReviewReference.commentReviewColumns);
            OperatingLogDetails details = new OperatingLogDetails(JSON.toJSONString(issueComment.getId()), review.getProjectId(), issueComment.getDescription(), issueComment.getAuthor(), columns);
            return JSON.toJSONString(details);
        }
        return null;
    }
}
