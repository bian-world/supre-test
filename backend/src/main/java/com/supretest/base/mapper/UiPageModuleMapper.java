package com.supretest.base.mapper;

import com.supretest.base.domain.UiPageModule;
import com.supretest.base.domain.UiPageModuleExample;
import java.util.List;

import com.supretest.ui.dto.UiPageModuleDTO;
import org.apache.ibatis.annotations.Param;

public interface UiPageModuleMapper {
    long countByExample(UiPageModuleExample example);

    int deleteByExample(UiPageModuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(UiPageModule record);

    int insertSelective(UiPageModule record);

    List<UiPageModule> selectByExample(UiPageModuleExample example);

    List<UiPageModuleDTO> getNodeTreeByProjectId(String projectId);

    UiPageModule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UiPageModule record, @Param("example") UiPageModuleExample example);

    int updateByExample(@Param("record") UiPageModule record, @Param("example") UiPageModuleExample example);

    int updateByPrimaryKeySelective(UiPageModule record);

    int updateByPrimaryKey(UiPageModule record);

    void updatePos(String id, Double pos);

    String getNameById(String moduleId);
}