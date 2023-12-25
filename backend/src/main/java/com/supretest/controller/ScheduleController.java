package com.supretest.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.service.ApiAutomationService;
import com.supretest.base.domain.Schedule;
import com.supretest.controller.request.QueryScheduleRequest;
import com.supretest.controller.request.ScheduleRequest;
import com.supretest.dto.ScheduleDao;
import com.supretest.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("schedule")
@RestController
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;
    @Resource
    private ApiAutomationService apiAutomationService;

    @PostMapping("/list/{goPage}/{pageSize}")
    public List<ScheduleDao> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody QueryScheduleRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return scheduleService.list(request);
    }

    @GetMapping("/findOne/{testId}/{group}")
    public Schedule schedule(@PathVariable String testId,@PathVariable String group) {
        Schedule schedule = scheduleService.getScheduleByResource(testId,group);
        return schedule;
    }

    @PostMapping(value = "/update")
    public void updateSchedule(@RequestBody Schedule request) {
        scheduleService.updateSchedule(request);
    }

    @PostMapping(value = "/create")
    public void createSchedule(@RequestBody ScheduleRequest request) {
        scheduleService.createSchedule(request);
    }

    @GetMapping(value = "/getTaskInfo")
    public Object getTaskInfo() {
        return scheduleService.getCurrentlyExecutingJobs();
    }
}
