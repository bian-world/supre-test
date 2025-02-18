package com.supretest.api.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.dto.ApiTestEnvironmentDTO;
import com.supretest.api.dto.ssl.KeyStoreEntry;
import com.supretest.api.service.ApiTestEnvironmentService;
import com.supretest.api.service.CommandService;
import com.supretest.base.domain.ApiTestEnvironmentWithBLOBs;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.controller.request.EnvironmentRequest;
import com.supretest.log.annotation.MsAuditLog;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/environment")
public class ApiTestEnvironmentController {

    @Resource
    ApiTestEnvironmentService apiTestEnvironmentService;
    @Resource
    private CommandService commandService;

    @GetMapping("/list/{projectId}")
    public List<ApiTestEnvironmentWithBLOBs> list(@PathVariable String projectId) {
        return apiTestEnvironmentService.list(projectId);
    }

    /**
     * 查询指定项目和指定名称的环境
     *
     * @param goPage
     * @param pageSize
     * @param environmentRequest
     * @return
     */
    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<ApiTestEnvironmentWithBLOBs>> listByCondition(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody EnvironmentRequest environmentRequest) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, apiTestEnvironmentService.listByConditions(environmentRequest));
    }

    @GetMapping("/get/{id}")
    public ApiTestEnvironmentWithBLOBs get(@PathVariable String id) {
        return apiTestEnvironmentService.get(id);
    }


    @PostMapping(value = "/get/entry")
    public List<KeyStoreEntry> getEntry(@RequestPart("request") String password, @RequestPart(value = "file") MultipartFile sslFiles) {
        return commandService.get(password, sslFiles);
    }

    @PostMapping("/add")
    @MsAuditLog(module = OperLogModule.PROJECT_ENVIRONMENT_SETTING, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#apiTestEnvironmentWithBLOBs.id)", msClass = ApiTestEnvironmentService.class)
    public String create(@RequestPart("request") ApiTestEnvironmentDTO apiTestEnvironmentWithBLOBs, @RequestPart(value = "files", required = false) List<MultipartFile> sslFiles) {
        return apiTestEnvironmentService.add(apiTestEnvironmentWithBLOBs, sslFiles);
    }

    @PostMapping(value = "/update")
    @MsAuditLog(module = OperLogModule.PROJECT_ENVIRONMENT_SETTING, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#apiTestEnvironment.id)", content = "#msClass.getLogDetails(#apiTestEnvironment.id)", msClass = ApiTestEnvironmentService.class)
    public void update(@RequestPart("request") ApiTestEnvironmentDTO apiTestEnvironment, @RequestPart(value = "files", required = false) List<MultipartFile> sslFiles) {
        apiTestEnvironmentService.update(apiTestEnvironment, sslFiles);
    }

    @GetMapping("/delete/{id}")
    @MsAuditLog(module = OperLogModule.PROJECT_ENVIRONMENT_SETTING, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#id)", msClass = ApiTestEnvironmentService.class)
    public void delete(@PathVariable String id) {
        apiTestEnvironmentService.delete(id);
    }

    @GetMapping("/getTcpMockInfo/{projectId}")
    public String getMockInfo(@PathVariable(value = "projectId") String projectId) {
        return apiTestEnvironmentService.getMockInfo(projectId);
    }
}
