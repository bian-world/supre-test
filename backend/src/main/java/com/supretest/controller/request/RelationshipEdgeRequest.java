package com.supretest.controller.request;

import com.supretest.base.domain.RelationshipEdge;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RelationshipEdgeRequest extends RelationshipEdge {
    private String id;
    private List<String> targetIds;
    private List<String> sourceIds;
}
