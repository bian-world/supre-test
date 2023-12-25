package com.supretest.ui.service;

import com.supretest.api.dto.automation.SaveApiScenarioRequest;
import com.supretest.api.dto.definition.request.MsScenario;
import com.supretest.api.service.ApiAutomationRelationshipEdgeService;
import com.supretest.base.domain.ApiScenario;
import com.supretest.base.domain.ApiScenarioWithBLOBs;
import com.supretest.base.domain.UiElement;
import com.supretest.base.domain.UiPage;
import com.supretest.base.mapper.ApiScenarioMapper;
import com.supretest.base.mapper.UiElementMapper;
import com.supretest.base.mapper.UiPageMapper;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.commons.utils.ServiceUtils;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.ui.controller.request.UiPageRequest;
import com.supretest.ui.dto.UiElementDTO;
import com.supretest.ui.dto.UiPageDTO;
import com.supretest.ui.dto.UiPageElementsDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.swing.text.Element;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class UiPageService {

    @Resource
    private UiPageMapper uiPageMapper;

    @Resource
    private UiElementService uiElementService;

    public List<UiPageDTO> list(UiPageRequest request) {
        return uiPageMapper.list(request);
    }


    public UiPage create(UiPageDTO request) {
        request.setId(UUID.randomUUID().toString());
        final UiPage uiPage = buildUiPage(request);

        uiPageMapper.insert(uiPage);

        return uiPage;
    }

    public UiPage getPage(String id) {
        return uiPageMapper.getPage(id);
    }

    public List<UiElement> getPageElements(String id) {
        return uiPageMapper.getPageElements(id);
    }

    public int delete(UiPageDTO request) {
        return uiPageMapper.delete(request);
    }

    @Transactional
    public UiPageElementsDTO editPageElements(UiPageElementsDTO request) {

//        更新页面
        if(request.getId().isEmpty() || request.getId() == null){
//            pageId为空新建，不为空更新
            request.setId(UUID.randomUUID().toString());
            request.setCreateUser(SessionUtils.getUserId());
            final UiPage uiPage = buildUiPage(request);
            uiPageMapper.insert(uiPage);
        } else {
            final UiPage uiPage = buildUiPage(request);
            uiPageMapper.update(uiPage);
        }

        List<UiElementDTO> elementDTOS = request.getUiElementDTOS();

//         新增页面的同时新增元素，将元素与页面关联
//        for (UiElementDTO uiElementDTO: elementDTOS) {
//
//        }
//        修改元素
        for (UiElementDTO uiElementDTO: elementDTOS) {
            uiElementDTO.setModuleId(request.getModuleId());
            uiElementDTO.setProjectId(request.getProjectId());
            if (uiElementDTO.getId() == null || uiElementDTO.getId().isEmpty()){
                // elementId为空新建，不为空更新
                uiElementDTO.setPageId(request.getId());
                uiElementService.create(uiElementDTO);
            } else {
                uiElementService.update(uiElementDTO);
            }
        }
        return request;
    }

    public int update(UiPageDTO request) {
        final UiPage uiPage = buildUiPage(request);
        return uiPageMapper.update(uiPage);
    }

    public UiPage buildUiPage(UiPageElementsDTO request){
        request.setNum(getNextNum(request.getProjectId()));

        UiPage uiPage = new UiPage();
        uiPage.setId(request.getId());
        uiPage.setNum(request.getNum());
        uiPage.setParentId(request.getParentId());
        uiPage.setModuleId(request.getModuleId());
        uiPage.setProjectId(request.getProjectId());
        uiPage.setName(request.getName());
        uiPage.setCreateUser(request.getCreateUser());
        uiPage.setDescription(request.getDescription());
//        uiPage.setCreateTime(request.getCreateTime());
//        uiPage.setUpdateTime(request.getCreateTime());
        uiPage.setSubProjectId(request.getSubProjectId());

//        if (request.getUpdateTime() != 0){
//            uiPage.setUpdateTime(request.getUpdateTime());
//        }
        buildPageLevel(uiPage);
        return uiPage;
    }

    public UiPage buildUiPage(UiPageDTO request){
        request.setNum(getNextNum(request.getProjectId()));

        UiPage uiPage = new UiPage();
        uiPage.setId(request.getId());
        uiPage.setNum(request.getNum());
        uiPage.setParentId(request.getParentId());
        uiPage.setModuleId(request.getModuleId());
        uiPage.setProjectId(request.getProjectId());
        uiPage.setName(request.getName());
        uiPage.setCreateUser(request.getCreateUser());
        uiPage.setDescription(request.getDescription());
//        uiPage.setCreateTime(request.getCreateTime());
//        uiPage.setUpdateTime(request.getCreateTime());
        uiPage.setSubProjectId(request.getSubProjectId());

//        if (request.getUpdateTime() != 0){
//            uiPage.setUpdateTime(request.getUpdateTime());
//        }
        buildPageLevel(uiPage);
        return uiPage;
    }

    public Integer getNextNum(String projectId){
        UiPage uiPage = uiPageMapper.getNextNum(projectId);

        if (uiPage == null || uiPage.getNum() == null) {
            return 100001;
        } else {
            return Optional.of(uiPage.getNum() + 1).orElse(100001);
        }
    }

    public void buildPageLevel(UiPage uiPage){
        String pageId = uiPage.getParentId();
        if(StringUtils.isNotEmpty(pageId)){
           UiPage parentPage =  uiPageMapper.getPage(pageId);
           uiPage.setPageLevel(StringUtils.join(parentPage.getPageLevel(), ",", parentPage.getId()));
        }else{
            uiPage.setPageLevel("");
        }
    }



}
