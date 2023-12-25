package com.supretest.base.mapper;

import com.supretest.base.domain.TestCaseReviewFollow;
import com.supretest.base.domain.TestCaseReviewFollowExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TestCaseReviewFollowMapper {
    long countByExample(TestCaseReviewFollowExample example);

    int deleteByExample(TestCaseReviewFollowExample example);

    int insert(TestCaseReviewFollow record);

    int insertSelective(TestCaseReviewFollow record);

    List<TestCaseReviewFollow> selectByExample(TestCaseReviewFollowExample example);

    int updateByExampleSelective(@Param("record") TestCaseReviewFollow record, @Param("example") TestCaseReviewFollowExample example);

    int updateByExample(@Param("record") TestCaseReviewFollow record, @Param("example") TestCaseReviewFollowExample example);
}