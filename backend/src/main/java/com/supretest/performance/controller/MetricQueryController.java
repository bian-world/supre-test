package com.supretest.performance.controller;

import com.supretest.performance.dto.MetricData;
import com.supretest.performance.dto.Monitor;
import com.supretest.performance.service.MetricQueryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/metric")
public class MetricQueryController {

    @Resource
    private MetricQueryService metricService;

    @GetMapping("/query/{id}")
    public List<MetricData> queryMetric(@PathVariable("id") String reportId) {
        return metricService.queryMetric(reportId);
    }

    @GetMapping("/query/resource/{id}")
    public List<Monitor> queryReportResource(@PathVariable("id") String reportId) {
        return metricService.queryReportResource(reportId);
    }
}
