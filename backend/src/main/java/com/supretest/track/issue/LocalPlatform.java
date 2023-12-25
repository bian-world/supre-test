package com.supretest.track.issue;

import com.alibaba.fastjson.JSONObject;
import com.supretest.base.domain.IssuesDao;
import com.supretest.base.domain.IssuesWithBLOBs;
import com.supretest.commons.constants.IssuesManagePlatform;
import com.supretest.commons.user.SessionUser;
import com.supretest.commons.utils.BeanUtils;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.track.dto.DemandDTO;
import com.supretest.track.request.testcase.IssuesRequest;
import com.supretest.track.request.testcase.IssuesUpdateRequest;
import com.supretest.track.request.testcase.TestCaseBatchRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

public class LocalPlatform extends LocalAbstractPlatform {

    protected String key = IssuesManagePlatform.Local.toString();

    public LocalPlatform(IssuesRequest issuesRequest) {
        super(issuesRequest);
    }

    @Override
    public List<IssuesDao> getIssue(IssuesRequest issuesRequest) {
        String projectId = issuesRequest.getProjectId();
        issuesRequest.setPlatform(IssuesManagePlatform.Local.toString());
        if (StringUtils.isNotBlank(projectId)) {
            return extIssuesMapper.getIssues(issuesRequest);
        }
        return extIssuesMapper.getIssuesByCaseId(issuesRequest);
    }

    @Override
    public List<DemandDTO> getDemandList(String projectId) {
        return null;
    }

    @Override
    public IssuesWithBLOBs addIssue(IssuesUpdateRequest issuesRequest) {
        String issueStatus = "new";
        if (StringUtils.isNotBlank(issuesRequest.getCustomFields())) {
            List<TestCaseBatchRequest.CustomFiledRequest> fields = JSONObject.parseArray(issuesRequest.getCustomFields(), TestCaseBatchRequest.CustomFiledRequest.class);
            for (TestCaseBatchRequest.CustomFiledRequest field : fields) {
                if (StringUtils.equals("状态", field.getName())) {
                    issueStatus = (String) field.getValue();
                    break;
                }
            }
        }
        SessionUser user = SessionUtils.getUser();
        String id = UUID.randomUUID().toString();
        IssuesWithBLOBs issues = new IssuesWithBLOBs();
        BeanUtils.copyBean(issues, issuesRequest);
        issues.setId(id);
        issues.setPlatformId(id);
        issues.setStatus(issueStatus);
        issues.setReporter(user.getId());
        issues.setCreateTime(System.currentTimeMillis());
        issues.setUpdateTime(System.currentTimeMillis());
        issues.setPlatform(IssuesManagePlatform.Local.toString());;
        issues.setNum(getNextNum(issuesRequest.getProjectId()));
        issuesMapper.insert(issues);

        issuesRequest.setId(id);
        handleTestCaseIssues(issuesRequest);

        return issues;
    }

    @Override
    public void updateIssue(IssuesUpdateRequest request) {
        handleIssueUpdate(request);
    }
}
