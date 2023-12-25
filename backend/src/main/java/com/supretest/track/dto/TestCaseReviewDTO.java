package com.supretest.track.dto;

import com.supretest.base.domain.TestCaseReview;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestCaseReviewDTO extends TestCaseReview {

    private String projectName;
    private String subProjectName;
    private String reviewerName;
    private String creatorName;
}
