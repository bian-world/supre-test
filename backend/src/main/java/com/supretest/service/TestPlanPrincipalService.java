package com.supretest.service;

import com.supretest.base.domain.TestPlanPrincipal;
import com.supretest.base.domain.TestPlanPrincipalExample;
import com.supretest.base.mapper.TestPlanPrincipalMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestPlanPrincipalService {

    @Resource
    private TestPlanPrincipalMapper testPlanPrincipalMapper;


    public void deleteTestPlanPrincipalByPlanId(String planId) {
        if (StringUtils.isBlank(planId)) {
            return;
        }
        TestPlanPrincipalExample example = new TestPlanPrincipalExample();
        example.createCriteria().andTestPlanIdEqualTo(planId);
        testPlanPrincipalMapper.deleteByExample(example);
    }

    public TestPlanPrincipal insert(TestPlanPrincipal testPlanPrincipal) {
        testPlanPrincipalMapper.insert(testPlanPrincipal);
        return testPlanPrincipal;
    }
}
