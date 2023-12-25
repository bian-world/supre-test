package com.supretest.base.mapper;

import com.supretest.base.domain.ApiModule;
import com.supretest.base.domain.ApiModuleExample;
import java.util.List;
import java.util.Map;

import com.supretest.base.domain.TestCaseNode;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface ApiModuleMapper {
    long countByExample(ApiModuleExample example);

    int deleteByExample(ApiModuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApiModule record);

    int insertSelective(ApiModule record);

    List<ApiModule> selectByExample(ApiModuleExample example);

    ApiModule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApiModule record, @Param("example") ApiModuleExample example);

    int updateByExample(@Param("record") ApiModule record, @Param("example") ApiModuleExample example);

    int updateByPrimaryKeySelective(ApiModule record);

    int updateByPrimaryKey(ApiModule record);

    @MapKey("zentaoTreeId")
    Map<String, ApiModule> selectByProjectIdAndProtocol(String projectId, String protocol);
}