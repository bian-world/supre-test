package com.supretest.base.mapper.ext;

import com.supretest.track.dto.TestCaseReviewDTO;
import com.supretest.track.dto.TestReviewDTOWithMetric;
import com.supretest.track.request.testreview.QueryCaseReviewRequest;
import com.supretest.track.request.testreview.QueryTestReviewRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ExtTestCaseReviewMapper {

    List<TestCaseReviewDTO> list(@Param("request") QueryCaseReviewRequest params);

    List<TestCaseReviewDTO> listByWorkspaceId(@Param("workspaceId") String workspaceId, @Param("userId") String userId, @Param("projectId") String projectId);

    List<TestReviewDTOWithMetric> listRelate(@Param("request") QueryTestReviewRequest request);

    /**
     * 检查某项目是否有某测试评审
     *
     * @param reviewId
     * @param projectIds
     * @return Review ID
     */
    int checkIsHave(@Param("reviewId") String reviewId, @Param("projectIds") Set<String> projectIds);
}
