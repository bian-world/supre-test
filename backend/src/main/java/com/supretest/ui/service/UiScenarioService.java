package com.supretest.ui.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.supretest.api.dto.EnvironmentType;
import com.supretest.api.dto.automation.ApiScenarioDTO;
import com.supretest.api.dto.automation.ApiScenarioRequest;
import com.supretest.api.dto.automation.RunScenarioRequest;
import com.supretest.api.dto.automation.ScenarioStatus;
import com.supretest.commons.constants.ScheduleGroup;
import com.supretest.commons.constants.ScheduleType;
import com.supretest.controller.request.ScheduleRequest;
import com.supretest.job.sechedule.ApiScenarioTestJob;
import com.supretest.job.sechedule.SwaggerUrlImportJob;
import com.supretest.job.sechedule.TestPlanTestJob;
import com.supretest.job.sechedule.UiScenarioTestJob;
import com.supretest.service.ScheduleService;
import com.supretest.ui.dto.definition.RunDefinitionRequest;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.UiScenarioMapper;
import com.supretest.base.mapper.UiScenarioModuleMapper;
import com.supretest.base.mapper.ext.ExtScheduleMapper;
import com.supretest.base.mapper.ext.ExtUiScenarioMapper;
import com.supretest.commons.utils.DateUtils;
import com.supretest.commons.utils.FileUtils;
import com.supretest.commons.utils.ServiceUtils;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.ui.dto.SaveUiScenarioRequest;
import com.supretest.ui.dto.UiScenarioDTO;
import com.supretest.ui.dto.UiScenarioRequest;
import com.supretest.ui.dto.definition.request.StUiScenario;
import com.supretest.ui.exec.UiScenarioExecuteService;
import io.metersphere.dto.MsExecResponseDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class UiScenarioService {

    @Resource
    private UiScenarioMapper uiScenarioMapper;

    @Resource
    private UiScenarioModuleMapper uiScenarioModuleMapper;

    @Resource
    private ExtScheduleMapper extScheduleMapper;

    @Resource
    private UiScenarioReferenceIdService uiScenarioReferenceIdService;

    @Resource
    private ExtUiScenarioMapper extUiScenarioMapper;

    @Resource
    private UiScenarioExecuteService uiScenarioExecuteService;

    @Resource
    private StHashTreeService stHashTreeService;

    public static final String HASH_TREE = "hashTree";

    public static final String PRE_SCENARIO_ID = "preScenarioId";

    @Resource
    private ScheduleService scheduleService;



    public UiScenarioWithBLOBs create(SaveUiScenarioRequest request, List<MultipartFile> bodyFiles, List<MultipartFile> scenarioFiles) {
        if (request.getId() == null || request.getId().isEmpty()){
            request.setId(UUID.randomUUID().toString());
        }
        if (request.getScenarioDefinition() == null) {
            StUiScenario stUiScenario = new StUiScenario();
            stUiScenario.setHashTree(new LinkedList<>());
            request.setScenarioDefinition(stUiScenario);
        }

        if(uiScenarioMapper.selectByPrimaryKey(request.getId()) != null){
//          UUID重复
            request.setId(UUID.randomUUID().toString());
        }
        final UiScenarioWithBLOBs uiScenarioWithBLOBs =  buildSaveScenario(request);

        // 存储uiScenario
        uiScenarioMapper.insert(uiScenarioWithBLOBs);
        // 存储依赖关系
        uiScenarioReferenceIdService.saveApiAndScenarioRelation(uiScenarioWithBLOBs);

        extScheduleMapper.updateNameByResourceID(request.getId(), request.getName());//  修改场景name，同步到修改首页定时任务
        // 上传文件
        uploadFiles(request, bodyFiles, scenarioFiles);

        return uiScenarioWithBLOBs;
    }

    public UiScenarioWithBLOBs selectByPrimaryKey(String id){
        return uiScenarioMapper.selectByPrimaryKey(id);
    }

    public UiScenarioDTO getScenarioDefinitions(String id){
        UiScenarioDTO uiScenarioDTO = extUiScenarioMapper.selectById(id);
        if (uiScenarioDTO != null && StringUtils.isNotEmpty(uiScenarioDTO.getScenarioDefinition())) {
            JSONObject element = JSON.parseObject(uiScenarioDTO.getScenarioDefinition(), Feature.DisableSpecialKeyDetect);
            stHashTreeService.dataFormatting(element);
            uiScenarioDTO.setScenarioDefinition(JSON.toJSONString(element));
        }
        return uiScenarioDTO;
    }

    public UiScenarioDTO getProScenarioDefinitions(String id){
        UiScenarioDTO uiScenarioDTO = extUiScenarioMapper.selectById(id);
        if (uiScenarioDTO != null && StringUtils.isNotEmpty(uiScenarioDTO.getScenarioDefinition())) {
            JSONObject element = JSON.parseObject(uiScenarioDTO.getScenarioDefinition(), Feature.DisableSpecialKeyDetect);
            // todo 递归查询前置的前置
//            if (element.get("preScenarioId") != null || !element.get("preScenarioId").toString().isEmpty()){
//                UiScenarioDTO uiProScenarioDTO = extUiScenarioMapper.selectById(element.get("preScenarioId").toString());
//                JSONObject proElement = JSON.parseObject(uiProScenarioDTO.getScenarioDefinition(), Feature.DisableSpecialKeyDetect);
//                JSONArray proElementJSONArray = proElement.getJSONArray(HASH_TREE);
//                JSONArray elementJSONArray = element.getJSONArray(HASH_TREE);
//                proElementJSONArray.addAll(elementJSONArray);
//                element.put(HASH_TREE,proElementJSONArray);
//            }
            formatProScenario(element);
            stHashTreeService.dataFormatting(element);
            uiScenarioDTO.setScenarioDefinition(JSON.toJSONString(element));
        }
        return uiScenarioDTO;
    }

    public List<UiScenarioDTO> list(UiScenarioRequest request) {
        request = this.initRequest(request, true, true);
        List<UiScenarioDTO> list = extUiScenarioMapper.list(request);
        return list;
    }

    public List<Map<String,String>> listAll(String projectId, String subProjectId) {
        return uiScenarioMapper.listAll(projectId, subProjectId);
    }

    public int delete(String id){
        uiScenarioReferenceIdService.deleteByScenarioId(id);
        return uiScenarioMapper.deleteByPrimaryKey(id);
    }

    public UiScenario update(SaveUiScenarioRequest request, List<MultipartFile> bodyFiles, List<MultipartFile> scenarioFiles){

        UiScenarioWithBLOBs uiScenarioWithBLOBs = buildSaveScenario(request);
        // 删除依赖关系
        uiScenarioReferenceIdService.deleteByScenarioId(uiScenarioWithBLOBs.getId());

        uiScenarioMapper.updateByPrimaryKeySelective(uiScenarioWithBLOBs);

        // 新建存储依赖关系
        uiScenarioReferenceIdService.saveApiAndScenarioRelation(uiScenarioWithBLOBs);

        extScheduleMapper.updateNameByResourceID(request.getId(), request.getName());//  修改场景name，同步到修改首页定时任务
        // 上传文件
        uploadFiles(request, bodyFiles, scenarioFiles);


        return uiScenarioWithBLOBs;
    }

    public void debugRun(RunDefinitionRequest request, List<MultipartFile> bodyFiles, List<MultipartFile> scenarioFiles){
//        apiScenarioExecuteService.debug(request, bodyFiles, scenarioFiles);
        uiScenarioExecuteService.debug(request, bodyFiles, scenarioFiles);
    }

    public List<MsExecResponseDTO> run(RunScenarioRequest request){
       return  uiScenarioExecuteService.run(request);

    }

    public UiScenarioWithBLOBs getNewApiScenario(String id){
        return uiScenarioMapper.selectByPrimaryKey(id);
    }

    public UiScenarioWithBLOBs buildSaveScenario(SaveUiScenarioRequest request) {
        UiScenarioWithBLOBs scenario = new UiScenarioWithBLOBs();
        scenario.setId(request.getId());
        scenario.setName(request.getName());
        scenario.setProjectId(request.getProjectId());
        scenario.setSubProjectId(request.getSubProjectId());
        scenario.setCustomNum(request.getCustomNum());
        if (StringUtils.equals(request.getTags(), "[]")) {
            scenario.setTags("");
        } else {
            scenario.setTags(request.getTags());
        }
        scenario.setModuleId(request.getModuleId());
        scenario.setModulePath(request.getModulePath());
        scenario.setLevel(request.getLevel());
        scenario.setPrincipal(request.getPrincipal());
        scenario.setStepTotal(request.getStepTotal());
//        scenario.setUpdateTime(System.currentTimeMillis());
        scenario.setDescription(request.getDescription());
        scenario.setCreateUser(SessionUtils.getUserId());
//        scenario.setCreateTime(request.getCreateTime());
        scenario.setOrder(request.getOrder());
        scenario.setVersionId(request.getVersionId());
        scenario.setRefId(request.getRefId());

        scenario.setSubProjectId(request.getSubProjectId());
        scenario.setLatest(request.getLatest());
        scenario.setExecuteTimes(request.getExecuteTimes());
        scenario.setDeleteTime(request.getDeleteTime());
        scenario.setDeleteUserId(request.getDeleteUserId());
        scenario.setVersion(request.getVersion());
        scenario.setUseUrl(request.getUseUrl());
        scenario.setOriginalState(request.getOriginalState());
        scenario.setNum(getNextNum(request.getProjectId()));

        scenario.setReportId(request.getReportId());
        scenario.setLastResult(request.getLastResult());
        scenario.setPassRate(request.getPassRate());
        scenario.setSchedule(request.getSchedule());

        scenario.setScenarioDefinition(JSON.toJSONString(request.getScenarioDefinition()));

//        scenario.setScenarioDefinition(request.getScenarioDefinition());

        Boolean isValidEnum = EnumUtils.isValidEnum(EnvironmentType.class, request.getEnvironmentType());
        if (BooleanUtils.isTrue(isValidEnum)) {
            scenario.setEnvironmentType(request.getEnvironmentType());
        } else {
            scenario.setEnvironmentType(EnvironmentType.JSON.toString());
        }
        scenario.setEnvironmentJson(request.getEnvironmentJson());
        scenario.setEnvironmentGroupId(request.getEnvironmentGroupId());
        if (StringUtils.isNotEmpty(request.getStatus())) {
            scenario.setStatus(request.getStatus());
        } else {
            scenario.setStatus(ScenarioStatus.Underway.name());
        }
        if (StringUtils.isNotEmpty(request.getUserId())) {
            scenario.setUserId(request.getUserId());
        } else {
            scenario.setUserId(SessionUtils.getUserId());
        }

        if (StringUtils.isEmpty(request.getModuleId()) || "default-module".equals(request.getModuleId())) {
            replenishScenarioModuleIdPath(request.getProjectId(), uiScenarioModuleMapper, scenario);
        }

        return scenario;
    }

    private void replenishScenarioModuleIdPath(String request, UiScenarioModuleMapper uiScenarioModuleMapper, UiScenarioWithBLOBs item) {
        UiScenarioModuleExample example = new UiScenarioModuleExample();
        example.createCriteria().andProjectIdEqualTo(request).andNameEqualTo("未规划场景");
        List<UiScenarioModule> modules = uiScenarioModuleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(modules)) {
            item.setModuleId(modules.get(0).getId());
            item.setModulePath(modules.get(0).getName());
        }
    }

    /**
     *
     * @param projectId
     * @return 最新的num
     */
    public Integer getNextNum(String projectId){
        UiScenario uiScenario = uiScenarioMapper.getNextNum(projectId);

        if (uiScenario == null || uiScenario.getNum() == null) {
            return 100001;
        } else {
            return Optional.of(uiScenario.getNum() + 1).orElse(100001);
        }
    }

    /**
     * 上传文件
     * @param request
     * @param bodyFiles
     * @param scenarioFiles
     */
    private void uploadFiles(SaveUiScenarioRequest request, List<MultipartFile> bodyFiles, List<MultipartFile> scenarioFiles) {
        FileUtils.createBodyFiles(request.getScenarioFileIds(), scenarioFiles);
        List<String> bodyFileRequestIds = request.getBodyFileRequestIds();
        if (CollectionUtils.isNotEmpty(bodyFileRequestIds)) {
            bodyFileRequestIds.forEach(requestId -> {
                FileUtils.createBodyFiles(requestId, bodyFiles);
            });
        }
    }

    private UiScenarioRequest initRequest(UiScenarioRequest request, boolean setDefultOrders, boolean checkThisWeekData) {
        if (setDefultOrders) {
            request.setOrders(ServiceUtils.getDefaultSortOrder(request.getOrders()));
        }
        if (StringUtils.isNotEmpty(request.getExecuteStatus())) {
            Map<String, List<String>> statusFilter = new HashMap<>();
            List<String> list = new ArrayList<>();
            list.add("Prepare");
            list.add("Underway");
            list.add("Completed");
            statusFilter.put("status", list);
            request.setFilters(statusFilter);
        }
        if (checkThisWeekData) {
            if (request.isSelectThisWeedData()) {
                Map<String, Date> weekFirstTimeAndLastTime = DateUtils.getWeedFirstTimeAndLastTime(new Date());
                Date weekFirstTime = weekFirstTimeAndLastTime.get("firstTime");
                if (weekFirstTime != null) {
                    request.setCreateTime(weekFirstTime.getTime());
                }
            }
        }
        return request;
    }

    private void formatProScenario(JSONObject element) {

        if(element.containsKey(PRE_SCENARIO_ID)){
            if (!StringUtils.isEmpty(element.get("preScenarioId").toString())){
                UiScenarioDTO uiProScenarioDTO = extUiScenarioMapper.selectById(element.get("preScenarioId").toString());
                JSONObject proElement = JSON.parseObject(uiProScenarioDTO.getScenarioDefinition(), Feature.DisableSpecialKeyDetect);
                // 递归查询前置
                formatProScenario(proElement);

                JSONArray proElementJSONArray = proElement.getJSONArray(HASH_TREE);
                JSONArray elementJSONArray = element.getJSONArray(HASH_TREE);
                proElementJSONArray.addAll(elementJSONArray);
                element.put(HASH_TREE,proElementJSONArray);
            }
        }


    }

    public void createSchedule(ScheduleRequest request) {
        Schedule schedule = scheduleService.buildApiTestSchedule(request);
        UiScenarioWithBLOBs apiScene = uiScenarioMapper.selectByPrimaryKey(request.getResourceId());
        schedule.setName(apiScene.getName());   //  add场景定时任务时，设置新增的数据库表字段的值
        schedule.setProjectId(apiScene.getProjectId());
        schedule.setJob(UiScenarioTestJob.class.getName());
        schedule.setGroup(ScheduleGroup.UI_SCENARIO_TEST.name());
        schedule.setType(ScheduleType.CRON.name());
        scheduleService.addSchedule(schedule);
        this.addOrUpdateApiScenarioCronJob(request);
    }

    public void updateSchedule(Schedule request) {
        scheduleService.editSchedule(request);
        this.addOrUpdateApiScenarioCronJob(request);
    }

    private  void addOrUpdateApiScenarioCronJob(Schedule request) {
        if (StringUtils.equals(request.getGroup(), ScheduleGroup.TEST_PLAN_TEST.name())) {
            scheduleService.addOrUpdateCronJob(
                    request, TestPlanTestJob.getJobKey(request.getResourceId()), TestPlanTestJob.getTriggerKey(request.getResourceId()), TestPlanTestJob.class);
        } else if (StringUtils.equals(request.getGroup(), ScheduleGroup.SWAGGER_IMPORT.name())) {
            scheduleService.addOrUpdateCronJob(request, SwaggerUrlImportJob.getJobKey(request.getResourceId()), SwaggerUrlImportJob.getTriggerKey(request.getResourceId()), SwaggerUrlImportJob.class);
        } else if(StringUtils.equals(request.getGroup(), ScheduleGroup.API_SCENARIO_TEST.name())){
            scheduleService.addOrUpdateCronJob(
                    request, ApiScenarioTestJob.getJobKey(request.getResourceId()), ApiScenarioTestJob.getTriggerKey(request.getResourceId()), ApiScenarioTestJob.class);
        }else{
            scheduleService.addOrUpdateCronJob(
                    request, UiScenarioTestJob.getJobKey(request.getResourceId()), UiScenarioTestJob.getTriggerKey(request.getResourceId()), UiScenarioTestJob.class);

        }

    }

}
