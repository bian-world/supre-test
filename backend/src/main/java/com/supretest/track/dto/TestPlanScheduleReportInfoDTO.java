package com.supretest.track.dto;

import com.supretest.base.domain.TestPlanReport;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author song.tianyang
 * @Date 2021/7/20 11:22 上午
 */
@Getter
@Setter
public class TestPlanScheduleReportInfoDTO {
    private TestPlanReport testPlanReport;
    private Map<String, String> planScenarioIdMap;
    private Map<String, String> apiTestCaseDataMap;
    private Map<String, String> performanceIdMap;
}
