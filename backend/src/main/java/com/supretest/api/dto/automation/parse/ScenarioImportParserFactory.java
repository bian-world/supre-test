package com.supretest.api.dto.automation.parse;

import com.supretest.api.parse.ApiImportParser;
import com.supretest.commons.constants.ApiImportPlatform;
import org.apache.commons.lang3.StringUtils;

public class ScenarioImportParserFactory {
    public static ApiImportParser getImportParser(String platform) {
        if (StringUtils.equals(ApiImportPlatform.Supretest.name(), platform)) {
            return new MsScenarioParser();
        } else if (StringUtils.equals(ApiImportPlatform.Postman.name(), platform)) {
            return new PostmanScenarioParser();
        } else if (StringUtils.equals(ApiImportPlatform.Jmeter.name(), platform)) {
            return new MsJmeterParser();
        } else if (StringUtils.equals(ApiImportPlatform.Har.name(), platform)) {
            return new HarScenarioParser();
        }
        return null;
    }
}
