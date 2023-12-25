package com.supretest.track.dto;

import com.supretest.base.domain.IssuesDao;
import com.supretest.base.domain.TestCaseWithBLOBs;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestCaseDTO extends TestCaseWithBLOBs {

    private String maintainerName;
    private String apiName;
    private String performName;
    private String lastResultId;
    private String projectName;
    private String subProjectName;
    private String createName;
    private String lastExecuteResult;
    private String versionName;

    private List<String> caseTags = new ArrayList<>();
    private List<IssuesDao> issueList = new ArrayList<>();
}
