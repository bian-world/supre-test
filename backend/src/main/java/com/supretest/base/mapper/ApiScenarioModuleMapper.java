package com.supretest.base.mapper;

import com.supretest.base.domain.ApiScenarioModule;
import com.supretest.base.domain.ApiScenarioModuleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface ApiScenarioModuleMapper {
    long countByExample(ApiScenarioModuleExample example);

    int deleteByExample(ApiScenarioModuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApiScenarioModule record);

    int insertSelective(ApiScenarioModule record);

    List<ApiScenarioModule> selectByExample(ApiScenarioModuleExample example);

    ApiScenarioModule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApiScenarioModule record, @Param("example") ApiScenarioModuleExample example);

    int updateByExample(@Param("record") ApiScenarioModule record, @Param("example") ApiScenarioModuleExample example);

    int updateByPrimaryKeySelective(ApiScenarioModule record);

    int updateByPrimaryKey(ApiScenarioModule record);

    @MapKey("zentaoTreeId")
    Map<String,ApiScenarioModule> selectByProjectId(String projectId);
}