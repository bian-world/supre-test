package com.supretest.track.request.testcase;

import com.supretest.api.dto.definition.ApiTestCaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author song.tianyang
 * @Date 2021/4/6 5:32 下午
 * @Description
 */
@Getter
@Setter
public class ApiCaseBatchCondition extends ApiTestCaseRequest {
    private boolean selectAll;
    private List<String> unSelectIds;
}
