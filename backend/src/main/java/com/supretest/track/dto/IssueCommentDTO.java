package com.supretest.track.dto;

import com.supretest.base.domain.IssueComment;
import lombok.Data;

@Data
public class IssueCommentDTO extends IssueComment {
    private String authorName;
}
