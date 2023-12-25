package com.supretest.base.mapper;

import com.supretest.base.domain.TestPlanReportContent;
import com.supretest.base.domain.TestPlanReportContentExample;
import com.supretest.base.domain.TestPlanReportContentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestPlanReportContentMapper {
    long countByExample(TestPlanReportContentExample example);

    int deleteByExample(TestPlanReportContentExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestPlanReportContentWithBLOBs record);

    int insertSelective(TestPlanReportContentWithBLOBs record);

    List<TestPlanReportContentWithBLOBs> selectByExampleWithBLOBs(TestPlanReportContentExample example);

    List<TestPlanReportContent> selectByExample(TestPlanReportContentExample example);

    TestPlanReportContentWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestPlanReportContentWithBLOBs record, @Param("example") TestPlanReportContentExample example);

    int updateByExampleWithBLOBs(@Param("record") TestPlanReportContentWithBLOBs record, @Param("example") TestPlanReportContentExample example);

    int updateByExample(@Param("record") TestPlanReportContent record, @Param("example") TestPlanReportContentExample example);

    int updateByPrimaryKeySelective(TestPlanReportContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TestPlanReportContentWithBLOBs record);

    int updateByPrimaryKey(TestPlanReportContent record);
}