package com.supretest.job.sechedule;

import com.supretest.api.dto.SaveAPITestRequest;
import com.supretest.api.service.APITestService;
import com.supretest.commons.constants.ReportTriggerMode;
import com.supretest.commons.constants.ScheduleGroup;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.service.ProjectService;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class ProjectWeekPlanProgressJob extends MsScheduleJob {

    private ProjectService projectService;
    public ProjectWeekPlanProgressJob() {
        projectService = CommonBeanFactory.getBean(ProjectService.class);
    }

    @Override
    void businessExecute(JobExecutionContext context) {
//        String s;
        projectService.insertProjectWeekPlanProgressesList();
    }

    public static JobKey getJobKey(String name) {
        return new JobKey(name, ScheduleGroup.PROJECT_PLAN_PROGRESS.name());
    }

    public static TriggerKey getTriggerKey(String name) {
        return new TriggerKey(name, ScheduleGroup.PROJECT_PLAN_PROGRESS.name());
    }
}

