package com.supretest.base.mapper;

import com.supretest.base.domain.ZendaoBranchTree;
import com.supretest.base.domain.ZendaoBranchTreeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface ZendaoBranchTreeMapper {
    long countByExample(ZendaoBranchTreeExample example);

    int deleteByExample(ZendaoBranchTreeExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZendaoBranchTree record);

    int insertSelective(ZendaoBranchTree record);

    @MapKey("zendaoModuleId")
    Map<String,ZendaoBranchTree> selectByExample(ZendaoBranchTreeExample example);

    ZendaoBranchTree selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZendaoBranchTree record, @Param("example") ZendaoBranchTreeExample example);

    int updateByExample(@Param("record") ZendaoBranchTree record, @Param("example") ZendaoBranchTreeExample example);

    int updateByPrimaryKeySelective(ZendaoBranchTree record);

    int updateByPrimaryKey(ZendaoBranchTree record);
}