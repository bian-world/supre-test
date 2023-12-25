package com.supretest.api.dto.datacount;

import com.supretest.track.dto.TestPlanDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 已执行的案例
 */
@Getter
@Setter
public class ExecutedCaseInfoResult {
    private String testCaseID;
    private String id;
    //案例名称
    private String caseName;
    //所属测试计划
    private String testPlan;
    //失败次数
    private Long failureTimes;
    //案例类型
    private String caseType;
    private List<TestPlanDTO> testPlanDTOList;
}
