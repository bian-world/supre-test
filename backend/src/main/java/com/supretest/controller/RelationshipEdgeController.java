package com.supretest.controller;


import com.supretest.controller.request.RelationshipEdgeRequest;
import com.supretest.service.RelationshipEdgeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("relationship/edge")
@RestController
public class RelationshipEdgeController {

    @Resource
    private RelationshipEdgeService relationshipEdgeService;

    @PostMapping("/save/batch")
    public void add(@RequestBody RelationshipEdgeRequest request) {
        relationshipEdgeService.saveBatch(request);
    }

    @GetMapping("/delete/{sourceId}/{targetId}")
    public void delete(@PathVariable("sourceId") String sourceId, @PathVariable("targetId") String targetId) {
        relationshipEdgeService.delete(sourceId, targetId);
    }

}
