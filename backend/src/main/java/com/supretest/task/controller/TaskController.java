package com.supretest.task.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.task.dto.TaskCenterDTO;
import com.supretest.task.dto.TaskCenterRequest;
import com.supretest.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/task/center")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<TaskCenterDTO>> getTasks(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody TaskCenterRequest request) {
        request.setProjects(taskService.getOwnerProjectIds(null));
        request.setGoPage(goPage);
        request.setPageSize(pageSize);
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, taskService.getTasks(request));
    }

    @GetMapping("/case/{id}")
    public List<TaskCenterDTO> getCase(@PathVariable String id) {
        return taskService.getCases(id);
    }

    @GetMapping("/scenario/{id}")
    public List<TaskCenterDTO> getScenario(@PathVariable String id) {
        return taskService.getScenario(id);
    }

    @PostMapping("/count/running")
    public int getRunningTasks(@RequestBody TaskCenterRequest request) {
        return taskService.getRunningTasks(request);
    }

}
