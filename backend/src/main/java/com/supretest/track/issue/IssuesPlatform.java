package com.supretest.track.issue;

import com.supretest.base.domain.IssuesDao;
import com.supretest.base.domain.IssuesWithBLOBs;
import com.supretest.base.domain.Project;
import com.supretest.dto.IssueTemplateDao;
import com.supretest.dto.UserDTO;
import com.supretest.track.dto.DemandDTO;
import com.supretest.track.issue.domain.PlatformUser;
import com.supretest.track.request.testcase.EditTestCaseRequest;
import com.supretest.track.request.testcase.IssuesRequest;
import com.supretest.track.request.testcase.IssuesUpdateRequest;

import java.util.List;

public interface IssuesPlatform {

    /**
     * 获取平台相关联的缺陷
     *
     * @return platform issues list
     */
    List<IssuesDao> getIssue(IssuesRequest request);

    /*获取平台相关需求*/
    List<DemandDTO> getDemandList(String projectId);

    /**
     * 添加缺陷到缺陷平台
     *
     * @param issuesRequest issueRequest
     */
    IssuesWithBLOBs addIssue(IssuesUpdateRequest issuesRequest);

    /**
     * 更新缺陷
     * @param request
     */
    void updateIssue(IssuesUpdateRequest request);

    /**
     * 删除缺陷平台缺陷
     *
     * @param id issue id
     */
    void deleteIssue(String id);

    /**
     * 测试平台联通性
     */
    void testAuth();

    /**
     * 用户信息测试
     */
    void userAuth(UserDTO.PlatformInfo userInfo);

    /**
     * 获取缺陷平台项目下的相关人员
     * @return platform user list
     */
    List<PlatformUser> getPlatformUser();

    /**
     * 同步缺陷最新变更
     * @param project
     * @param tapdIssues
     */
    void syncIssues(Project project, List<IssuesDao> tapdIssues);

    /**
     * 同步缺陷全量的缺陷
     * @param project
     */
    void syncAllIssues(Project project);

    /**
     * 获取第三方平台缺陷模板
     * @return
     */
    IssueTemplateDao getThirdPartTemplate();

    /**
     * 检查其它平台关联的ID是否存在
     * @param relateId 其它平台在MS项目上关联的相关ID
     * @return Boolean
     */
    Boolean checkProjectExist(String relateId);

    /**
     * 更新缺陷关联关系
     * @param request
     */
    void removeIssueParentLink(IssuesUpdateRequest request);

    /**
     * 更新需求与缺陷关联关系
     *
     * @param testCase
     */
    void updateDemandIssueLink(EditTestCaseRequest testCase, Project project);

    /**
     * @param request
     * @param type    add or edit
     */
    void updateDemandHyperLink(EditTestCaseRequest request, Project project, String type);
}
