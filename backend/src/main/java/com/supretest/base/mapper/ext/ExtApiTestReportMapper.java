package com.supretest.base.mapper.ext;

import com.supretest.api.dto.APIReportResult;
import com.supretest.api.dto.QueryAPIReportRequest;
import com.supretest.api.dto.datacount.ApiDataCountResult;
import com.supretest.dto.DashboardTestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtApiTestReportMapper {

    List<APIReportResult> list(@Param("request") QueryAPIReportRequest request);

    List<APIReportResult> listByTestId(@Param("testId") String testId);

    APIReportResult get(@Param("id") String id);

    List<DashboardTestDTO> selectDashboardTests(@Param("workspaceId") String workspaceId, @Param("startTimestamp") long startTimestamp);

    List<ApiDataCountResult> countByProjectIdGroupByExecuteResult(String projectId);

    long countByProjectIDAndCreateInThisWeek(@Param("projectId") String projectId, @Param("startTime") long startTime, @Param("endTime")long endTime);
}
