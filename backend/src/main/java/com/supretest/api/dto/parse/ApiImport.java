package com.supretest.api.dto.parse;

import com.supretest.api.dto.scenario.Scenario;
import lombok.Data;

import java.util.List;

@Data
public class ApiImport {
    private List<Scenario> scenarios;
}
