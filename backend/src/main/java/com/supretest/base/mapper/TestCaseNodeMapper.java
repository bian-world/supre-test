package com.supretest.base.mapper;

import com.supretest.base.domain.TestCaseNode;
import com.supretest.base.domain.TestCaseNodeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.K;

public interface TestCaseNodeMapper {
    long countByExample(TestCaseNodeExample example);

    int deleteByExample(TestCaseNodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestCaseNode record);

    int insertSelective(TestCaseNode record);

    List<TestCaseNode> selectByExample(TestCaseNodeExample example);

    TestCaseNode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestCaseNode record, @Param("example") TestCaseNodeExample example);

    int updateByExample(@Param("record") TestCaseNode record, @Param("example") TestCaseNodeExample example);

    int updateByPrimaryKeySelective(TestCaseNode record);

    int updateByPrimaryKey(TestCaseNode record);

    @MapKey("zentaoTreeId")
    Map<String,TestCaseNode> selectByProjectId(String projectId);

}