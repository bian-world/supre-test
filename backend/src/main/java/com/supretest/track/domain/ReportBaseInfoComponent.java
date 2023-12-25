package com.supretest.track.domain;

import com.supretest.base.domain.TestPlanPrincipal;
import com.supretest.base.domain.TestPlanPrincipalExample;
import com.supretest.base.mapper.TestPlanPrincipalMapper;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.commons.utils.ServiceUtils;
import com.supretest.track.dto.TestCaseReportMetricDTO;
import com.supretest.track.dto.TestPlanCaseDTO;
import com.supretest.track.dto.TestPlanDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ReportBaseInfoComponent extends ReportComponent {
    private Set<String> executorsSet = new HashSet<>();

    public ReportBaseInfoComponent(TestPlanDTO testPlan) {
        super(testPlan);
        componentId = "1";
    }

    @Override
    public void readRecord(TestPlanCaseDTO testCase) {
        executorsSet.add(testCase.getExecutor());
    }

    @Override
    public void afterBuild(TestCaseReportMetricDTO testCaseReportMetric) {
        testCaseReportMetric.setProjectName(testPlan.getProjectName());
        List<String> principalIds = new ArrayList<>();
        if (StringUtils.isNotBlank(testPlan.getId())) {
            TestPlanPrincipalMapper testPlanPrincipalMapper = CommonBeanFactory.getBean(TestPlanPrincipalMapper.class);
            TestPlanPrincipalExample example = new TestPlanPrincipalExample();
            example.createCriteria().andTestPlanIdEqualTo(testPlan.getId());
            if (testPlanPrincipalMapper != null) {
                List<TestPlanPrincipal> principals = testPlanPrincipalMapper.selectByExample(example);
                principalIds = principals.stream().map(TestPlanPrincipal::getPrincipalId).collect(Collectors.toList());
            }
        }
        testCaseReportMetric.setExecutors(new ArrayList<>(this.executorsSet));
        List<String> userIds = new ArrayList<>();
        userIds.addAll(principalIds);
        userIds.addAll(testCaseReportMetric.getExecutors());
        Map<String, String> userMap = ServiceUtils.getUserNameMap(userIds);
        String principalName = "";
        for (String principalId : principalIds) {
            String name = userMap.get(principalId);
            if (StringUtils.isNotBlank(principalName)) {
                principalName = principalName + "、" +name;
            } else {
                principalName = principalName + name;
            }
        }

        testCaseReportMetric.setPrincipalName(principalName);
        List<String> names = new ArrayList<>();
        testCaseReportMetric.getExecutors().forEach(item -> {
            if (StringUtils.isNotBlank(item)) {
                names.add(userMap.get(item));
            }
        });
        testCaseReportMetric.setExecutorNames(names);
    }
}
