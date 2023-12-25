package com.supretest.base.mapper;

import com.supretest.base.domain.TestCaseFollow;
import com.supretest.base.domain.TestCaseFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestCaseFollowMapper {
    long countByExample(TestCaseFollowExample example);

    int deleteByExample(TestCaseFollowExample example);

    int insert(TestCaseFollow record);

    int insertSelective(TestCaseFollow record);

    List<TestCaseFollow> selectByExample(TestCaseFollowExample example);

    int updateByExampleSelective(@Param("record") TestCaseFollow record, @Param("example") TestCaseFollowExample example);

    int updateByExample(@Param("record") TestCaseFollow record, @Param("example") TestCaseFollowExample example);
}