package com.supretest.api.dto.definition.request.sampler;

import com.alibaba.fastjson.annotation.JSONField;

import io.metersphere.plugin.core.MsTestElement;


public class StWebDriverConfig extends MsTestElement  {

    private String type = "com.googlecode.jmeter.plugins.webdriver.config.ChromeDriverConfig";
    private String clazzName = StWebDriverConfig.class.getCanonicalName();

    @JSONField(ordinal = 20, defaultValue = "SYSTEM")
    private String proxyType;

    @JSONField(ordinal = 21)
    private String proxyPacUrl;

    @JSONField(ordinal = 22)
    private String httpHost;

    @JSONField(ordinal = 23, defaultValue = "8080")
    private String httpPort;

    @JSONField(ordinal = 24, defaultValue = "true")
    private Boolean useHttpForAllProtocols;

    @JSONField(ordinal = 25)
    private String httpsHost;

    @JSONField(ordinal = 26, defaultValue = "8080")
    private String httpsPort;

    @JSONField(ordinal = 27)
    private String ftpHost;

    @JSONField(ordinal = 28, defaultValue = "8080")
    private String ftpPort;

    @JSONField(ordinal = 29)
    private String socksHost;

    @JSONField(ordinal = 30, defaultValue = "8080")
    private String socksPort;

    @JSONField(ordinal = 31, defaultValue = "localhost")
    private String noProxy;

    @JSONField(ordinal = 32, defaultValue = "true")
    private String maximizeBrowser;

    @JSONField(ordinal = 33, defaultValue = "false")
    private Boolean reset_per_iteration;

    @JSONField(ordinal = 34, defaultValue = "false")
    private Boolean dev_mode;

    @JSONField(ordinal = 35, defaultValue = "false")
    private Boolean androidEnabled;

    @JSONField(ordinal = 36, defaultValue = "false")
    private Boolean headlessEnabled;

    @JSONField(ordinal = 37, defaultValue = "false")
    private Boolean insecurecertsEnabled;

    @JSONField(ordinal = 38, defaultValue = "false")
    private Boolean incognitoEnabled;

    @JSONField(ordinal = 39, defaultValue = "false")
    private Boolean noSandboxEnabled;

}

