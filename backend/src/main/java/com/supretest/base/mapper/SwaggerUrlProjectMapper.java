package com.supretest.base.mapper;

import com.supretest.base.domain.SwaggerUrlProject;
import com.supretest.base.domain.SwaggerUrlProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SwaggerUrlProjectMapper {
    long countByExample(SwaggerUrlProjectExample example);

    int deleteByExample(SwaggerUrlProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(SwaggerUrlProject record);

    int insertSelective(SwaggerUrlProject record);

    List<SwaggerUrlProject> selectByExampleWithBLOBs(SwaggerUrlProjectExample example);

    List<SwaggerUrlProject> selectByExample(SwaggerUrlProjectExample example);

    SwaggerUrlProject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SwaggerUrlProject record, @Param("example") SwaggerUrlProjectExample example);

    int updateByExampleWithBLOBs(@Param("record") SwaggerUrlProject record, @Param("example") SwaggerUrlProjectExample example);

    int updateByExample(@Param("record") SwaggerUrlProject record, @Param("example") SwaggerUrlProjectExample example);

    int updateByPrimaryKeySelective(SwaggerUrlProject record);

    int updateByPrimaryKeyWithBLOBs(SwaggerUrlProject record);

    int updateByPrimaryKey(SwaggerUrlProject record);
}