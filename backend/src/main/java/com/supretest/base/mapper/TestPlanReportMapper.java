package com.supretest.base.mapper;

import com.supretest.api.dto.definition.ParamsDTO;
import com.supretest.base.domain.TestPlanReport;
import com.supretest.base.domain.TestPlanReportExample;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface TestPlanReportMapper {
    long countByExample(TestPlanReportExample example);

    int deleteByExample(TestPlanReportExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestPlanReport record);

    int insertSelective(TestPlanReport record);

    List<TestPlanReport> selectByExample(TestPlanReportExample example);

    TestPlanReport selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestPlanReport record, @Param("example") TestPlanReportExample example);

    int updateByExample(@Param("record") TestPlanReport record, @Param("example") TestPlanReportExample example);

    int updateByPrimaryKeySelective(TestPlanReport record);

    int updateByPrimaryKey(TestPlanReport record);

    @MapKey("id")
    Map<String, ParamsDTO> reportCount(@Param("planIds") Set<String> planIds);
}