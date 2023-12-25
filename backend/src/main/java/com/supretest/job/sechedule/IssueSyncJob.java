package com.supretest.job.sechedule;

import com.supretest.commons.constants.ScheduleGroup;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.track.service.IssuesService;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class IssueSyncJob extends MsScheduleJob {

    private IssuesService issuesService;

    public IssueSyncJob() {
        issuesService = CommonBeanFactory.getBean(IssuesService.class);
    }

    @Override
    void businessExecute(JobExecutionContext context) {
        issuesService.syncThirdPartyIssues();
    }

    public static JobKey getJobKey(String projectId) {
        return new JobKey(projectId, ScheduleGroup.ISSUE_SYNC.name());
    }

    public static TriggerKey getTriggerKey(String projectId) {
        return new TriggerKey(projectId, ScheduleGroup.ISSUE_SYNC.name());
    }
}

