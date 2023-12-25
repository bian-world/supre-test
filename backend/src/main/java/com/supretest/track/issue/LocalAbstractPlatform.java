package com.supretest.track.issue;

import com.supretest.base.domain.IssuesDao;
import com.supretest.base.domain.Project;
import com.supretest.dto.UserDTO;
import com.supretest.track.issue.domain.PlatformUser;
import com.supretest.track.request.testcase.IssuesRequest;

import java.util.List;

public abstract class LocalAbstractPlatform extends AbstractIssuePlatform {

    public LocalAbstractPlatform(IssuesRequest issuesRequest) { super(issuesRequest); }

    @Override
    public void testAuth() {}

    @Override
    public void userAuth(UserDTO.PlatformInfo userInfo) {}

    @Override
    public List<PlatformUser> getPlatformUser() { return null; }

    @Override
    public void syncIssues(Project project, List<IssuesDao> tapdIssues) {}

    @Override
    public String getProjectId(String projectId) { return null; }
}
