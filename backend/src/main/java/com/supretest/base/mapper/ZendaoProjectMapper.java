package com.supretest.base.mapper;

import com.supretest.base.domain.ZendaoBranchTree;
import com.supretest.base.domain.ZendaoBranchTreeExample;
import com.supretest.base.domain.ZendaoProject;
import com.supretest.base.domain.ZendaoProjectExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface ZendaoProjectMapper {
    long countByExample(ZendaoProjectExample example);

    int deleteByExample(ZendaoProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(ZendaoProject record);

    int insertSelective(ZendaoProject record);

    @MapKey("zendaoProjectId")
    Map<String, ZendaoProject> selectByProductId(String productId);

    List<ZendaoProject> selectByExample(ZendaoProjectExample example);

    ZendaoProject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ZendaoProject record, @Param("example") ZendaoProjectExample example);

    int updateByExample(@Param("record") ZendaoProject record, @Param("example") ZendaoProjectExample example);

    int updateByPrimaryKeySelective(ZendaoProject record);

//    int updateByPrimaryKey(ZendaoProject record);

    int updateByZendaoProjectId(ZendaoProject record);
}