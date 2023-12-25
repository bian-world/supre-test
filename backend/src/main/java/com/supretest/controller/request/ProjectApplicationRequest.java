package com.supretest.controller.request;

import com.supretest.base.domain.ProjectApplication;
import lombok.Data;

import java.util.List;

@Data
public class ProjectApplicationRequest {
    private List<ProjectApplication> configs;
}
