package com.supretest.base.mapper.ext;

import com.supretest.base.domain.LoadTestReport;
import com.supretest.base.domain.LoadTestReportWithBLOBs;
import com.supretest.dto.DashboardTestDTO;
import com.supretest.dto.ReportDTO;
import com.supretest.performance.controller.request.ReportRequest;
import com.supretest.track.dto.PlanReportCaseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtLoadTestReportMapper {

    List<ReportDTO> getReportList(@Param("reportRequest") ReportRequest request);

    ReportDTO getReportTestAndProInfo(@Param("id") String id);

    List<DashboardTestDTO> selectDashboardTests(@Param("workspaceId") String workspaceId, @Param("startTimestamp") long startTimestamp);

    List<String> selectResourceId(@Param("reportId") String reportId);

    void updateJmxContentIfAbsent(LoadTestReportWithBLOBs record);

    List<LoadTestReport> selectReportByProjectId(String projectId);

    List<PlanReportCaseDTO> selectForPlanReport(@Param("ids") List<String> reportIds);

    int updateReportVumStatus(String reportId,String reportKey ,String nextStatus, String preStatus);
}
