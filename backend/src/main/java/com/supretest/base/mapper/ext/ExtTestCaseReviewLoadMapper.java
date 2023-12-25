package com.supretest.base.mapper.ext;

import com.supretest.dto.TestReviewLoadCaseDTO;
import com.supretest.track.request.testplan.LoadCaseRequest;
import com.supretest.track.request.testreview.TestReviewRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtTestCaseReviewLoadMapper {
    List<String> selectIdsNotInPlan(@Param("projectId") String projectId, @Param("reviewId") String reviewId);
    List<TestReviewLoadCaseDTO> selectTestReviewLoadCaseList(@Param("request") TestReviewRequest request);
    void updateCaseStatus(@Param("reportId") String reportId, @Param("status") String status);
    List<String> getStatusByreviewId(@Param("reviewId") String reviewId);
}
