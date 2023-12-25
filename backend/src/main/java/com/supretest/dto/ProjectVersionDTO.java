package com.supretest.dto;

import com.supretest.base.domain.ProjectVersion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectVersionDTO extends ProjectVersion {
    private String createUserName;
}
