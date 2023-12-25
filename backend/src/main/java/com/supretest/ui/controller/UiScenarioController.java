package com.supretest.ui.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.dto.automation.*;
import com.supretest.base.domain.*;
import com.supretest.commons.constants.*;
import com.supretest.controller.request.ScheduleRequest;
import com.supretest.ui.dto.definition.RunDefinitionRequest;
import com.supretest.api.service.ApiAutomationService;
import com.supretest.api.service.MsHashTreeService;
import com.supretest.base.mapper.ext.ExtUiScenarioMapper;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.notice.annotation.SendNotice;
import com.supretest.service.CheckPermissionService;
import com.supretest.ui.dto.SaveUiScenarioRequest;
import com.supretest.ui.dto.UiPageDTO;
import com.supretest.ui.dto.UiScenarioDTO;
import com.supretest.ui.dto.UiScenarioRequest;
import com.supretest.ui.parse.UiStepToScript;
import com.supretest.ui.service.StHashTreeService;
import com.supretest.ui.service.UiElementService;
import com.supretest.ui.service.UiScenarioService;
import io.metersphere.dto.MsExecResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ui/scenario")
public class UiScenarioController {

    @Resource
    private CheckPermissionService checkPermissionService;

    @Resource
    private UiScenarioService uiScenarioService;

    @Resource
    private MsHashTreeService hashTreeService;

    @Resource
    private StHashTreeService stHashTreeService;

    @Resource
    private ExtUiScenarioMapper extUiScenarioMapper;

    @Resource
    private UiElementService uiElementService;

    @Resource
    private UiStepToScript uiStepToScript;

    /**
     * 新建UI自动化用例
     * @param request
     * @param bodyFiles
     * @param scenarioFiles
     * @return
     */
    @PostMapping(value = "/create")
//    @RequiresPermissions(PermissionConstants.PROJECT_UI_PAGE_READ_CREATE)
    public UiScenarioWithBLOBs create(@RequestPart("request") SaveUiScenarioRequest request, @RequestPart(value = "bodyFiles", required = false) List<MultipartFile> bodyFiles,
                                      @RequestPart(value = "scenarioFiles", required = false) List<MultipartFile> scenarioFiles) {

        return uiScenarioService.create(request, bodyFiles, scenarioFiles);
    }


    @PostMapping("/list/{goPage}/{pageSize}")
    @RequiresPermissions("PROJECT_API_SCENARIO:READ")
    public Pager<List<UiScenarioDTO>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody UiScenarioRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        // 查询场景环境
        request.setSelectEnvironment(true);
        return PageUtils.setPageInfo(page, uiScenarioService.list(request));
    }

    /**
     *  前置场景选择
     * @param projectId
     * @param subProjectId
     * @return 返回uiscenario id 和name
     */
    @GetMapping("/listAll/{projectId}/{subProjectId}")
    @RequiresPermissions("PROJECT_API_SCENARIO:READ")
    public List<Map<String,String>> listAll(@PathVariable String projectId, @PathVariable String subProjectId) {
        return uiScenarioService.listAll(projectId,subProjectId);
    }


    @GetMapping("/listAll/{projectId}")
    @RequiresPermissions("PROJECT_API_SCENARIO:READ")
    public List<Map<String,String>> listAll(@PathVariable String projectId) {
        return uiScenarioService.listAll(projectId,null);
    }


    @GetMapping(value = "/{scenarioId}", consumes = {"application/json"})
    @RequiresPermissions(PermissionConstants.PROJECT_UI_PAGE_READ_CREATE)
    public UiScenarioWithBLOBs get(@PathVariable String scenarioId) {
        return uiScenarioService.selectByPrimaryKey(scenarioId);
    }

    /**
     * 查询UI自动化用例详情
     * @param id
     * @return
     */
    @GetMapping("/getUiScenario/{id}")
    public UiScenarioDTO getScenarioDefinition(@PathVariable String id) {
        return uiScenarioService.getScenarioDefinitions(id);
    }

    /**
     * 查询case引用的前置场景
     * @param id
     * @return
     */
    @GetMapping("/getUiScenario/preScenario/{id}")
    public UiScenarioDTO getScenarioDefinitions(@PathVariable String id) {
        return uiScenarioService.getProScenarioDefinitions(id);
    }

    @GetMapping("/test")
    public String getScenarioDefinition(@RequestBody HashMap step) {
        System.out.println(uiStepToScript.stepToScript(step));

        if(step.get("operationType").toString().equals("WebElement")) {
            UiElement uiElement = uiElementService.getElement((String) step.get("elementId"));
//                    uiElementService.getLocationType((String)uiElement.getLocationType());
            step.put("locationType", uiElementService.getLocationTypeById(uiElement.getLocationType()));
            step.put("location", uiElement.getLocation());
        } else if (step.get("operationType").toString().equals("browser")) {
            step.put("locationType", "browser");
        }
        String script = uiStepToScript.stepToScript(step);
        return uiStepToScript.stepToScript(step);
    }

    /**
     * 删除UI自动化用例
     * @param id scenarioID
     * @return
     */
    @PostMapping("/delete/{id}")
    @MsAuditLog(module = OperLogModule.API_AUTOMATION, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#id)", msClass = UiScenarioService.class)
//    @RequiresPermissions(PermissionConstants.PROJECT_API_SCENARIO_READ_DELETE)
    public int delete(@PathVariable String id) {
        return uiScenarioService.delete(id);
    }

    @PostMapping(value = "/update")
    @MsAuditLog(module = OperLogModule.API_AUTOMATION, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#request.id)", title = "#request.name", content = "#msClass.getLogDetails(#request.id)", msClass = UiScenarioService.class)
//    @RequiresPermissions(value = {PermissionConstants.PROJECT_API_SCENARIO_READ_EDIT, PermissionConstants.PROJECT_API_SCENARIO_READ_COPY}, logical = Logical.OR)
//    @SendNotice(taskType = NoticeConstants.TaskType.API_AUTOMATION_TASK, event = NoticeConstants.Event.UPDATE, mailTemplate = "api/AutomationUpdate", subject = "接口自动化通知")
    public UiScenario update(@RequestPart("request") SaveUiScenarioRequest request, @RequestPart(value = "bodyFiles", required = false) List<MultipartFile> bodyFiles,
                             @RequestPart(value = "scenarioFiles", required = false) List<MultipartFile> scenarioFiles) {
        return uiScenarioService.update(request, bodyFiles, scenarioFiles);
    }

    @PostMapping(value = "/run/debug")
    @MsAuditLog(module = OperLogModule.API_AUTOMATION, type = OperLogConstants.DEBUG, title = "#request.scenarioName", sourceId = "#request.scenarioId", project = "#request.projectId")
    public void runDebug(@RequestPart("request") RunDefinitionRequest request,
                         @RequestPart(value = "bodyFiles", required = false) List<MultipartFile> bodyFiles, @RequestPart(value = "scenarioFiles", required = false) List<MultipartFile> scenarioFiles) {
        if (StringUtils.isEmpty(request.getExecuteType())) {
            request.setExecuteType(ExecuteType.Debug.name());
        }
        uiScenarioService.debugRun(request, bodyFiles, scenarioFiles);
    }

    @PostMapping(value = "/run/batch")
    @MsAuditLog(module = OperLogModule.API_AUTOMATION, type = OperLogConstants.EXECUTE, content = "#msClass.getLogDetails(#request.ids)")
    public List<MsExecResponseDTO> runBatch(@RequestBody RunScenarioRequest request) {
        request.setExecuteType(ExecuteType.Saved.name());
        request.setTriggerMode(TriggerMode.BATCH.name());
        request.setRunMode(ApiRunMode.UI_SCENARIO.name());
        return uiScenarioService.run(request);
    }


    @PostMapping(value = "/schedule/update")
    public void updateSchedule(@RequestBody Schedule request) {
        uiScenarioService.updateSchedule(request);
    }

    @PostMapping(value = "/schedule/create")
    public void createSchedule(@RequestBody ScheduleRequest request) {
        uiScenarioService.createSchedule(request);
    }


}
