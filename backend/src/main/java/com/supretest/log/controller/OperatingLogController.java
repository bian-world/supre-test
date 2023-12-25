package com.supretest.log.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.base.domain.OperatingLogWithBLOBs;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.log.service.OperatingLogService;
import com.supretest.log.vo.OperatingLogDTO;
import com.supretest.log.vo.OperatingLogRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/operating/log")
public class OperatingLogController {
    @Resource
    private OperatingLogService operatingLogService;

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<OperatingLogDTO>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody OperatingLogRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, operatingLogService.list(request));
    }

    @GetMapping("/get/{id}")
    public OperatingLogDTO get(@PathVariable String id) {
        return operatingLogService.get(id);
    }


    @PostMapping("/get/source/{goPage}/{pageSize}")
    public Pager<List<OperatingLogDTO>> findBySourceId(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody OperatingLogRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, operatingLogService.findBySourceId(request));
    }

    @PostMapping("/save")
    public void save(@RequestBody OperatingLogWithBLOBs msOperLog) {
        //保存获取的操作
        msOperLog.setId(UUID.randomUUID().toString());
        String sourceIds = msOperLog.getSourceId();
        operatingLogService.create(msOperLog, sourceIds);
    }
}
