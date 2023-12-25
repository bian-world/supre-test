package com.supretest.api.dto.definition.request.sampler;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.googlecode.jmeter.plugins.webdriver.config.ChromeDriverConfig;
import com.googlecode.jmeter.plugins.webdriver.proxy.ProxyType;
import com.supretest.api.dto.definition.request.ElementUtil;
import com.supretest.api.dto.definition.request.ParameterConfig;
import com.supretest.commons.utils.HtmlTransferUtils;
import io.metersphere.plugin.core.MsParameter;
import io.metersphere.plugin.core.MsTestElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.collections.HashTree;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@EqualsAndHashCode(callSuper = true)
@JSONType(typeName = "com.googlecode.jmeter.plugins.webdriver.config.ChromeDriverConfig")
public class StChromeDriverConfig extends StWebDriverConfig{

    private String type = "com.googlecode.jmeter.plugins.webdriver.config.ChromeDriverConfig";
    private String clazzName = StWebDriverConfig.class.getCanonicalName();

    @JSONField(ordinal = 20)
    private static String chromedriverPath;

    @Value("${chrome.driver_path}")
    public void setChromeDriverPath(String path){
        StChromeDriverConfig.chromedriverPath = path;
    }

    public void toHashTree(HashTree tree, List<MsTestElement> hashTree, MsParameter msParameter) {
        ParameterConfig config = (ParameterConfig) msParameter;
        ChromeDriverConfig driverConfig = new ChromeDriverConfig();
        driverConfig.setEnabled(this.isEnable());
        driverConfig.setName(this.getName());
        if (StringUtils.isEmpty(this.getName())) {
            driverConfig.setName("StWebDriverConfig");
        }

        driverConfig.setProperty(TestElement.TEST_CLASS, ChromeDriverConfig.class.getName());
        driverConfig.setProperty(TestElement.GUI_CLASS, SaveService.aliasToClass("com.googlecode.jmeter.plugins.webdriver.config.gui.ChromeDriverConfigGui"));
        ElementUtil.setBaseParams(driverConfig, this.getParent(), config, this.getId(), this.getIndex());
        driverConfig.setChromeDriverPath(chromedriverPath);
        driverConfig.setHeadlessEnabled(false);
        driverConfig.setAdditionalArgs("--incognito");  // 开启无痕模式
        driverConfig.setProxyType(ProxyType.SYSTEM);
        driverConfig.setUseHttpSettingsForAllProtocols(Boolean.TRUE);
        driverConfig.setNoSandboxEnabled(true);
        tree.add(driverConfig);
    }
}
