package com.supretest.api.exec.queue;

import com.supretest.base.domain.ApiExecutionQueue;
import com.supretest.base.domain.ApiExecutionQueueDetail;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DBTestQueue extends ApiExecutionQueue {
    private String completedReportId;
    private ApiExecutionQueueDetail queue;
    private Map<String, String> detailMap = new HashMap<>();
}
