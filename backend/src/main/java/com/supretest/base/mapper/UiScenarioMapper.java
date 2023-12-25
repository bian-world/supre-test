package com.supretest.base.mapper;

import com.supretest.base.domain.Project;
import com.supretest.base.domain.UiScenario;
import com.supretest.base.domain.UiScenarioExample;
import com.supretest.base.domain.UiScenarioWithBLOBs;
import java.util.List;
import java.util.Map;

import com.supretest.ui.controller.request.UiPageRequest;
import org.apache.ibatis.annotations.Param;
import org.python.antlr.ast.Str;

public interface UiScenarioMapper {
    long countByExample(UiScenarioExample example);

    int deleteByExample(UiScenarioExample example);

    int deleteByPrimaryKey(String id);

    int insert(UiScenarioWithBLOBs record);

    int insertSelective(UiScenarioWithBLOBs record);

    List<UiScenarioWithBLOBs> selectByExampleWithBLOBs(UiScenarioExample example);

    List<UiScenario> selectByExample(UiScenarioExample example);

    List<Map<String,String>> listAll(@Param("projectId") String projectId, @Param("subProjectId") String subProjectId);

    UiScenarioWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UiScenarioWithBLOBs record, @Param("example") UiScenarioExample example);

    int updateByExampleWithBLOBs(@Param("record") UiScenarioWithBLOBs record, @Param("example") UiScenarioExample example);

    int updateByExample(@Param("record") UiScenario record, @Param("example") UiScenarioExample example);

    int updateByPrimaryKeySelective(UiScenarioWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UiScenarioWithBLOBs record);

    int updateByPrimaryKey(UiScenario record);

    UiScenario getNextNum(String projectId);

    List<Map<String, Object>> moduleCountByCollection(@Param("request") UiPageRequest uiPageRequest);
}