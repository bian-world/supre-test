package com.supretest.track.service;

import com.alibaba.fastjson.JSON;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.TestCaseReportMapper;
import com.supretest.base.mapper.TestCaseReportTemplateMapper;
import com.supretest.base.mapper.TestPlanMapper;
import com.supretest.base.mapper.ext.ExtTestPlanMapper;
import com.supretest.commons.utils.BeanUtils;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.log.utils.ReflexObjectUtil;
import com.supretest.log.vo.DetailColumn;
import com.supretest.log.vo.OperatingLogDetails;
import com.supretest.log.vo.track.TestPlanReference;
import com.supretest.track.request.testCaseReport.CreateReportRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestCaseReportService {

    @Resource
    TestCaseReportMapper testCaseReportMapper;

    @Resource
    TestPlanMapper testPlanMapper;

    @Resource
    ExtTestPlanMapper extTestPlanMapper;

    @Resource
    TestCaseReportTemplateMapper testCaseReportTemplateMapper;

    public List<TestCaseReport> listTestCaseReport(TestCaseReport request) {
        TestCaseReportExample example = new TestCaseReportExample();
        if (StringUtils.isNotBlank(request.getName())) {
            example.createCriteria().andNameEqualTo(request.getName());
        }
        return testCaseReportMapper.selectByExample(example);
    }

    public TestCaseReport getTestCaseReport(String id) {
        return testCaseReportMapper.selectByPrimaryKey(id);
    }

    public void addTestCaseReport(TestCaseReport testCaseReport) {
        testCaseReport.setId(UUID.randomUUID().toString());
        testCaseReportMapper.insert(testCaseReport);
    }

    public void editTestCaseReport(TestCaseReport TestCaseReport) {
        testCaseReportMapper.updateByPrimaryKeyWithBLOBs(TestCaseReport);
    }

    public int deleteTestCaseReport(String id) {
        return testCaseReportMapper.deleteByPrimaryKey(id);
    }

    public String addTestCaseReportByTemplateId(CreateReportRequest request) {
        TestCaseReportTemplate template = testCaseReportTemplateMapper.selectByPrimaryKey(request.getTemplateId());
        TestCaseReport report = new TestCaseReport();
        BeanUtils.copyBean(report, template);
        TestPlanWithBLOBs testPlan = testPlanMapper.selectByPrimaryKey(request.getPlanId());
        report.setName(testPlan.getName());
        report.setId(request.getId());
        report.setCreateUser(SessionUtils.getUserId());
        testCaseReportMapper.insert(report);
        testPlan.setReportId(report.getId());
        testPlanMapper.updateByPrimaryKeySelective(testPlan);
        return report.getId();
    }

    public String getLogDetails(String id) {
        TestCaseReport report = testCaseReportMapper.selectByPrimaryKey(id);
        if (report != null) {
            List<DetailColumn> columns = ReflexObjectUtil.getColumns(report, TestPlanReference.reportColumns);
            OperatingLogDetails details = new OperatingLogDetails(JSON.toJSONString(report.getId()), null, report.getName(), report.getCreateUser(), columns);
            return JSON.toJSONString(details);
        }
        return null;
    }
}
