package com.supretest.api.dto.scenario.environment;

import com.alibaba.fastjson.JSONObject;
import com.supretest.api.dto.definition.request.assertions.MsAssertions;
import com.supretest.api.dto.definition.request.processors.MsJSR223Processor;
import com.supretest.api.dto.definition.request.processors.post.MsJSR223PostProcessor;
import com.supretest.api.dto.definition.request.processors.pre.MsJSR223PreProcessor;
import com.supretest.api.dto.scenario.DatabaseConfig;
import com.supretest.api.dto.scenario.HttpConfig;
import com.supretest.api.dto.scenario.TCPConfig;
import com.supretest.api.dto.ssl.KeyStoreConfig;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EnvironmentConfig {
    private String apiEnvironmentid;
    private CommonConfig commonConfig;
    private HttpConfig httpConfig;
    private List<DatabaseConfig> databaseConfigs;
    private TCPConfig tcpConfig;
    private KeyStoreConfig sslConfig;
    //全局前后置脚本（每个请求都跑一遍）
    private MsJSR223PreProcessor preProcessor;
    private MsJSR223PostProcessor postProcessor;
    //全局前后置脚本步骤（只在全部步骤都前后做处理）
    private MsJSR223Processor preStepProcessor;
    private MsJSR223Processor postStepProcessor;
    //全局前后置脚本都配置
    private GlobalScriptConfig globalScriptConfig;
    private JSONObject authManager;
    private List<MsAssertions> assertions;
    private boolean useErrorCode;
    private boolean higherThanSuccess;
    private boolean higherThanError;

    public EnvironmentConfig() {
        this.commonConfig = new CommonConfig();
        this.httpConfig = new HttpConfig();
        this.databaseConfigs = new ArrayList<>();
        this.tcpConfig = new TCPConfig();
        this.sslConfig = new KeyStoreConfig();
    }
}
