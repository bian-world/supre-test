package com.supretest.track.request.testreview;

import com.supretest.base.domain.TestCaseReview;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveTestCaseReviewRequest extends TestCaseReview {
    private List<String> projectIds;
    private List<String> userIds;
    private List<String> followIds;
}
