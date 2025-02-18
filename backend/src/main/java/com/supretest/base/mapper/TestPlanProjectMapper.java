package com.supretest.base.mapper;

import com.supretest.base.domain.TestPlanProject;
import com.supretest.base.domain.TestPlanProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestPlanProjectMapper {
    long countByExample(TestPlanProjectExample example);

    int deleteByExample(TestPlanProjectExample example);

    int insert(TestPlanProject record);

    int insertSelective(TestPlanProject record);

    List<TestPlanProject> selectByExample(TestPlanProjectExample example);

    int updateByExampleSelective(@Param("record") TestPlanProject record, @Param("example") TestPlanProjectExample example);

    int updateByExample(@Param("record") TestPlanProject record, @Param("example") TestPlanProjectExample example);
}