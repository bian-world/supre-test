package com.supretest.track.dto;

import lombok.Data;

@Data
public class ApiRunConfigDTO {
    private String mode;
    private String reportType;
    private boolean onSampleError;
    private String runWithinResourcePool;
    private String resourcePoolId;
}
