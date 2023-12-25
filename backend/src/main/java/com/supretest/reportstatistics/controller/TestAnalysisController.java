package com.supretest.reportstatistics.controller;

import com.supretest.reportstatistics.dto.TestAnalysisChartRequest;
import com.supretest.reportstatistics.dto.TestAnalysisResult;
import com.supretest.reportstatistics.service.TestAnalysisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/report/test/analysis")
public class TestAnalysisController {

    @Resource
    TestAnalysisService testAnalysisService;

    @PostMapping("/getReport")
    public TestAnalysisResult getReport(@RequestBody TestAnalysisChartRequest request) {
        return testAnalysisService.getReport(request);
    }
}
