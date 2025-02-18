package com.supretest.base.mapper;

import com.supretest.base.domain.UiElement;
import com.supretest.base.domain.UiElementExample;
import java.util.List;

import com.supretest.base.domain.UiElementLocationType;
import com.supretest.base.domain.UiElementOperation;
import com.supretest.ui.dto.UiElementDTO;
import org.apache.ibatis.annotations.Param;

public interface UiElementMapper {
    long countByExample(UiElementExample example);

    int deleteByExample(UiElementExample example);

    int deleteByPrimaryKey(UiElement record);

    int insert(UiElement record);

    int insertSelective(UiElement record);

    List<UiElement> selectByExample(UiElementExample example);

    UiElement selectByPrimaryKey(String id);

    List<UiElementOperation> getOperation();

    List<UiElementLocationType> getLocationType();

    String getLocationTypeById(@Param("id") String id);

    int updateByExampleSelective(@Param("record") UiElement record, @Param("example") UiElementExample example);

    int updateByExample(@Param("record") UiElement record, @Param("example") UiElementExample example);

    int updateByPrimaryKeySelective(UiElement record);

    int updateByPrimaryKey(UiElement record);

    UiElement getNextNum(String projectId);
}