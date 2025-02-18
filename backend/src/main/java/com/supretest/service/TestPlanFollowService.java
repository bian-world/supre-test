package com.supretest.service;

import com.supretest.base.domain.TestPlanFollow;
import com.supretest.base.domain.TestPlanFollowExample;
import com.supretest.base.mapper.TestPlanFollowMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestPlanFollowService {

    @Resource
    private TestPlanFollowMapper testPlanFollowMapper;


    public void deleteTestPlanFollowByPlanId(String planId) {
        if (StringUtils.isBlank(planId)) {
            return;
        }
        TestPlanFollowExample example = new TestPlanFollowExample();
        example.createCriteria().andTestPlanIdEqualTo(planId);
        testPlanFollowMapper.deleteByExample(example);
    }

    public TestPlanFollow insert(TestPlanFollow testPlanFollow) {
        testPlanFollowMapper.insert(testPlanFollow);
        return testPlanFollow;
    }
}
