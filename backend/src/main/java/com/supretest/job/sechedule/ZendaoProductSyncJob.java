package com.supretest.job.sechedule;

import com.supretest.commons.constants.ScheduleGroup;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.track.issue.ZentaoPlatform;
import com.supretest.track.service.IssuesService;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class ZendaoProductSyncJob extends MsScheduleJob {


        private ZentaoPlatform zentaoPlatform;

        public ZendaoProductSyncJob() {
            zentaoPlatform = new ZentaoPlatform();
        }

        @Override
        void businessExecute(JobExecutionContext context) {
            zentaoPlatform.syncProducts();
        }

        public static JobKey getJobKey(String projectId) {
            return new JobKey(projectId, ScheduleGroup.ZENDAO_SYNC.name());
        }

        public static TriggerKey getTriggerKey(String projectId) {
            return new TriggerKey(projectId, ScheduleGroup.ZENDAO_SYNC.name());
        }

}
