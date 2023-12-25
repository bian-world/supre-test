package com.supretest.track.service;

import com.supretest.api.dto.definition.ApiTestCaseDTO;
import com.supretest.api.dto.definition.ApiTestCaseRequest;
import com.supretest.api.dto.definition.TestPlanApiCaseDTO;
import com.supretest.api.service.ApiDefinitionExecResultService;
import com.supretest.api.service.ApiTestCaseService;
import com.supretest.base.domain.TestCaseReviewApiCase;
import com.supretest.base.domain.TestCaseReviewApiCaseExample;
import com.supretest.base.mapper.TestCaseReviewApiCaseMapper;
import com.supretest.base.mapper.ext.ExtTestCaseReviewApiCaseMapper;
import com.supretest.commons.utils.ServiceUtils;
import com.supretest.track.request.testcase.TestPlanApiCaseBatchRequest;
import com.supretest.track.request.testreview.TestReviewApiCaseBatchRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestCaseReviewApiCaseService {
    @Resource
    private ExtTestCaseReviewApiCaseMapper extTestCaseReviewApiCaseMapper;
    @Resource
    ApiTestCaseService apiTestCaseService;
    @Resource
    TestCaseReviewApiCaseMapper  testCaseReviewApiCaseMapper;
    @Lazy
    @Resource
    ApiDefinitionExecResultService apiDefinitionExecResultService;
    public List<TestPlanApiCaseDTO> list(ApiTestCaseRequest request) {
        request.setProjectId(null);
        request.setOrders(ServiceUtils.getDefaultOrder(request.getOrders()));
        List<TestPlanApiCaseDTO> apiTestCases = extTestCaseReviewApiCaseMapper.list(request);
        if (CollectionUtils.isEmpty(apiTestCases)) {
            return apiTestCases;
        }
        apiTestCaseService.buildUserInfo(apiTestCases, request.isSelectEnvironment());
        return apiTestCases;
    }
    public List<String> getExecResultByReviewId(String reviewId) {
        return extTestCaseReviewApiCaseMapper.getExecResultByReviewId(reviewId);
    }
    public List<ApiTestCaseDTO> relevanceList(ApiTestCaseRequest request) {
        List<String> ids = apiTestCaseService.selectIdsNotExistsInReview(request.getProjectId(), request.getReviewId());
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        request.setIds(ids);
        return apiTestCaseService.listSimple(request);
    }
    public int delete(String id) {
        apiDefinitionExecResultService.deleteByResourceId(id);
        TestCaseReviewApiCaseExample  example=new TestCaseReviewApiCaseExample();
        example.createCriteria()
                .andIdEqualTo(id);

        return  testCaseReviewApiCaseMapper.deleteByExample(example);
    }
    public void deleteApiCaseBath(TestReviewApiCaseBatchRequest request) {
        if (CollectionUtils.isEmpty(request.getIds())) {
            return;
        }
        apiDefinitionExecResultService.deleteByResourceIds(request.getIds());
        TestCaseReviewApiCaseExample example=new TestCaseReviewApiCaseExample();
        example.createCriteria()
                .andIdIn(request.getIds())
                .andTestCaseReviewIdEqualTo(request.getTestCaseReviewId());
        testCaseReviewApiCaseMapper.deleteByExample(example);
    }
    public void batchUpdateEnv(TestPlanApiCaseBatchRequest request) {
        // 批量修改用例环境
        Map<String, String> rows = request.getSelectRows();
        Set<String> ids = rows.keySet();
        Map<String, String> env = request.getProjectEnvMap();
        if (env != null && !env.isEmpty()) {
            ids.forEach(id -> {
                TestCaseReviewApiCase apiCase = new TestCaseReviewApiCase();
                apiCase.setId(id);
                apiCase.setEnvironmentId(env.get(rows.get(id)));
                testCaseReviewApiCaseMapper.updateByPrimaryKeySelective(apiCase);
            });
        }
    }
    public void setExecResult(String id, String status,Long time) {
        TestCaseReviewApiCase apiCase = new TestCaseReviewApiCase();
        apiCase.setId(id);
        apiCase.setStatus(status);
        apiCase.setUpdateTime(time);
        testCaseReviewApiCaseMapper.updateByPrimaryKeySelective(apiCase);
    }
    public void updateByPrimaryKeySelective(TestCaseReviewApiCase apiCase) {
        testCaseReviewApiCaseMapper.updateByPrimaryKeySelective(apiCase);
    }
}
