package com.supretest.base.mapper;

import com.supretest.base.domain.CustomFunction;
import com.supretest.base.domain.CustomFunctionExample;
import com.supretest.base.domain.CustomFunctionWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomFunctionMapper {
    long countByExample(CustomFunctionExample example);

    int deleteByExample(CustomFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomFunctionWithBLOBs record);

    int insertSelective(CustomFunctionWithBLOBs record);

    List<CustomFunctionWithBLOBs> selectByExampleWithBLOBs(CustomFunctionExample example);

    List<CustomFunction> selectByExample(CustomFunctionExample example);

    CustomFunctionWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomFunctionWithBLOBs record, @Param("example") CustomFunctionExample example);

    int updateByExampleWithBLOBs(@Param("record") CustomFunctionWithBLOBs record, @Param("example") CustomFunctionExample example);

    int updateByExample(@Param("record") CustomFunction record, @Param("example") CustomFunctionExample example);

    int updateByPrimaryKeySelective(CustomFunctionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CustomFunctionWithBLOBs record);

    int updateByPrimaryKey(CustomFunction record);
}