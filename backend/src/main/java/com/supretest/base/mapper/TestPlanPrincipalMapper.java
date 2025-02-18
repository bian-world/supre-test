package com.supretest.base.mapper;

import com.supretest.base.domain.TestPlanPrincipal;
import com.supretest.base.domain.TestPlanPrincipalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestPlanPrincipalMapper {
    long countByExample(TestPlanPrincipalExample example);

    int deleteByExample(TestPlanPrincipalExample example);

    int insert(TestPlanPrincipal record);

    int insertSelective(TestPlanPrincipal record);

    List<TestPlanPrincipal> selectByExample(TestPlanPrincipalExample example);

    int updateByExampleSelective(@Param("record") TestPlanPrincipal record, @Param("example") TestPlanPrincipalExample example);

    int updateByExample(@Param("record") TestPlanPrincipal record, @Param("example") TestPlanPrincipalExample example);
}