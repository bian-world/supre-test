package com.supretest.api.dto.scenario.extract;

import lombok.Data;

@Data
public class ExtractType {
    public final static String REGEX = "Regex";
    public final static String JSON_PATH = "JSONPath";
    public final static String XPATH = "XPath";

    private String type;
}
