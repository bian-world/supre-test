package com.supretest.dto;

import com.supretest.base.domain.Project;
import com.supretest.base.domain.Workspace;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkspaceResource {
    private List<Workspace> workspaces = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
}
