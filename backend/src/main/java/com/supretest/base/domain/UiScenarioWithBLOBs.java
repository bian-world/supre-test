package com.supretest.base.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UiScenarioWithBLOBs extends UiScenario implements Serializable {
    private String scenarioDefinition;

    private String description;

    private String useUrl;

    private String environmentJson;

    private static final long serialVersionUID = 1L;
}