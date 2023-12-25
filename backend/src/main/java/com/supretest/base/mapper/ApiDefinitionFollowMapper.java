package com.supretest.base.mapper;

import com.supretest.base.domain.ApiDefinitionFollow;
import com.supretest.base.domain.ApiDefinitionFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiDefinitionFollowMapper {
    long countByExample(ApiDefinitionFollowExample example);

    int deleteByExample(ApiDefinitionFollowExample example);

    int insert(ApiDefinitionFollow record);

    int insertSelective(ApiDefinitionFollow record);

    List<ApiDefinitionFollow> selectByExample(ApiDefinitionFollowExample example);

    int updateByExampleSelective(@Param("record") ApiDefinitionFollow record, @Param("example") ApiDefinitionFollowExample example);

    int updateByExample(@Param("record") ApiDefinitionFollow record, @Param("example") ApiDefinitionFollowExample example);
}