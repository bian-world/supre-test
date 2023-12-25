package com.supretest.controller;

import com.supretest.base.domain.Plugin;
import com.supretest.commons.exception.MSException;
import com.supretest.controller.request.PluginDTO;
import com.supretest.controller.request.PluginRequest;
import com.supretest.service.PluginService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/plugin")
public class PluginController {

    @Resource
    private PluginService pluginService;

    @PostMapping("/add")
    public String create(@RequestPart(value = "file", required = false) MultipartFile file) {
        if (file == null) {
            MSException.throwException("上传文件/执行入口为空");
        }
        return pluginService.editPlugin(file);
    }

    @GetMapping("/list")
    public List<PluginDTO> list(String name) {
        return pluginService.list(name);
    }

    @GetMapping("/get/{id}")
    public Plugin get(@PathVariable String id) {
        return pluginService.get(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return pluginService.delete(id);
    }

    @PostMapping(value = "/customMethod")
    public Object customMethod(@RequestBody PluginRequest request) {
        return pluginService.customMethod(request);
    }

}
