package com.supretest.dto;

import com.supretest.base.domain.Project;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO extends Project {
    private String workspaceName;
    private String createUserName;
}
