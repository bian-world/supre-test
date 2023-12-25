package com.supretest.ui.exec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.supretest.api.dto.EnvironmentType;
import com.supretest.api.dto.RunModeDataDTO;
import com.supretest.api.dto.automation.APIScenarioReportResult;
import com.supretest.api.dto.automation.ExecuteType;
import com.supretest.api.dto.automation.RunScenarioRequest;
//import com.supretest.api.dto.definition.RunDefinitionRequest;
import com.supretest.api.dto.definition.request.processors.post.MsJSR223PostProcessor;
import com.supretest.api.dto.definition.request.sampler.StWebDriverSampler;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.UiScenarioMapper;
import com.supretest.base.mapper.ext.ExtUiScenarioMapper;
import com.supretest.ui.dto.UiScenarioDTO;
import com.supretest.ui.dto.definition.RunDefinitionRequest;
import com.supretest.api.dto.definition.request.ParameterConfig;
import com.supretest.api.exec.queue.DBTestQueue;
import com.supretest.api.exec.scenario.ApiScenarioEnvService;
import com.supretest.api.exec.scenario.ApiScenarioParallelService;
import com.supretest.api.exec.scenario.ApiScenarioSerialService;
import com.supretest.api.exec.utils.GenerateHashTreeUtil;
import com.supretest.api.jmeter.JMeterService;
import com.supretest.api.service.ApiExecutionQueueService;
import com.supretest.api.service.ApiScenarioReportService;
import com.supretest.api.service.ApiScenarioReportStructureService;
import com.supretest.api.service.TcpApiParamService;
import com.supretest.base.mapper.ApiScenarioMapper;
import com.supretest.base.mapper.ApiScenarioReportMapper;
import com.supretest.base.mapper.TestPlanApiScenarioMapper;
import com.supretest.base.mapper.ext.ExtApiScenarioMapper;
import com.supretest.commons.constants.APITestStatus;
import com.supretest.commons.constants.ApiRunMode;
import com.supretest.commons.constants.ReportTriggerMode;
import com.supretest.commons.constants.ReportTypeConstants;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.FileUtils;
import com.supretest.commons.utils.LogUtil;
import com.supretest.commons.utils.ServiceUtils;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.i18n.Translator;
import com.supretest.service.EnvironmentGroupProjectService;
import com.supretest.track.service.TestPlanScenarioCaseService;
import com.supretest.ui.dto.definition.request.StUiScenario;
import com.supretest.ui.parse.UiStepToScript;
import com.supretest.ui.service.UiElementService;
import io.metersphere.constants.RunModeConstants;
import io.metersphere.dto.JmeterRunRequestDTO;
import io.metersphere.dto.MsExecResponseDTO;
import io.metersphere.dto.RunModeConfigDTO;
import io.metersphere.plugin.core.MsTestElement;
import io.metersphere.utils.LoggerUtil;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.comparators.FixedOrderComparator;
import org.apache.commons.lang3.StringUtils;
import org.apache.jorphan.collections.HashTree;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.swing.text.Element;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UiScenarioExecuteService {
    @Resource
    private ApiScenarioEnvService apiScenarioEnvService;
    @Resource
    private ApiScenarioMapper apiScenarioMapper;
    @Resource
    private UiScenarioMapper uiScenarioMapper;
    @Resource
    private ApiExecutionQueueService apiExecutionQueueService;
    @Resource
    private ExtApiScenarioMapper extApiScenarioMapper;
    @Resource
    private TestPlanApiScenarioMapper testPlanApiScenarioMapper;
    @Resource
    private ApiScenarioReportMapper apiScenarioReportMapper;
    @Resource
    @Lazy
    private TestPlanScenarioCaseService testPlanScenarioCaseService;
    @Resource
    private ApiScenarioReportService apiScenarioReportService;
    @Resource
    private EnvironmentGroupProjectService environmentGroupProjectService;
    @Resource
    private ApiScenarioReportStructureService reportStructureService;
    @Resource
    private ApiScenarioSerialService serialService;
    @Resource
    private ApiScenarioParallelService parallelService;
    @Resource
    private TcpApiParamService tcpApiParamService;
    @Resource
    private JMeterService jMeterService;

    @Resource
    private UiStepToScript uiStepToScript;

    @Resource
    private ExtUiScenarioMapper extUiScenarioMapper;

    @Resource
    private UiElementService uiElementService;

    public static final String HASH_TREE = "hashTree";

    public static final String TYPE = "type";

    public static final String SCENARIO = "scenario";

    private final static List<String> requests = new ArrayList<String>() {{
        this.add("HTTPSamplerProxy");
        this.add("DubboSampler");
        this.add("JDBCSampler");
        this.add("TCPSampler");
        this.add("StWebDriverSampler");
    }};


    public List<MsExecResponseDTO> run(RunScenarioRequest request) {
        if (LoggerUtil.getLogger().isDebugEnabled()) {
            LoggerUtil.debug("Scenario run-执行脚本装载-接收到场景执行参数：【 " + JSON.toJSONString(request) + " 】");
        }
        if (StringUtils.isEmpty(request.getTriggerMode())) {
            request.setTriggerMode(ReportTriggerMode.MANUAL.name());
        }
        if (request.getConfig() == null) {
            request.setConfig(new RunModeConfigDTO());
        }

        if (StringUtils.equals("GROUP", request.getConfig().getEnvironmentType()) && StringUtils.isNotEmpty(request.getConfig().getEnvironmentGroupId())) {
            request.getConfig().setEnvMap(environmentGroupProjectService.getEnvMap(request.getConfig().getEnvironmentGroupId()));
        }

        // 生成集成报告
        String serialReportId = null;
        LoggerUtil.info("Scenario run-执行脚本装载-根据条件查询所有场景 ");
        List<UiScenarioWithBLOBs> uiScenarios = this.get(request);
        // 只有一个场景且没有测试步骤，则提示
        if (uiScenarios != null && uiScenarios.size() == 1 && (uiScenarios.get(0).getStepTotal() == null || uiScenarios.get(0).getStepTotal() == 0)) {
            MSException.throwException((uiScenarios.get(0).getName() + "，" + Translator.get("automation_exec_info")));
        }
        // 环境检查
        LoggerUtil.info("Scenario run-执行脚本装载-开始针对所有执行场景进行环境检查");
//        apiScenarioEnvService.checkEnv(request, uiScenarios);
        // 集合报告设置
        if (StringUtils.equals(request.getConfig().getReportType(), RunModeConstants.SET_REPORT.toString())
                && StringUtils.isNotEmpty(request.getConfig().getReportName())) {
            if (request.getConfig().getMode().equals(RunModeConstants.SERIAL.toString())) {
                request.setExecuteType(ExecuteType.Completed.name());
            } else {
                request.setExecuteType(ExecuteType.Marge.name());
            }
            serialReportId = UUID.randomUUID().toString();
        }

        Map<String, RunModeDataDTO> executeQueue = new LinkedHashMap<>();
        List<String> scenarioIds = new ArrayList<>();
        StringBuilder scenarioNames = new StringBuilder();

        LoggerUtil.info("Scenario run-执行脚本装载-初始化执行队列");
        if (StringUtils.equalsAny(request.getRunMode(), ApiRunMode.SCENARIO_PLAN.name(),
                ApiRunMode.SCHEDULE_SCENARIO_PLAN.name(), ApiRunMode.JENKINS_SCENARIO_PLAN.name())) {
            //测试计划执行 todo
//            assemblyPlanScenario(uiScenarios, request, executeQueue, scenarioIds, scenarioNames);
        } else {
            // 按照场景执行
            assemblyScenario(uiScenarios, request, executeQueue, scenarioIds, scenarioNames, serialReportId);
        }
        LoggerUtil.info("Scenario run-执行脚本装载-初始化执行队列完成：" + executeQueue.size());

        List<MsExecResponseDTO> responseDTOS = new LinkedList<>();
        if (executeQueue.isEmpty()) {
            return responseDTOS;
        }
        if (GenerateHashTreeUtil.isSetReport(request.getConfig())) {
            LoggerUtil.info("Scenario run-执行脚本装载-初始化集成报告：" + serialReportId);
            request.getConfig().setReportId(UUID.randomUUID().toString());

            String reportScenarioIds = JSON.toJSONString(CollectionUtils.isNotEmpty(scenarioIds) && scenarioIds.size() > 50 ? scenarioIds.subList(0, 50) : scenarioIds);
            APIScenarioReportResult report = apiScenarioReportService.init(request.getConfig().getReportId(), reportScenarioIds,
                    scenarioNames.toString(), request.getTriggerMode(), ExecuteType.Saved.name(), request.getProjectId(),
                    request.getReportUserID(), request.getConfig());

            report.setVersionId(uiScenarios.get(0).getVersionId());
            report.setName(request.getConfig().getReportName());
            report.setId(serialReportId);
            report.setReportType(ReportTypeConstants.UI_INTEGRATED.name());
            request.getConfig().setAmassReport(serialReportId);
            report.setStatus(APITestStatus.Running.name());
            apiScenarioReportMapper.insert(report);

            responseDTOS.add(new MsExecResponseDTO(JSON.toJSONString(scenarioIds), serialReportId, request.getRunMode()));
            reportStructureService.saveUi(uiScenarios, serialReportId, request.getConfig().getReportType());
        }
        String reportType = request.getConfig().getReportType();
        String planReportId = StringUtils.isNotEmpty(request.getTestPlanReportId()) ? request.getTestPlanReportId() : serialReportId;
        // 生成执行队列
        DBTestQueue executionQueue = apiExecutionQueueService.add(executeQueue, request.getConfig().getResourcePoolId()
                , ApiRunMode.SCENARIO.name(), planReportId, reportType, request.getRunMode(), request.getConfig());

        // 预生成报告
        apiScenarioReportService.batchSave(executeQueue, serialReportId, request.getRunMode(), responseDTOS);

        // 开始执行
        String finalSerialReportId = serialReportId;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("SCENARIO-PARALLEL-THREAD");
                if (StringUtils.equals(request.getConfig().getMode(), RunModeConstants.SERIAL.toString())) {
                    serialService.serial(executionQueue, executionQueue.getQueue());
                } else {
                    parallelService.parallel(executeQueue, request, finalSerialReportId, executionQueue);
                }
            }
        });
        thread.start();

        return responseDTOS;
    }

    private List<UiScenarioWithBLOBs> get(RunScenarioRequest request) {
        ServiceUtils.getSelectAllIds(request, request.getCondition(),
                (query) -> extUiScenarioMapper.selectIdsByQuery(query));
        List<String> ids = request.getIds();
        UiScenarioExample example = new UiScenarioExample();
        example.createCriteria().andIdIn(ids);
        List<UiScenarioWithBLOBs> uiScenarios = uiScenarioMapper.selectByExampleWithBLOBs(example);
        if (request.getConfig() != null && request.getConfig().getMode().equals(RunModeConstants.SERIAL.toString())) {
            if (request.getCondition() == null || !request.getCondition().isSelectAll()) {
                // 按照id指定顺序排序
                FixedOrderComparator<String> fixedOrderComparator = new FixedOrderComparator<String>(ids);
                fixedOrderComparator.setUnknownObjectBehavior(FixedOrderComparator.UnknownObjectBehavior.BEFORE);
                BeanComparator beanComparator = new BeanComparator("id", fixedOrderComparator);
                Collections.sort(uiScenarios, beanComparator);
            }
        }
        return uiScenarios;
    }


    /**
     * 测试计划接口场景的预执行（生成场景报告）
     */
    private void assemblyPlanScenario(List<ApiScenarioWithBLOBs> apiScenarios, RunScenarioRequest request, Map<String, RunModeDataDTO> executeQueue, List<String> scenarioIds, StringBuilder scenarioNames) {
        String reportId = request.getId();
        Map<String, String> planScenarioIdMap = request.getScenarioTestPlanIdMap();
        if (MapUtils.isEmpty(planScenarioIdMap)) {
            return;
        }
        String projectId = request.getProjectId();
        Map<String, ApiScenarioWithBLOBs> scenarioMap = apiScenarios.stream().collect(Collectors.toMap(ApiScenarioWithBLOBs::getId, Function.identity(), (t1, t2) -> t1));
        for (Map.Entry<String, String> entry : planScenarioIdMap.entrySet()) {
            String testPlanScenarioId = entry.getKey();
            String scenarioId = entry.getValue();
            ApiScenarioWithBLOBs scenario = scenarioMap.get(scenarioId);

            if (scenario.getStepTotal() == null || scenario.getStepTotal() == 0) {
                continue;
            }

            // 获取场景用例单独的执行环境
            Map<String, String> planEnvMap = new HashMap<>();
            TestPlanApiScenario planApiScenario = testPlanApiScenarioMapper.selectByPrimaryKey(testPlanScenarioId);
            String envJson = planApiScenario.getEnvironment();
            String envType = planApiScenario.getEnvironmentType();
            String envGroupId = planApiScenario.getEnvironmentGroupId();
            if (StringUtils.equals(envType, EnvironmentType.JSON.toString()) && StringUtils.isNotBlank(envJson)) {
                planEnvMap = JSON.parseObject(envJson, Map.class);
            } else if (StringUtils.equals(envType, EnvironmentType.GROUP.toString()) && StringUtils.isNotBlank(envGroupId)) {
                planEnvMap = environmentGroupProjectService.getEnvMap(envGroupId);
            }
            if (StringUtils.isEmpty(projectId)) {
                projectId = testPlanScenarioCaseService.getProjectIdById(testPlanScenarioId);
            }
            if (StringUtils.isEmpty(projectId)) {
                projectId = scenario.getProjectId();
            }

            APIScenarioReportResult report = apiScenarioReportService.init(reportId, testPlanScenarioId, scenario.getName(), request.getTriggerMode(),
                    request.getExecuteType(), projectId, request.getReportUserID(), request.getConfig());

            report.setVersionId(scenario.getVersionId());
            scenarioIds.add(scenario.getId());
            RunModeDataDTO runModeDataDTO = new RunModeDataDTO();
            runModeDataDTO.setTestId(testPlanScenarioId);
            runModeDataDTO.setPlanEnvMap(planEnvMap);
            runModeDataDTO.setReport(report);
            runModeDataDTO.setReportId(report.getId());
            runModeDataDTO.setScenario(scenario);
            executeQueue.put(report.getId(), runModeDataDTO);
            scenarioNames.append(scenario.getName()).append(",");
            // 生成文档结构
            if (!StringUtils.equals(request.getConfig().getReportType(), RunModeConstants.SET_REPORT.toString())) {
                reportStructureService.save(scenario, report.getId(), request.getConfig() != null ? request.getConfig().getReportType() : null);
            }
            // 重置报告ID
            reportId = UUID.randomUUID().toString();
        }
    }

    /**
     * 接口场景的预执行（生成场景报告）
     */
    private void assemblyScenario(List<UiScenarioWithBLOBs> uiScenarios, RunScenarioRequest request, Map<String, RunModeDataDTO> executeQueue, List<String> scenarioIds, StringBuilder scenarioNames, String serialReportId) {
        String reportId = request.getId();
        for (int i = 0; i < uiScenarios.size(); i++) {
            UiScenarioWithBLOBs item = uiScenarios.get(i);
            if (item.getStepTotal() == null || item.getStepTotal() == 0) {
                continue;
            }
            APIScenarioReportResult report = apiScenarioReportService.init(reportId, item.getId(), item.getName(), request.getTriggerMode(),
                    request.getExecuteType(), item.getProjectId(), request.getReportUserID(), request.getConfig());
            scenarioIds.add(item.getId());
            report.setReportType(ReportTypeConstants.UI_INDEPENDENT.name());
            report.setVersionId(item.getVersionId());
            scenarioNames.append(item.getName()).append(",");

            RunModeDataDTO runModeDataDTO = new RunModeDataDTO();
            runModeDataDTO.setTestId(item.getId());
            runModeDataDTO.setPlanEnvMap(new HashMap<>());
            if (request.getConfig().getEnvMap() != null) {
                runModeDataDTO.setPlanEnvMap(request.getConfig().getEnvMap());
            }
            runModeDataDTO.setReport(report);
            runModeDataDTO.setReportId(report.getId());
            executeQueue.put(report.getId(), runModeDataDTO);
            runModeDataDTO.setUiScenario(item);
            executeQueue.put(report.getId(), runModeDataDTO);
            // 生成报告结构
            if (!StringUtils.equals(request.getConfig().getReportType(), RunModeConstants.SET_REPORT.toString())) {
                reportStructureService.save(item, report.getId(), request.getConfig().getReportType());
            }
            // 重置报告ID
            reportId = UUID.randomUUID().toString();
        }
    }

    public void testElement(RunDefinitionRequest request) {
        if (request.getTestElement() != null) {
            tcpApiParamService.checkTestElement(request.getTestElement());
        }
    }

    public String debug(RunDefinitionRequest request, List<MultipartFile> bodyFiles, List<MultipartFile> scenarioFiles) {
        Map<String, String> map = request.getEnvironmentMap();
        String envType = request.getEnvironmentType();
        if (StringUtils.equals(envType, EnvironmentType.GROUP.toString())) {
            String environmentGroupId = request.getEnvironmentGroupId();
            map = environmentGroupProjectService.getEnvMap(environmentGroupId);
        }
        ParameterConfig config = new ParameterConfig();
        config.setScenarioId(request.getScenarioId());
        if (map != null) {
            apiScenarioEnvService.setEnvConfig(map, config);
        }
        HashTree hashTree = null;
        try {
            uploadBodyFiles(request.getBodyFileRequestIds(), bodyFiles);
            FileUtils.createBodyFiles(request.getScenarioFileIds(), scenarioFiles);
//            JSONObject element = (JSONObject) JSONObject.toJSON(request.getTestElement());
//            System.out.println(element);
//            dataFormatting(element);
//            System.out.println(element.toString());
//            MsTestElement msTestElement = JSONObject.parseObject(element.toString(),MsTestElement.class);
//            MsTestElement msTestElement = JSON.parseObject(element.toJSONString(), UiTestElement.class);
//            System.out.println("msTestElement lkj9098765678" + msTestElement.toString());
//            request.setTestElement(msTestElement);

//            List<MsTestElement> stWebDriverSamplers = request.getTestElement().getHashTree().get(0).getHashTree().get(0).getHashTree();
            // 将step转化为script
//            for (int i = 0; i < stWebDriverSamplers.size(); i++) {
//                System.out.println(stWebDriverSamplers.get(i).toString());
//                StWebDriverSampler stWebDriverSampler = (StWebDriverSampler) stWebDriverSamplers.get(i);
//                HashMap step = (HashMap) stWebDriverSampler.getStep();
//
//                if(step.get("operationType").toString().equals("WebElement")) {
//                    UiElement uiElement = uiElementService.getElement((String) step.get("elementId"));
////                    uiElementService.getLocationType((String)uiElement.getLocationType());
//                    step.put("locationType", uiElementService.getLocationTypeById(uiElement.getLocationType()));
//                    step.put("location", uiElement.getLocation());
//                } else if (step.get("operationType").toString().equals("browser")) {
//                    step.put("locationType", "browser");
//                } else if (step.get("operationType").toString().equals("JavascriptExecutor")) {
//                    step.put("locationType", "JavascriptExecutor");
//                }
//                String script = uiStepToScript.stepToScript(step);
//                stWebDriverSampler.setScript(script);
//
//                // 添加后置log打印
////                LinkedList<MsTestElement> samplerHashTree = stWebDriverSampler.getHashTree();
////                MsJSR223PostProcessor msJSR223PostProcessor = buildMsJSR223PostProcessor(new MsJSR223PostProcessor());
////                msJSR223PostProcessor.setId(UUID.randomUUID().toString());
////                msJSR223PostProcessor.setScript(String.format("log.info(\"执行步骤: %d 步骤名称: %s\");", i+1, stWebDriverSampler.getName()));
////                samplerHashTree.add(msJSR223PostProcessor);
////                stWebDriverSampler.setHashTree(samplerHashTree);
//
//                stWebDriverSamplers.set(i,stWebDriverSampler);
//            }
//            formatUIScript(stWebDriverSamplers);
            hashTree = request.getTestElement().generateHashTree(config);
            LogUtil.info(request.getTestElement().getJmx(hashTree));
        } catch (Exception e) {
            LoggerUtil.error(e);
            MSException.throwException(e.getMessage());
        }
        if (request.isSaved()) {
            APIScenarioReportResult report = apiScenarioReportService.init(request.getId(),
                    request.getScenarioId(),
                    request.getScenarioName(),
                    ReportTriggerMode.MANUAL.name(),
                    request.getExecuteType(),
                    request.getProjectId(),
                    SessionUtils.getUserId(),
                    request.getConfig());
            ApiScenarioWithBLOBs scenario = apiScenarioMapper.selectByPrimaryKey(request.getScenarioId());
            String reportType = request.getConfig() != null ? request.getConfig().getReportType() : null;
            if (scenario != null) {
                report.setVersionId(scenario.getVersionId());
                String scenarioDefinition = JSON.toJSONString(request.getTestElement().getHashTree().get(0).getHashTree().get(0));
                scenario.setScenarioDefinition(scenarioDefinition);
                reportStructureService.save(scenario, report.getId(), reportType);
            } else {
                if (request.getTestElement() != null && CollectionUtils.isNotEmpty(request.getTestElement().getHashTree())) {
                    ApiScenarioWithBLOBs apiScenario = new ApiScenarioWithBLOBs();
                    apiScenario.setId(request.getScenarioId());
                    MsTestElement testElement = request.getTestElement().getHashTree().get(0).getHashTree().get(0);
                    if (testElement != null) {
                        apiScenario.setName(testElement.getName());
                        apiScenario.setScenarioDefinition(JSON.toJSONString(testElement));
                        reportStructureService.save(apiScenario, report.getId(), reportType);
                    }
                }
            }
            report.setReportType(ReportTypeConstants.UI_INDEPENDENT.name());
            apiScenarioReportMapper.insert(report);
        }
        String runMode = StringUtils.isEmpty(request.getRunMode()) ? ApiRunMode.SCENARIO.name() : request.getRunMode();
        // 调用执行方法
        JmeterRunRequestDTO runRequest = new JmeterRunRequestDTO(request.getId(), request.getId(), runMode, hashTree);
        runRequest.setDebug(true);
        jMeterService.run(runRequest);
        return request.getId();
    }

    public void formatUIScript(List<MsTestElement>  stWebDriverSamplers){

        for (int i = 0; i < stWebDriverSamplers.size(); i++) {
            System.out.println(stWebDriverSamplers.get(i).toString());
            StWebDriverSampler stWebDriverSampler = (StWebDriverSampler) stWebDriverSamplers.get(i);
            HashMap step = (HashMap) stWebDriverSampler.getStep();

            if(step.get("operationType").toString().equals("WebElement")) {
                UiElement uiElement = uiElementService.getElement((String) step.get("elementId"));
//                    uiElementService.getLocationType((String)uiElement.getLocationType());
                step.put("locationType", uiElementService.getLocationTypeById(uiElement.getLocationType()));
                step.put("location", uiElement.getLocation());
            } else if (step.get("operationType").toString().equals("browser")) {
                step.put("locationType", "browser");
            } else if (step.get("operationType").toString().equals("JavascriptExecutor")) {
                step.put("locationType", "JavascriptExecutor");
            }
            String script = uiStepToScript.stepToScript(step);
            stWebDriverSampler.setScript(script);
            stWebDriverSamplers.set(i,stWebDriverSampler);
        }

    }

    private void uploadBodyFiles(List<String> bodyFileRequestIds, List<MultipartFile> bodyFiles) {
        if (CollectionUtils.isNotEmpty(bodyFileRequestIds)) {
            bodyFileRequestIds.forEach(requestId -> {
                FileUtils.createBodyFiles(requestId, bodyFiles);
            });
        }
    }

    public void dataFormatting(JSONArray hashTree) {
        for (int i = 0; i < hashTree.size(); i++) {
            JSONObject element = hashTree.getJSONObject(i);
            if (element != null) {
                element = this.setRefScenario(element);
                hashTree.set(i, element);
            }

            if (element.containsKey(HASH_TREE)) {
                JSONArray elementJSONArray = element.getJSONArray(HASH_TREE);
                dataFormatting(elementJSONArray);
            }
        }
    }

    public void dataFormatting(JSONObject element) {
        if (element != null && StringUtils.equalsIgnoreCase(element.getString(TYPE), SCENARIO)) {
            element = this.setRefScenario(element);
        }
//        else if (element != null && requests.contains(element.getString(TYPE))) {
//            element = this.setRequest(element);
//        }
        if (element != null && element.containsKey(HASH_TREE)) {
            JSONArray elementJSONArray = element.getJSONArray(HASH_TREE);
            dataFormatting(elementJSONArray);
        }
    }

    private JSONObject setRefScenario(JSONObject element) {
        try {
            if(element.containsKey("step")){
                HashMap steps = JSONObject.parseObject(element.get("step").toString(),HashMap.class);
//                element.remove("script");
                element.put("script", uiStepToScript.stepToScript(steps));
            }

        }catch (Exception e){
            LogUtil.info("script key 不存在");
        }

        return element;
    }
//    {
//        "id": "f1b80ab9-6cc8-42be-b049-79cd392d07ba",
//            "type": "JSR223PostProcessor",
//            "name": "JSR223PostProcessor",
//            "enabled": true,
//            "$type": "PostProcessor",
//            "scriptLanguage": "beanshell",
//            "parameters": [],
//        "cacheKey": true,
//            "enable": true,
//            "script": "log.info(\"测试步骤:step-python \");",
//            "index": 1,
//            "active": true,
//            "requestResult": [],
//        "clazzName": "com.supretest.api.dto.definition.request.processors.post.MsJSR223PostProcessor"
//    }
    public MsJSR223PostProcessor buildMsJSR223PostProcessor(MsJSR223PostProcessor msJSR223PostProcessor){

        msJSR223PostProcessor.setType("JSR223PostProcessor");
        msJSR223PostProcessor.setName("JSR223PostProcessor");
        msJSR223PostProcessor.setEnable(true);
//        msJSR223PostProcessor.setEnabled(true);
        msJSR223PostProcessor.setScriptLanguage("beanshell");
        msJSR223PostProcessor.setIndex("1");
        msJSR223PostProcessor.setActive(true);
        return msJSR223PostProcessor;
    }
}
