package com.supretest.service;

import com.supretest.api.dto.automation.ApiScenarioRequest;
import com.supretest.api.dto.definition.ApiTestCaseRequest;
import com.supretest.base.mapper.ext.ExtApiScenarioMapper;
import com.supretest.base.mapper.ext.ExtApiTestCaseMapper;
import com.supretest.base.mapper.ext.ExtLoadTestMapper;
import com.supretest.base.mapper.ext.ExtTestCaseMapper;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.performance.request.QueryTestPlanRequest;
import com.supretest.track.request.testcase.QueryTestCaseRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkstationService {

    @Resource
    private ExtApiScenarioMapper extApiScenarioMapper;
    @Resource
    private ExtApiTestCaseMapper extApiTestCaseMapper;
    @Resource
    private ExtTestCaseMapper extTestCaseMapper;
    @Resource
    private ExtLoadTestMapper extLoadTestMapper;


    public Map<String, Integer> getMyCreatedCaseGroupContMap() {
        String userId = SessionUtils.getUserId();
        //build query condition object
        QueryTestPlanRequest testPlanRequest = new QueryTestPlanRequest();
        testPlanRequest.setUserId(userId);
        testPlanRequest.setWorkspaceId(SessionUtils.getCurrentWorkspaceId());
        ApiTestCaseRequest apiTestCaseRequest = new ApiTestCaseRequest();
        apiTestCaseRequest.setWorkspaceId(SessionUtils.getCurrentWorkspaceId());
        //@see com/supretest/base/mapper/ext/ExtApiTestCaseMapper.xml:103
        Map<String, Object> combine = new HashMap<>(2);
        Map<String,String>operatorValue = new HashMap<>(2);
        operatorValue.put("operator","current user");
        operatorValue.put("value","current user");
        combine.put("creator",operatorValue);
        testPlanRequest.setCombine(combine);
        apiTestCaseRequest.setCombine(combine);
        ApiScenarioRequest apiScenarioRequest = new ApiScenarioRequest();
        apiScenarioRequest.setWorkspaceId(SessionUtils.getCurrentWorkspaceId());
        apiScenarioRequest.setCombine(combine);
        QueryTestCaseRequest testCaseRequest = new QueryTestCaseRequest();
        testCaseRequest.setWorkspaceId(SessionUtils.getCurrentWorkspaceId());
        testCaseRequest.setCombine(combine);
        //query db
        int apiScenarioCaseCount = extApiScenarioMapper.listModule(apiScenarioRequest);
        int apiTestCaseCount = extApiTestCaseMapper.moduleCount(apiTestCaseRequest);
        int testCaseCount = extTestCaseMapper.moduleCount(testCaseRequest);
        int loadTestCount = extLoadTestMapper.moduleCount(testPlanRequest);
        //build result
        Map<String, Integer>map = new HashMap<>(4);
        map.put("apiScenarioCaseCount",apiScenarioCaseCount);
        map.put("apiTestCaseCount",apiTestCaseCount);
        map.put("testCaseCount",testCaseCount);
        map.put("loadTestCount",loadTestCount);
        return map;

    }
}
