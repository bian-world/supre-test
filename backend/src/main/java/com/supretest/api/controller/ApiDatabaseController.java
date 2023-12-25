package com.supretest.api.controller;

import com.supretest.api.dto.scenario.DatabaseConfig;
import com.supretest.api.service.APIDatabaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/database")
public class ApiDatabaseController {

    @Resource
    APIDatabaseService apiDatabaseService;

    @PostMapping("/validate")
    public void validate(@RequestBody DatabaseConfig databaseConfig) {
        apiDatabaseService.validate(databaseConfig);
    }

}
