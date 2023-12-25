package com.supretest.api.dto.definition.request.sampler;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmeter.plugins.webdriver.config.ChromeDriverConfig;
import com.googlecode.jmeter.plugins.webdriver.sampler.WebDriverSampler;
import com.supretest.api.dto.definition.parse.JMeterScriptUtil;
import com.supretest.api.dto.definition.request.ElementUtil;
import com.supretest.api.dto.definition.request.ParameterConfig;
import com.supretest.api.dto.definition.request.assertions.MsAssertions;
import com.supretest.api.dto.definition.request.auth.MsAuthManager;
import com.supretest.api.dto.definition.request.dns.MsDNSCacheManager;
import com.supretest.base.domain.UiElement;
import com.supretest.commons.constants.MsTestElementConstants;
import com.supretest.ui.parse.UiStepToScript;
import com.supretest.ui.service.UiElementService;
import io.metersphere.plugin.core.MsParameter;
import io.metersphere.plugin.core.MsTestElement;
import io.metersphere.utils.LoggerUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.collections.HashTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Data
@EqualsAndHashCode(callSuper = true)
@JSONType(typeName = "com.googlecode.jmeter.plugins.webdriver.sampler.WebDriverSampler")
public class StWebDriverSampler extends MsTestElement {


    private String type = "com.googlecode.jmeter.plugins.webdriver.sampler.WebDriverSampler";
    private String clazzName = StWebDriverSampler.class.getCanonicalName();

    @JSONField(ordinal = 20)
    private String script;

    @JSONField(ordinal = 21)
    private List<String> parameters;

    @JSONField(ordinal = 22)
    private String scriptLanguage;

    @JSONField(ordinal = 23)
    private String useEnvironment;

    @JSONField(ordinal = 24)
    private Boolean customizeReq;

//    @JSONField(ordinal = 25)
//    private String script;

    @JSONField(ordinal = 26)
    private Integer parentIndex;

    @JSONField(ordinal = 27)
    private Boolean checkBox;

    @JSONField(ordinal = 28)
    private Boolean isBatchProcess;

    @JSONField(ordinal = 29)
    private Boolean isLeaf;

    @JSONField(ordinal = 30)
    private Boolean debug;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JSONField(ordinal = 31)
    private Map<String,Object> step;

    @Resource
    private UiStepToScript uiStepToScript;

    @Resource
    private UiElementService uiElementService;

    public void toHashTree(HashTree tree, List<MsTestElement> hashTree, MsParameter msParameter) {
        ParameterConfig config = (ParameterConfig) msParameter;
        if (StringUtils.isEmpty(this.getEnvironmentId())) {
            this.setEnvironmentId(this.useEnvironment);
        }
        // 非导出操作，且不是启用状态则跳过执行Ms
        if (!config.isOperating() && !this.isEnable()) {
            return;
        }else if(config.isOperating() && StringUtils.isNotEmpty(config.getOperatingSampleTestName())){
            this.setName(config.getOperatingSampleTestName());
        }


        WebDriverSampler sampler = new WebDriverSampler();
        sampler.setEnabled(this.isEnable());
        sampler.setName(this.getName());
        if (StringUtils.isEmpty(this.getName())) {
            sampler.setName("WebDriverSampler");
        }
        if (config.isOperating()) {
            String[] testNameArr = sampler.getName().split("<->");
            if (testNameArr.length > 0) {
                String testName = testNameArr[0];
                sampler.setName(testName);
            }
        }
        sampler.setProperty(TestElement.TEST_CLASS, WebDriverSampler.class.getName());
        sampler.setProperty(TestElement.GUI_CLASS, SaveService.aliasToClass("com.googlecode.jmeter.plugins.webdriver.sampler.gui.WebDriverSamplerGui"));
        ElementUtil.setBaseParams(sampler, this.getParent(), config, this.getId(), this.getIndex());

        if (config.getConfig() == null) {
            if (StringUtils.isNotEmpty(config.getProjectId())) {
                this.setProjectId(config.getProjectId());
            }
            String projectId = this.getProjectId();
            config.setConfig(ElementUtil.getEnvironmentConfig(this.useEnvironment, projectId, this.isMockEnvironment()));
            this.setProjectId(projectId);
        }
        sampler.setScript(this.formatUIScript());
        sampler.setScriptLanguage("js");

        new StChromeDriverConfig().toHashTree(tree, hashTree, msParameter);
        final HashTree httpSamplerTree = tree.add(sampler);

        //判断是否要开启DNS
        if (config.isEffective(this.getProjectId()) && config.getConfig().get(this.getProjectId()).getCommonConfig() != null
                && config.getConfig().get(this.getProjectId()).getCommonConfig().isEnableHost()) {
            MsDNSCacheManager.addEnvironmentVariables(httpSamplerTree, this.getName(), config.getConfig().get(this.getProjectId()));
//            MsDNSCacheManager.addEnvironmentDNS(httpSamplerTree, this.getName(), config.getConfig().get(this.getProjectId()), httpConfig);
        }


        if (CollectionUtils.isNotEmpty(hashTree)) {
            hashTree = ElementUtil.order(hashTree);
            for (MsTestElement el : hashTree) {
                if (el.getEnvironmentId() == null) {
                    if (this.getEnvironmentId() == null) {
                        el.setEnvironmentId(useEnvironment);
                    } else {
                        el.setEnvironmentId(this.getEnvironmentId());
                    }
                }
                el.toHashTree(httpSamplerTree, el.getHashTree(), config);
            }
        }
    }


    public String formatUIScript(){
//
//            System.out.println(stWebDriverSamplers.get(i).toString());
//            StWebDriverSampler stWebDriverSampler = (StWebDriverSampler) stWebDriverSamplers.get(i);
//            HashMap step = (HashMap) stWebDriverSampler.getStep();

//            if(this.step.get("operationType").toString().equals("WebElement")) {
//                UiElement uiElement = uiElementService.getElement((String) step.get("elementId"));
////                    uiElementService.getLocationType((String)uiElement.getLocationType());
//                this.step.put("locationType", uiElementService.getLocationTypeById(uiElement.getLocationType()));
//                this.step.put("location", uiElement.getLocation());
//            } else if (this.step.get("operationType").toString().equals("browser")) {
//                this.step.put("locationType", "browser");
//            } else if (this.step.get("operationType").toString().equals("JavascriptExecutor")) {
//                this.step.put("locationType", "JavascriptExecutor");
//            }
            return new UiStepToScript().stepToScript((HashMap) this.step);

        }


}
