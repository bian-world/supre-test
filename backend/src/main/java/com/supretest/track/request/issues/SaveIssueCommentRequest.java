package com.supretest.track.request.issues;

import com.supretest.base.domain.IssueComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveIssueCommentRequest extends IssueComment {
     private String reviewId;
}
