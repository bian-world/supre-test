package com.supretest.performance.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricDataRequest {
    private String seriesName;
    private String promQL;
    private String instance;
}
