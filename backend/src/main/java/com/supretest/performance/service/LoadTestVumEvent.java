package com.supretest.performance.service;

import com.supretest.base.domain.*;
import com.supretest.base.mapper.LoadTestReportMapper;
import com.supretest.base.mapper.ProjectMapper;
import com.supretest.base.mapper.ext.ExtLoadTestReportMapper;
import com.supretest.commons.constants.PerformanceTestStatus;
import com.supretest.commons.constants.ReportKeys;
import com.supretest.commons.consumer.LoadTestFinishEvent;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.commons.utils.LogUtil;
import com.supretest.performance.base.VumProcessedStatus;
import com.supretest.service.QuotaService;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author lyh
 */

@Component
public class LoadTestVumEvent implements LoadTestFinishEvent {
    @Resource
    private LoadTestReportMapper loadTestReportMapper;
    @Resource
    private ExtLoadTestReportMapper extLoadTestReportMapper;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private ProjectMapper projectMapper;

    private void handleVum(LoadTestReport report) {
        if (report == null) {
            return;
        }

        LoadTestReportWithBLOBs testReport = loadTestReportMapper.selectByPrimaryKey(report.getId());
        if (testReport == null) {
            return;
        }

        int bl = extLoadTestReportMapper.updateReportVumStatus(report.getId(),
                ReportKeys.VumProcessedStatus.name(),
                VumProcessedStatus.PROCESSED,
                VumProcessedStatus.NOT_PROCESSED);

        // 防止重复处理
        if (bl == 0) {
            return;
        }

        QuotaService quotaService = CommonBeanFactory.getBean(QuotaService.class);
        String projectId = report.getProjectId();
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (project == null || StringUtils.isBlank(project.getWorkspaceId())) {
            MSException.throwException("project is null or workspace_id of project is null. project id: " + projectId);
        }
        RLock lock = redissonClient.getLock(project.getWorkspaceId());
        if (quotaService != null) {
            try {
                lock.lock();
                BigDecimal toReduceVum = quotaService.getReduceVumUsed(testReport);
                if (toReduceVum.compareTo(BigDecimal.ZERO) != 0) {
                    quotaService.updateVumUsed(projectId, toReduceVum.negate());
                }
            } finally {
                lock.unlock();
            }
        } else {
            LogUtil.error("handle vum event get quota service bean is null. load test report id: " + report.getId());
        }
    }

    @Override
    public void execute(LoadTestReport report) {
        if (PerformanceTestStatus.Error.name().equals(report.getStatus())) {
            // 失败后回退vum数量
            this.handleVum(report);
        }
    }
}
