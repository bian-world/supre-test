package com.supretest.performance.controller;


import com.supretest.commons.utils.WeakConcurrentHashMap;
import com.supretest.controller.handler.annotation.NoResultHolder;
import com.supretest.performance.service.JmeterFileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("jmeter")
public class JmeterFileController {
    @Resource
    private JmeterFileService jmeterFileService;
    private final WeakConcurrentHashMap<String, List<Double>> readyMap = new WeakConcurrentHashMap<>(30 * 60 * 1000);// 默认保留30分钟

    @GetMapping("ping")
    public String checkStatus() {
        return "PONG";
    }

    @GetMapping("ready")
    @NoResultHolder
    public long ready(@RequestParam("reportId") String reportId, @RequestParam("ratio") String ratio,
                      @RequestParam("resourceIndex") int resourceIndex) {
        try {
            List<Double> ratios = readyMap.getOrDefault(reportId, Arrays.stream(ratio.split(",")).map(Double::parseDouble).collect(Collectors.toList()));
            ratios.set(resourceIndex, -1.0);
            readyMap.put(reportId, ratios);
            return ratios.stream().filter(r -> r > 0).count();
        } catch (Exception e) {
            return 0;
        }
    }

    @GetMapping("download")
    public ResponseEntity<byte[]> downloadJmeterFiles(@RequestParam("testId") String testId,
                                                      @RequestParam("ratio") String ratio,
                                                      @RequestParam("reportId") String reportId,
                                                      @RequestParam("resourceIndex") int resourceIndex) {
        double[] ratios = Arrays.stream(ratio.split(",")).mapToDouble(Double::parseDouble).toArray();
        byte[] bytes = jmeterFileService.downloadZip(reportId, ratios, resourceIndex);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + testId + ".zip\"")
                .body(bytes);
    }

}
