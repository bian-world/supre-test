package com.supretest.track.request.testreview;

import com.supretest.base.domain.TestCaseComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCommentRequest extends TestCaseComment {
     private String reviewId;

}
