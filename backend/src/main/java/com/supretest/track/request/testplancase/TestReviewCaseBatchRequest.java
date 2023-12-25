package com.supretest.track.request.testplancase;

import com.supretest.base.domain.TestCaseReviewTestCase;
import com.supretest.track.request.testreview.QueryCaseReviewCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestReviewCaseBatchRequest extends TestCaseReviewTestCase {
    private String reviewId;
    private List<String> ids;
    private QueryCaseReviewCondition condition;
}
