package com.supretest.ui.service;

import com.supretest.base.domain.UiElement;
import com.supretest.base.domain.UiElementLocationType;
import com.supretest.base.domain.UiElementOperation;
import com.supretest.base.mapper.UiElementMapper;
import com.supretest.ui.dto.UiElementDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class UiElementService {

    @Resource
    private UiElementMapper uiElementMapper;

    public UiElement create(UiElementDTO request) {
        if(request.getId() == null || request.getId().isEmpty()){
            request.setId(UUID.randomUUID().toString());
        }
        final UiElement uiElement = buildUiElement(request);

        uiElementMapper.insert(uiElement);

        return uiElement;
    }

    public UiElement getElement(String id) {
        return uiElementMapper.selectByPrimaryKey(id);
    }

    public List<UiElementOperation> getOperation(){
        return uiElementMapper.getOperation();
    }

    public List<UiElementLocationType> getLocationType(){
        return uiElementMapper.getLocationType();
    }

    public String getLocationTypeById(String id){
        return uiElementMapper.getLocationTypeById(id);
    }

    public int delete(UiElementDTO request) {
        return uiElementMapper.deleteByPrimaryKey(request);
    }

    public int update(UiElementDTO request) {
        return uiElementMapper.updateByPrimaryKeySelective(request);
    }

    public UiElement buildUiElement(UiElementDTO request){
        request.setNum(getNextNum(request.getProjectId()));

        UiElement uiElement = new UiElement();
        uiElement.setId(request.getId());
        uiElement.setNum(request.getNum());
        uiElement.setPageId(request.getPageId());
        uiElement.setModuleId(request.getModuleId());
        uiElement.setProjectId(request.getProjectId());
        uiElement.setName(request.getName());
        uiElement.setLocationType(request.getLocationType());
        uiElement.setLocation(request.getLocation());
        uiElement.setCreateUser(request.getCreateUser());
        uiElement.setVersionId(request.getVersionId());
        uiElement.setRefId(request.getRefId());
        uiElement.setOrder(request.getOrder());
        uiElement.setLatest(request.getLatest());
        uiElement.setDescription(request.getDescription());
        uiElement.setCreateTime(request.getCreateTime());
        uiElement.setUpdateTime(request.getCreateTime());

//        if (request.getUpdateTime() != 0){
//            uiElement.setUpdateTime(request.getUpdateTime());
//        }

        return uiElement;
    }

    public Integer getNextNum(String projectId){
        UiElement uiElement = uiElementMapper.getNextNum(projectId);

        if (uiElement == null || uiElement.getNum() == null) {
            return 100001;
        } else {
            return Optional.of(uiElement.getNum() + 1).orElse(100001);
        }
    }


}
