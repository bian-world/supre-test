package com.supretest.ui.dto.definition.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supretest.api.dto.EnvironmentType;
import com.supretest.api.dto.definition.request.ElementUtil;
import com.supretest.api.dto.definition.request.MsScenario;
import com.supretest.api.dto.definition.request.ParameterConfig;
import com.supretest.api.dto.definition.request.controller.MsCriticalSectionController;
import com.supretest.api.dto.definition.request.processors.MsJSR223Processor;
import com.supretest.api.dto.definition.request.variable.ScenarioVariable;
import com.supretest.api.dto.mockconfig.MockConfigStaticData;
import com.supretest.api.dto.scenario.KeyValue;
import com.supretest.api.dto.scenario.environment.EnvironmentConfig;
import com.supretest.api.service.ApiTestEnvironmentService;
import com.supretest.base.domain.ApiScenarioWithBLOBs;
import com.supretest.base.domain.ApiTestEnvironmentWithBLOBs;
import com.supretest.base.mapper.ApiScenarioMapper;
import com.supretest.commons.constants.MsTestElementConstants;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.commons.utils.FileUtils;
import com.supretest.service.EnvironmentGroupProjectService;
import io.metersphere.constants.RunModeConstants;
import io.metersphere.plugin.core.MsParameter;
import io.metersphere.plugin.core.MsTestElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.collections.HashTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
@EqualsAndHashCode(callSuper = true)
@JSONType(typeName = "scenario")
public class StUiScenario extends MsScenario {

    private String type = "scenario";
    private String clazzName = StUiScenario.class.getCanonicalName();


    @JSONField(ordinal = 31)
    private String preScenarioId;


    public StUiScenario() {
    }

    public StUiScenario(String name) {
        if (StringUtils.isEmpty(name)) {
            this.setName("Scenario");
        }
        this.setName(name);
    }

}
