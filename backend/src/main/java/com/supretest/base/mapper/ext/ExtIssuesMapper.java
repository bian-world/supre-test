package com.supretest.base.mapper.ext;

import com.supretest.base.domain.Issues;
import com.supretest.base.domain.IssuesDao;
import com.supretest.track.dto.PlanReportIssueDTO;
import com.supretest.track.request.testcase.IssuesRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtIssuesMapper {

    List<IssuesDao> getIssuesByCaseId(@Param("request") IssuesRequest issuesRequest);

    List<IssuesDao> getIssueForMinder(@Param("caseIds") List<String> caseIds, @Param("refType") String refType);

    List<IssuesDao> getIssues(@Param("request") IssuesRequest issuesRequest);

    List<IssuesDao> getRelateIssues(@Param("request") IssuesRequest request);

    Issues getNextNum(String projectId);

    List<IssuesDao> getIssueForSync(String projectId);

    List<PlanReportIssueDTO> selectForPlanReport(String planId);

    List<IssuesDao> getCountByStatus(@Param("request") IssuesRequest issuesRequest);

    List<String> selectIdNotInUuIds(@Param("projectId") String projectId, @Param("platform") String platform, @Param("platformIds") List<String> platformIds);

    List<IssuesDao> getPlanIssues(@Param("request") IssuesRequest issueRequest);

    int deleteIssues(@Param("issuesId") String issuesId, @Param("resourceId") String resourceId);

    IssuesDao selectByPrimaryKey(String id);
}
