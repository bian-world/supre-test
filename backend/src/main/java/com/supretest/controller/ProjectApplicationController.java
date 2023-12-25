package com.supretest.controller;

import com.supretest.base.domain.ProjectApplication;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.controller.request.ProjectApplicationRequest;
import com.supretest.dto.ProjectConfig;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.ProjectApplicationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/project_application")
public class ProjectApplicationController {
    @Resource
    private ProjectApplicationService projectApplicationService;

    @PostMapping("/update")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MANAGER, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#projectApplication)", content = "#msClass.getLogDetails(#projectApplication)", msClass = ProjectApplicationService.class)
    public void updateProject(@RequestBody ProjectApplication projectApplication) {
        projectApplicationService.updateProjectApplication(projectApplication);
    }

    @PostMapping("/update/batch")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MANAGER, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#projectApplication)", content = "#msClass.getLogDetails(#projectApplication)", msClass = ProjectApplicationService.class)
    public void updateProjectConfigBatch(@RequestBody ProjectApplicationRequest request) {
        projectApplicationService.updateProjectConfigBatch(request);
    }

    @GetMapping("/get/{projectId}/{type}")
    public ProjectApplication getProjectApplication(@PathVariable String projectId,@PathVariable String type) {
        return projectApplicationService.getProjectApplication(projectId,type);
    }

    @GetMapping("/get/config/{projectId}")
    public ProjectConfig getProjectConfig(@PathVariable String projectId) {
        return projectApplicationService.getProjectConfig(projectId);
    }

    @GetMapping("/get/config/{projectId}/{type}")
    public ProjectConfig getProjectConfigByType(@PathVariable String projectId, @PathVariable String type) {
        return projectApplicationService.getSpecificTypeValue(projectId, type);
    }
}
