package com.supretest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.base.domain.JarConfig;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.JarConfigService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/jar")
public class JarConfigController {

    @Resource
    JarConfigService JarConfigService;

    @PostMapping("list/{goPage}/{pageSize}")
    public Pager<List<JarConfig>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody JarConfig request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, JarConfigService.list(request));
    }

    @GetMapping("list/all")
    public List<JarConfig> listAll() {
        return JarConfigService.list();
    }

    @PostMapping("list")
    public List<JarConfig> list(@RequestBody JarConfig jarConfig) {
        return JarConfigService.searchList(jarConfig);
    }

    @GetMapping("/get/{id}")
    public JarConfig get(@PathVariable String id) {
        return JarConfigService.get(id);
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_JAR, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#request.id)", msClass = JarConfigService.class)
    public String add(@RequestPart("request") JarConfig request, @RequestPart(value = "file", required = false) MultipartFile file) {
        return JarConfigService.add(request, file);
    }

    @PostMapping(value = "/update", consumes = {"multipart/form-data"})
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_JAR, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#request.id)", content = "#msClass.getLogDetails(#request.id)", msClass = JarConfigService.class)
    public void update(@RequestPart("request") JarConfig request, @RequestPart(value = "file", required = false) MultipartFile file) {
        JarConfigService.update(request, file);
    }

    @GetMapping("/delete/{id}")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_JAR, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#id)", msClass = JarConfigService.class)
    public void delete(@PathVariable String id) {
        JarConfigService.delete(id);
    }

}
