package com.supretest.controller;

import com.supretest.base.domain.CustomField;
import com.supretest.base.domain.CustomFieldTemplate;
import com.supretest.dto.CustomFieldTemplateDao;
import com.supretest.service.CustomFieldTemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("custom/field/template")
public class CustomFieldTemplateController {

    @Resource
    CustomFieldTemplateService customFieldTemplateService;

    @PostMapping("/list")
    public List<CustomFieldTemplateDao> list(@RequestBody CustomFieldTemplate request) {
        return customFieldTemplateService.list(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody CustomFieldTemplate request) {
        customFieldTemplateService.update(request);
    }

    @GetMapping("/{id}")
    public CustomField get(@PathVariable String id) {
        return customFieldTemplateService.getCustomField(id);
    }

}
