package com.supretest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.dto.definition.RunDefinitionRequest;
import com.supretest.base.domain.CustomFunction;
import com.supretest.base.domain.CustomFunctionWithBLOBs;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.controller.request.CustomFunctionRequest;
import io.metersphere.dto.MsExecResponseDTO;
import com.supretest.service.CustomFunctionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lyh
 */

@RequestMapping("custom/func")
@RestController
public class CustomFunctionController {

    @Resource
    private CustomFunctionService customFunctionService;

    @PostMapping("/save")
    public CustomFunctionWithBLOBs save(@RequestBody CustomFunctionRequest request) {
        return customFunctionService.save(request);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        customFunctionService.delete(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody CustomFunctionRequest request) {
        customFunctionService.update(request);
    }

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<CustomFunction>> query(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody CustomFunctionRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, customFunctionService.query(request));
    }

    @GetMapping("/copy/{id}")
    public CustomFunctionWithBLOBs copy(@PathVariable String id) {
        return customFunctionService.copy(id);
    }

    @GetMapping("/get/{id}")
    public CustomFunctionWithBLOBs get(@PathVariable String id) {
        return customFunctionService.get(id);
    }

    @PostMapping("/run")
    public MsExecResponseDTO run(@RequestBody RunDefinitionRequest request) {
        return customFunctionService.run(request);
    }
}
