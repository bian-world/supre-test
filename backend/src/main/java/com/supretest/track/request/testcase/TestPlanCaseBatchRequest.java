package com.supretest.track.request.testcase;

import com.supretest.base.domain.TestPlanTestCase;
import com.supretest.track.request.testplancase.TestPlanFuncCaseConditions;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestPlanCaseBatchRequest extends TestPlanTestCase {
    private List<String> ids;
    private TestPlanFuncCaseConditions condition;
}
