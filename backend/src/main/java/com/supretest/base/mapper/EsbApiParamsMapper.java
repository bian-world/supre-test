package com.supretest.base.mapper;

import com.supretest.base.domain.EsbApiParams;
import com.supretest.base.domain.EsbApiParamsExample;
import com.supretest.base.domain.EsbApiParamsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsbApiParamsMapper {
    long countByExample(EsbApiParamsExample example);

    int deleteByExample(EsbApiParamsExample example);

    int deleteByPrimaryKey(String id);

    int insert(EsbApiParamsWithBLOBs record);

    int insertSelective(EsbApiParamsWithBLOBs record);

    List<EsbApiParamsWithBLOBs> selectByExampleWithBLOBs(EsbApiParamsExample example);

    List<EsbApiParams> selectByExample(EsbApiParamsExample example);

    EsbApiParamsWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EsbApiParamsWithBLOBs record, @Param("example") EsbApiParamsExample example);

    int updateByExampleWithBLOBs(@Param("record") EsbApiParamsWithBLOBs record, @Param("example") EsbApiParamsExample example);

    int updateByExample(@Param("record") EsbApiParams record, @Param("example") EsbApiParamsExample example);

    int updateByPrimaryKeySelective(EsbApiParamsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EsbApiParamsWithBLOBs record);

    int updateByPrimaryKey(EsbApiParams record);
}