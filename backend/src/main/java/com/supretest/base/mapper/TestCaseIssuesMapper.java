package com.supretest.base.mapper;

import com.supretest.api.dto.definition.ParamsDTO;
import com.supretest.base.domain.TestCaseIssues;
import com.supretest.base.domain.TestCaseIssuesExample;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface TestCaseIssuesMapper {
    long countByExample(TestCaseIssuesExample example);

    int deleteByExample(TestCaseIssuesExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestCaseIssues record);

    int insertSelective(TestCaseIssues record);

    List<TestCaseIssues> selectByExample(TestCaseIssuesExample example);

    TestCaseIssues selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestCaseIssues record, @Param("example") TestCaseIssuesExample example);

    int updateByExample(@Param("record") TestCaseIssues record, @Param("example") TestCaseIssuesExample example);

    int updateByPrimaryKeySelective(TestCaseIssues record);

    int updateByPrimaryKey(TestCaseIssues record);

}