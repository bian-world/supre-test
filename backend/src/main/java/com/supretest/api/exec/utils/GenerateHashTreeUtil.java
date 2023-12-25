package com.supretest.api.exec.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supretest.api.dto.EnvironmentType;
import com.supretest.api.dto.definition.request.*;
import com.supretest.api.dto.definition.request.variable.ScenarioVariable;
import com.supretest.api.jmeter.ResourcePoolCalculation;
import com.supretest.api.service.ApiExecutionQueueService;
import com.supretest.api.service.RemakeReportService;
import com.supretest.base.domain.ApiScenarioWithBLOBs;
import com.supretest.base.domain.TestResourcePool;
import com.supretest.base.domain.UiScenarioWithBLOBs;
import com.supretest.base.mapper.TestResourcePoolMapper;
import com.supretest.commons.constants.ResourcePoolTypeEnum;
import com.supretest.commons.utils.BeanUtils;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.commons.utils.LogUtil;
import com.supretest.ui.dto.definition.request.StUiScenario;
import io.metersphere.constants.RunModeConstants;
import io.metersphere.dto.*;
import com.supretest.dto.BaseSystemConfigDTO;
import io.metersphere.plugin.core.MsTestElement;
import com.supretest.service.EnvironmentGroupProjectService;
import io.metersphere.vo.BooleanPool;
import org.apache.commons.lang3.StringUtils;
import org.apache.jorphan.collections.HashTree;
import org.springframework.beans.factory.annotation.Value;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenerateHashTreeUtil {

    @Value("${server.http.port}")
    static Integer httpPort;

    public static MsScenario parseScenarioDefinition(String scenarioDefinition) {
        if (StringUtils.isNotEmpty(scenarioDefinition)) {
            MsScenario scenario = JSONObject.parseObject(scenarioDefinition, MsScenario.class, Feature.DisableSpecialKeyDetect);
            if (scenario != null) {
                parse(scenarioDefinition, scenario);
            }
            return scenario;
        }
        return null;
    }

    public static void parse(String scenarioDefinition, MsScenario scenario) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JSONObject element = JSON.parseObject(scenarioDefinition, Feature.DisableSpecialKeyDetect);
            ElementUtil.dataFormatting(element);
            // 多态JSON普通转换会丢失内容，需要通过 ObjectMapper 获取
            if (element != null && StringUtils.isNotEmpty(element.getString("hashTree"))) {
                LinkedList<MsTestElement> elements = mapper.readValue(element.getString("hashTree"),
                        new TypeReference<LinkedList<MsTestElement>>() {
                        });
                scenario.getHashTree().addAll(elements);
            }

            if (element != null && StringUtils.isNotEmpty(element.getString("variables"))) {
                LinkedList<ScenarioVariable> variables = mapper.readValue(element.getString("variables"),
                        new TypeReference<LinkedList<ScenarioVariable>>() {
                        });
                   scenario.getVariables().addAll(variables);
            }
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    public static LinkedList<MsTestElement> getScenarioHashTree(String definition) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSONObject element = JSON.parseObject(definition, Feature.DisableSpecialKeyDetect);
        try {
            if (element != null) {
                ElementUtil.dataFormatting(element);
                return objectMapper.readValue(element.getString("hashTree"), new TypeReference<LinkedList<MsTestElement>>() {
                });
            }
        } catch (JsonProcessingException e) {
            LogUtil.error(e.getMessage(), e);
        }
        return new LinkedList<>();
    }

    public static BooleanPool isResourcePool(String id) {
        BooleanPool pool = new BooleanPool();
        pool.setPool(StringUtils.isNotEmpty(id));
        if (pool.isPool()) {
            TestResourcePool resourcePool = CommonBeanFactory.getBean(TestResourcePoolMapper.class).selectByPrimaryKey(id);
            pool.setK8s(resourcePool != null && resourcePool.getApi() && resourcePool.getType().equals(ResourcePoolTypeEnum.K8S.name()));
        }
        return pool;
    }

    public static List<JvmInfoDTO> setPoolResource(String id) {
        if (GenerateHashTreeUtil.isResourcePool(id).isPool() && !GenerateHashTreeUtil.isResourcePool(id).isK8s()) {
            ResourcePoolCalculation resourcePoolCalculation = CommonBeanFactory.getBean(ResourcePoolCalculation.class);
            return resourcePoolCalculation.getPools(id);
        }
        return new LinkedList<>();
    }

    public static void setScenarioEnv(MsScenario scenario, ApiScenarioWithBLOBs apiScenarioWithBLOBs) {
        String environmentType = apiScenarioWithBLOBs.getEnvironmentType();
        String environmentJson = apiScenarioWithBLOBs.getEnvironmentJson();
        String environmentGroupId = apiScenarioWithBLOBs.getEnvironmentGroupId();
        if (StringUtils.isBlank(environmentType)) {
            environmentType = EnvironmentType.JSON.toString();
        }
        if (StringUtils.equals(environmentType, EnvironmentType.JSON.toString())) {
            scenario.setEnvironmentMap(JSON.parseObject(environmentJson, Map.class));
        } else if (StringUtils.equals(environmentType, EnvironmentType.GROUP.toString())) {
            Map<String, String> map = CommonBeanFactory.getBean(EnvironmentGroupProjectService.class).getEnvMap(environmentGroupId);
            scenario.setEnvironmentMap(map);
        }
    }

    public static HashTree generateHashTree(ApiScenarioWithBLOBs item, Map<String, String> planEnvMap, JmeterRunRequestDTO runRequest) {
        HashTree jmeterHashTree = new HashTree();
        MsTestPlan testPlan = new MsTestPlan();
        testPlan.setHashTree(new LinkedList<>());
        try {
            MsThreadGroup group = new MsThreadGroup();
            group.setLabel(item.getName());
            group.setName(runRequest.getReportId());
            MsScenario scenario = JSONObject.parseObject(item.getScenarioDefinition(), MsScenario.class, Feature.DisableSpecialKeyDetect);
            group.setOnSampleError(scenario.getOnSampleError());
            if (planEnvMap != null && planEnvMap.size() > 0) {
                scenario.setEnvironmentMap(planEnvMap);
            } else {
                setScenarioEnv(scenario, item);
            }
            GenerateHashTreeUtil.parse(item.getScenarioDefinition(), scenario);

            group.setEnableCookieShare(scenario.isEnableCookieShare());
            LinkedList<MsTestElement> scenarios = new LinkedList<>();
            scenarios.add(scenario);

            group.setHashTree(scenarios);
            testPlan.getHashTree().add(group);
        } catch (Exception ex) {
            RemakeReportService remakeReportService = CommonBeanFactory.getBean(RemakeReportService.class);
            remakeReportService.remake(runRequest);
            ResultDTO dto = new ResultDTO();
            BeanUtils.copyBean(dto, runRequest);
            CommonBeanFactory.getBean(ApiExecutionQueueService.class).queueNext(dto);
        }
        ParameterConfig config = new ParameterConfig();
        config.setScenarioId(item.getId());
        config.setReportType(runRequest.getReportType());
        testPlan.toHashTree(jmeterHashTree, testPlan.getHashTree(), config);
        return jmeterHashTree;
    }

    public static HashTree generateHashTree(List<UiScenarioWithBLOBs> items, Map<String, String> planEnvMap, JmeterRunRequestDTO runRequest) {
        HashTree jmeterHashTree = new HashTree();
        MsTestPlan testPlan = new MsTestPlan();
        testPlan.setHashTree(new LinkedList<>());
        try {
            MsThreadGroup group = new MsThreadGroup();
            group.setLabel(items.get(0).getName());
            group.setName(runRequest.getReportId());
            StUiScenario scenario = JSONObject.parseObject(items.get(0).getScenarioDefinition(), StUiScenario.class, Feature.DisableSpecialKeyDetect);
            group.setOnSampleError(scenario.getOnSampleError());
            if (planEnvMap != null && planEnvMap.size() > 0) {
                scenario.setEnvironmentMap(planEnvMap);
            }
//            else {
//                setScenarioEnv(scenario, item);
//            }

            items.stream().sorted(new Comparator<UiScenarioWithBLOBs>() {
                @Override
                public int compare(UiScenarioWithBLOBs o1, UiScenarioWithBLOBs o2) {
                    return -1;
                }
            }).forEach( item -> GenerateHashTreeUtil.parse(item.getScenarioDefinition(), scenario));

            group.setEnableCookieShare(scenario.isEnableCookieShare());
            LinkedList<MsTestElement> scenarios = new LinkedList<>();
            scenarios.add(scenario);

            group.setHashTree(scenarios);
            testPlan.getHashTree().add(group);
        } catch (Exception ex) {
            RemakeReportService remakeReportService = CommonBeanFactory.getBean(RemakeReportService.class);
            remakeReportService.remake(runRequest);
            ResultDTO dto = new ResultDTO();
            BeanUtils.copyBean(dto, runRequest);
            CommonBeanFactory.getBean(ApiExecutionQueueService.class).queueNext(dto);
        }
        ParameterConfig config = new ParameterConfig();
        config.setScenarioId(items.get(0).getId());
        config.setReportType(runRequest.getReportType());
        testPlan.toHashTree(jmeterHashTree, testPlan.getHashTree(), config);
        return jmeterHashTree;
    }

    public static boolean isSetReport(RunModeConfigDTO config) {
        return config != null && StringUtils.equals(config.getReportType(), RunModeConstants.SET_REPORT.toString()) && StringUtils.isNotEmpty(config.getReportName());
    }

    public static String getPlatformUrl(BaseSystemConfigDTO baseInfo, JmeterRunRequestDTO request, String queueDetailId) {
        // 占位符
        String platformUrl = "http://localhost:"+httpPort;
        if (baseInfo != null) {
            platformUrl = baseInfo.getUrl();
        }

        platformUrl += "/api/jmeter/download?testId="
                + request.getTestId()
                + "&reportId=" + request.getReportId()
                + "&runMode=" + request.getRunMode()
                + "&reportType=" + request.getReportType()
                + "&queueId=" + queueDetailId;
        return platformUrl;
    }
}