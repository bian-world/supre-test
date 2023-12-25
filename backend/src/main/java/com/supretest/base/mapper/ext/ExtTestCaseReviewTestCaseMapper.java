package com.supretest.base.mapper.ext;

import com.supretest.base.domain.TestCase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtTestCaseReviewTestCaseMapper {
    List<TestCase> getTestCaseWithNodeInfo(@Param("reviewId") String reviewId);
}
