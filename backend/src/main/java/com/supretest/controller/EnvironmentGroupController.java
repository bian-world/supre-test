package com.supretest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.base.domain.EnvironmentGroup;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.controller.request.EnvironmentGroupRequest;
import com.supretest.dto.EnvironmentGroupDTO;
import com.supretest.service.EnvironmentGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lyh
 */

@RequestMapping("/environment/group")
@RestController
public class EnvironmentGroupController {

    @Resource
    private EnvironmentGroupService environmentGroupService;

    @PostMapping("/add")
    public EnvironmentGroup add(@RequestBody EnvironmentGroupRequest request) {
        return environmentGroupService.add(request);
    }

    @PostMapping("/batch/add")
    public void batchAdd(@RequestBody EnvironmentGroupRequest request) {
        environmentGroupService.batchAdd(request);
    }

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<EnvironmentGroup>> get(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody EnvironmentGroupRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        request.setWorkspaceId(SessionUtils.getCurrentWorkspaceId());
        return PageUtils.setPageInfo(page, environmentGroupService.getList(request));
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        environmentGroupService.delete(id);
    }

    @PostMapping("/update")
    public EnvironmentGroup update(@RequestBody EnvironmentGroupRequest request) {
        return environmentGroupService.update(request);
    }

    @PostMapping("/modify")
    public void modify(@RequestBody EnvironmentGroupRequest request) {
        environmentGroupService.modify(request);
    }

    @GetMapping("/get/{id}")
    public List<EnvironmentGroup> getRelateProject(@PathVariable("id") String projectId) {
        return environmentGroupService.getRelateProjectGroup(projectId);
    }

    @PostMapping("/get/all")
    public List<EnvironmentGroup> getAll(@RequestBody EnvironmentGroupRequest request) {
        request.setWorkspaceId(SessionUtils.getCurrentWorkspaceId());
        return environmentGroupService.getList(request);
    }

    @GetMapping("/copy/{id}")
    public void copy(@PathVariable String id) {
        environmentGroupService.copy(id);
    }

    @PostMapping("/get/option")
    public List<EnvironmentGroupDTO> getEnvOptionGroup(@RequestBody EnvironmentGroupRequest request) {
        return environmentGroupService.getEnvOptionGroup(request.getProjectIds());
    }
}
