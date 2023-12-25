package com.supretest.ui.service;

import com.alibaba.fastjson.JSON;
import com.supretest.api.dto.EnvironmentType;
import com.supretest.api.dto.automation.ScenarioStatus;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.UiScenarioMapper;
import com.supretest.base.mapper.UiScenarioModuleMapper;
import com.supretest.base.mapper.UiScriptMappingMapper;
import com.supretest.base.mapper.ext.ExtScheduleMapper;
import com.supretest.commons.utils.FileUtils;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.ui.dto.SaveUiScenarioRequest;
import com.supretest.ui.dto.UiScriptMappingDTO;
import com.supretest.ui.dto.definition.request.StUiScenario;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class UiScriptMappingService {

    @Resource
    private UiScriptMappingMapper uiScriptMappingMapper;


    public UiScriptMapping selectByCondition(UiScriptMappingDTO uiScriptMappingDTO){
        return uiScriptMappingMapper.selectByCondition(uiScriptMappingDTO);
    }



}
