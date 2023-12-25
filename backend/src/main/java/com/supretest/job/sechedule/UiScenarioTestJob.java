package com.supretest.job.sechedule;

import com.alibaba.fastjson.JSONObject;
import com.supretest.api.dto.automation.ExecuteType;
import com.supretest.api.dto.automation.RunScenarioRequest;
import com.supretest.api.service.ApiAutomationService;
import com.supretest.commons.constants.ApiRunMode;
import com.supretest.commons.constants.ReportTriggerMode;
import com.supretest.commons.constants.ScheduleGroup;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.commons.utils.LogUtil;
import com.supretest.ui.service.UiScenarioService;
import io.metersphere.constants.RunModeConstants;
import io.metersphere.dto.RunModeConfigDTO;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UiScenarioTestJob extends MsScheduleJob {

        private String projectID;

        private List<String> scenarioIds;

        private UiScenarioService uiScenarioService;

        public UiScenarioTestJob() {
            uiScenarioService = CommonBeanFactory.getBean(UiScenarioService.class);
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {

            JobKey jobKey = context.getTrigger().getJobKey();
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            String resourceId = jobDataMap.getString("resourceId");
            this.userId = jobDataMap.getString("userId");
            this.expression = jobDataMap.getString("expression");
            this.projectID = jobDataMap.getString("projectId");
            if (resourceId != null) {
                scenarioIds = new ArrayList<>();
                scenarioIds.add(resourceId);
            }

            LogUtil.info(jobKey.getGroup() + " Running: " + resourceId);
            LogUtil.info("CronExpression: " + expression);
            businessExecute(context);
        }

        @Override
        void businessExecute(JobExecutionContext context) {
            RunScenarioRequest request = new RunScenarioRequest();
            String id = UUID.randomUUID().toString();
            request.setId(id);
            request.setReportId(id);
            request.setProjectId(projectID);
            request.setTriggerMode(ReportTriggerMode.SCHEDULE.name());
            request.setExecuteType(ExecuteType.Saved.name());
            request.setIds(this.scenarioIds);
            request.setReportUserID(this.userId);
            request.setRunMode(ApiRunMode.UI_SCHEDULE_SCENARIO.name());
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            String config = jobDataMap.getString("config");
            if (StringUtils.isNotBlank(config)) {
                RunModeConfigDTO runModeConfig = JSONObject.parseObject(config, RunModeConfigDTO.class);
                request.setConfig(runModeConfig);
            } else {
                RunModeConfigDTO runModeConfigDTO = new RunModeConfigDTO();
                runModeConfigDTO.setMode(RunModeConstants.PARALLEL.toString());
                request.setConfig(runModeConfigDTO);
            }
            LogUtil.info("UI schedule running");
            uiScenarioService.run(request);
        }

        public static JobKey getJobKey(String testId) {
            return new JobKey(testId, ScheduleGroup.UI_SCENARIO_TEST.name());
        }

        public static TriggerKey getTriggerKey(String testId) {
            return new TriggerKey(testId, ScheduleGroup.UI_SCENARIO_TEST.name());
        }
}

