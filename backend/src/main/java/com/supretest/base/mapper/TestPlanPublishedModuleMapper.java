package com.supretest.base.mapper;

import com.supretest.base.domain.TestPlanPublishedModule;
import com.supretest.base.domain.TestPlanPublishedModuleExample;
import java.util.List;

import com.supretest.track.dto.TestPlanPublishedModuleDTO;
import org.apache.ibatis.annotations.Param;

public interface TestPlanPublishedModuleMapper {
    long countByExample(TestPlanPublishedModuleExample example);

    int deleteByExample(TestPlanPublishedModuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestPlanPublishedModule record);

    int insertSelective(TestPlanPublishedModule record);

    List<TestPlanPublishedModule> selectByExample(TestPlanPublishedModuleExample example);

    TestPlanPublishedModule selectByPrimaryKey(String id);

    List<TestPlanPublishedModuleDTO> selectByPlanId(String planId);

    int updateByExampleSelective(@Param("record") TestPlanPublishedModule record, @Param("example") TestPlanPublishedModuleExample example);

    int updateByExample(@Param("record") TestPlanPublishedModule record, @Param("example") TestPlanPublishedModuleExample example);

    int updateByPrimaryKeySelective(TestPlanPublishedModule record);

    int updateByPrimaryKey(TestPlanPublishedModule record);
}