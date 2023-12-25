package com.supretest.base.mapper;

import com.supretest.base.domain.ApiTestCaseFollow;
import com.supretest.base.domain.ApiTestCaseFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiTestCaseFollowMapper {
    long countByExample(ApiTestCaseFollowExample example);

    int deleteByExample(ApiTestCaseFollowExample example);

    int insert(ApiTestCaseFollow record);

    int insertSelective(ApiTestCaseFollow record);

    List<ApiTestCaseFollow> selectByExample(ApiTestCaseFollowExample example);

    int updateByExampleSelective(@Param("record") ApiTestCaseFollow record, @Param("example") ApiTestCaseFollowExample example);

    int updateByExample(@Param("record") ApiTestCaseFollow record, @Param("example") ApiTestCaseFollowExample example);
}