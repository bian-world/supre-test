package com.supretest.track.service;

import com.alibaba.fastjson.JSON;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.TestCaseCommentMapper;
import com.supretest.base.mapper.TestCaseMapper;
import com.supretest.base.mapper.UserMapper;
import com.supretest.base.mapper.ext.ExtTestCaseCommentMapper;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.i18n.Translator;
import com.supretest.log.utils.ReflexObjectUtil;
import com.supretest.log.vo.DetailColumn;
import com.supretest.log.vo.OperatingLogDetails;
import com.supretest.log.vo.track.TestCaseReviewReference;
import com.supretest.track.dto.TestCaseCommentDTO;
import com.supretest.track.request.testreview.SaveCommentRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestCaseCommentService {
    @Resource
    private TestCaseCommentMapper testCaseCommentMapper;
    @Resource
    private TestCaseMapper testCaseMapper;
    @Resource
    private ExtTestCaseCommentMapper extTestCaseCommentMapper;
    @Resource
    private UserMapper userMapper;

    public TestCaseComment saveComment(SaveCommentRequest request) {
        TestCaseComment testCaseComment = new TestCaseComment();
        testCaseComment.setId(request.getId());
        testCaseComment.setAuthor(SessionUtils.getUser().getId());
        testCaseComment.setCaseId(request.getCaseId());
        testCaseComment.setCreateTime(System.currentTimeMillis());
        testCaseComment.setUpdateTime(System.currentTimeMillis());
        testCaseComment.setDescription(request.getDescription());
        testCaseComment.setStatus(request.getStatus());
        testCaseCommentMapper.insert(testCaseComment);
        return testCaseComment;
    }

    public List<TestCaseCommentDTO> getCaseComments(String caseId) {
        return extTestCaseCommentMapper.getCaseComments(caseId);
    }

    public void deleteCaseComment(String caseId) {
        TestCaseCommentExample testCaseCommentExample = new TestCaseCommentExample();
        testCaseCommentExample.createCriteria().andCaseIdEqualTo(caseId);
        testCaseCommentMapper.deleteByExample(testCaseCommentExample);
    }

    private String getReviewContext(TestCaseComment testCaseComment, TestCaseWithBLOBs testCaseWithBLOBs) {
        User user = userMapper.selectByPrimaryKey(testCaseComment.getAuthor());
        Long startTime = testCaseComment.getCreateTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = null;
        String sTime = String.valueOf(startTime);
        if (!sTime.equals("null")) {
            start = sdf.format(new Date(Long.parseLong(sTime)));
        }
        String context = "";
        context = "测试评审任务通知：" + user.getName() + "在" + start + "为" + "'" + testCaseWithBLOBs.getName() + "'" + "添加评论:" + testCaseComment.getDescription();
        return context;
    }

    public void delete(String commentId) {
        checkCommentOwner(commentId);
        testCaseCommentMapper.deleteByPrimaryKey(commentId);
    }

    public TestCaseComment edit(SaveCommentRequest request) {
        checkCommentOwner(request.getId());
        testCaseCommentMapper.updateByPrimaryKeySelective(request);
        return testCaseCommentMapper.selectByPrimaryKey(request.getId());
    }

    private void checkCommentOwner(String commentId) {
        TestCaseComment comment = testCaseCommentMapper.selectByPrimaryKey(commentId);
        if (!StringUtils.equals(comment.getAuthor(), SessionUtils.getUser().getId())) {
            MSException.throwException(Translator.get("check_owner_comment"));
        }
    }

    public String getLogDetails(String id) {
        TestCaseComment caseComment = testCaseCommentMapper.selectByPrimaryKey(id);
        if (caseComment != null) {
            TestCase review = testCaseMapper.selectByPrimaryKey(caseComment.getCaseId());
            List<DetailColumn> columns = ReflexObjectUtil.getColumns(caseComment, TestCaseReviewReference.commentReviewColumns);
            OperatingLogDetails details = new OperatingLogDetails(JSON.toJSONString(caseComment.getId()), review.getProjectId(), caseComment.getDescription(), caseComment.getAuthor(), columns);
            return JSON.toJSONString(details);
        }
        return null;
    }
}
