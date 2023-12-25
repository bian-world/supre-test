package com.supretest.controller;

import com.supretest.base.domain.ServiceIntegration;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.controller.request.IntegrationRequest;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.IntegrationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("service/integration")
@RestController
public class ServiceIntegrationController {

    @Resource
    private IntegrationService integrationService;

    @PostMapping("/save")
    @MsAuditLog(module = OperLogModule.WORKSPACE_SERVICE_INTEGRATION, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#service.id)", msClass = IntegrationService.class)
    public ServiceIntegration save(@RequestBody ServiceIntegration service) {
        return integrationService.save(service);
    }

    @PostMapping("/type")
    public ServiceIntegration getByPlatform(@RequestBody IntegrationRequest request) {
        return integrationService.get(request);
    }

    @PostMapping("/delete")
    @MsAuditLog(module = OperLogModule.WORKSPACE_SERVICE_INTEGRATION, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#request.id)", msClass = IntegrationService.class)
    public void delete(@RequestBody IntegrationRequest request) {
        integrationService.delete(request);
    }

    @GetMapping("/all/{workspaceId}")
    public List<ServiceIntegration> getAll(@PathVariable String workspaceId) {
        return integrationService.getAll(workspaceId);
    }

}
