package com.supretest.base.mapper;

import com.supretest.api.dto.automation.ApiScenarioDTO;
import com.supretest.api.dto.automation.ApiScenarioRequest;
import com.supretest.api.dto.definition.ApiTestCaseDTO;
import com.supretest.api.dto.definition.ApiTestCaseRequest;
import com.supretest.base.domain.TestCaseTest;
import com.supretest.base.domain.TestCaseTestExample;
import java.util.List;

import com.supretest.dto.LoadTestDTO;
import com.supretest.track.request.testplan.LoadCaseRequest;
import org.apache.ibatis.annotations.Param;

public interface TestCaseTestMapper {
    long countByExample(TestCaseTestExample example);

    int deleteByExample(TestCaseTestExample example);

    int insert(TestCaseTest record);

    int insertSelective(TestCaseTest record);

    List<TestCaseTest> selectByExample(TestCaseTestExample example);

    int updateByExampleSelective(@Param("record") TestCaseTest record, @Param("example") TestCaseTestExample example);

    int updateByExample(@Param("record") TestCaseTest record, @Param("example") TestCaseTestExample example);

    List<ApiTestCaseDTO> relevanceApiList(@Param("request") ApiTestCaseRequest request);

    List<ApiScenarioDTO> relevanceScenarioList(@Param("request") ApiScenarioRequest request);

    List<LoadTestDTO> relevanceLoadList(@Param("request") LoadCaseRequest request);
}
