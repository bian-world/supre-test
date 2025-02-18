package com.supretest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.base.domain.CustomField;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.controller.request.QueryCustomFieldRequest;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.CustomFieldService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("custom/field")
@RestController

public class CustomFieldController {

    @Resource
    private CustomFieldService customFieldService;

    @PostMapping("/add")
    @MsAuditLog(module = OperLogModule.WORKSPACE_TEMPLATE_SETTINGS_FIELD, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#customField.id)", msClass = CustomFieldService.class)
    public String add(@RequestBody CustomField customField) {
        return customFieldService.add(customField);
    }

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<CustomField>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody QueryCustomFieldRequest request) {
        Page<List<CustomField>> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, customFieldService.list(request));
    }

    @PostMapping("/list/relate/{goPage}/{pageSize}")
    public Pager<List<CustomField>> listRelate(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody QueryCustomFieldRequest request) {
        return customFieldService.listRelate(goPage, pageSize, request);
    }

    @GetMapping("/delete/{id}")
    @MsAuditLog(module = OperLogModule.WORKSPACE_TEMPLATE_SETTINGS_FIELD, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#id)", msClass = CustomFieldService.class)
    public void delete(@PathVariable(value = "id") String id) {
        customFieldService.delete(id);
    }

    @PostMapping("/update")
    @MsAuditLog(module = OperLogModule.WORKSPACE_TEMPLATE_SETTINGS_FIELD, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#customField.id)", content = "#msClass.getLogDetails(#customField.id)", msClass = CustomFieldService.class)
    public void update(@RequestBody CustomField customField) {
        customFieldService.update(customField);
    }

    @PostMapping("/list/ids")
    public List<String> list(@RequestBody QueryCustomFieldRequest request) {
        return customFieldService.listIds(request);
    }

    @PostMapping("/list")
    public List<CustomField> getList(@RequestBody QueryCustomFieldRequest request) {
        return customFieldService.list(request);
    }

    @PostMapping("/default")
    public List<CustomField> getDefaultList(@RequestBody QueryCustomFieldRequest request) {
        return customFieldService.getDefaultField(request);
    }

}
