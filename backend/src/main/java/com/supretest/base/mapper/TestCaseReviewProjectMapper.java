package com.supretest.base.mapper;

import com.supretest.base.domain.TestCaseReviewProject;
import com.supretest.base.domain.TestCaseReviewProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestCaseReviewProjectMapper {
    long countByExample(TestCaseReviewProjectExample example);

    int deleteByExample(TestCaseReviewProjectExample example);

    int insert(TestCaseReviewProject record);

    int insertSelective(TestCaseReviewProject record);

    List<TestCaseReviewProject> selectByExample(TestCaseReviewProjectExample example);

    int updateByExampleSelective(@Param("record") TestCaseReviewProject record, @Param("example") TestCaseReviewProjectExample example);

    int updateByExample(@Param("record") TestCaseReviewProject record, @Param("example") TestCaseReviewProjectExample example);
}