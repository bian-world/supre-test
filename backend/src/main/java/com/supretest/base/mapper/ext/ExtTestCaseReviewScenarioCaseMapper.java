package com.supretest.base.mapper.ext;

import com.supretest.api.dto.automation.ApiScenarioDTO;
import com.supretest.api.dto.automation.TestPlanScenarioRequest;
import com.supretest.base.domain.TestCaseReviewScenario;
import com.supretest.base.domain.TestPlanApiScenario;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtTestCaseReviewScenarioCaseMapper {
    void insertIfNotExists(@Param("request") TestCaseReviewScenario request);

    List<ApiScenarioDTO> list(@Param("request") TestPlanScenarioRequest request);

    List<String> getExecResultByReviewId(String reviewId);

    List<String> getIdsByReviewId(String reviewId);

    List<String> getNotRelevanceCaseIds(String planId, List<String> relevanceProjectIds);
}
