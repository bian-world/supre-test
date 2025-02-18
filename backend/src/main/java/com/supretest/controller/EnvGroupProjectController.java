package com.supretest.controller;


import com.supretest.dto.EnvironmentGroupProjectDTO;
import com.supretest.service.EnvironmentGroupProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lyh
 */
@RequestMapping("/environment/group/project")
@RestController
public class EnvGroupProjectController {

    @Resource
    private EnvironmentGroupProjectService environmentGroupProjectService;

    @GetMapping("/list/{groupId}")
    public List<EnvironmentGroupProjectDTO> getList(@PathVariable String groupId) {
        return environmentGroupProjectService.getList(groupId);
    }
}
