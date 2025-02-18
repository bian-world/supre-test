package com.supretest.reportstatistics.controller;

import com.supretest.reportstatistics.dto.TestCaseCountRequest;
import com.supretest.reportstatistics.dto.TestCaseCountResponse;
import com.supretest.reportstatistics.service.TestCaseCountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/report/test/case/count")
public class TestCaseCountController {

    @Resource
    TestCaseCountService testCaseCountService;

    @PostMapping("/initDatas")
    public Map<String, List<Map<String,String>>> initDatas(@RequestBody TestCaseCountRequest request) {
        Map<String,List<Map<String, String>>> returnMap = testCaseCountService.getSelectFilterDatas(request.getProjectId());

        return returnMap;
    }

    @PostMapping("/getReport")
    public TestCaseCountResponse getReport(@RequestBody TestCaseCountRequest request) {
        TestCaseCountResponse response =  testCaseCountService.getReport(request);
        return response;
    }
}
