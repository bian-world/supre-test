package com.supretest.base.mapper.ext;

import com.supretest.track.dto.IssueCommentDTO;
import com.supretest.track.dto.TestCaseCommentDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExtIssueCommentMapper {

    /**
     * 获取用例的评论
     * @param issueId
     * @return
     */
    List<IssueCommentDTO> getComments(@Param("issueId") String issueId);

}
