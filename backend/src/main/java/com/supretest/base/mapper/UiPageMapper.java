package com.supretest.base.mapper;

import com.supretest.api.dto.definition.ApiDefinitionRequest;
import com.supretest.base.domain.ApiDefinitionWithBLOBs;
import com.supretest.base.domain.UiElement;
import com.supretest.base.domain.UiPage;
import com.supretest.ui.controller.request.UiPageRequest;
import com.supretest.ui.dto.UiPageDTO;
import org.apache.ibatis.annotations.Param;

import javax.swing.text.Element;
import java.util.List;
import java.util.Map;

public interface UiPageMapper {

    List<UiPageDTO> list(@Param("request") UiPageRequest uiPage);

    int insert(UiPage uiPage);

    UiPage getPage(String id);

    List<UiElement> getPageElements(@Param("id") String id);

    int delete(UiPage uiPage);

    int update(UiPage uiPage);

    UiPage getNextNum(String projectId);

    int updateByPrimaryKeySelective(UiPage page);

    List<UiPage> listAll(@Param("request") UiPageRequest uiPageRequest);

    List<Map<String, Object>> moduleCountByCollection(@Param("request") UiPageRequest uiPageRequest);

}
