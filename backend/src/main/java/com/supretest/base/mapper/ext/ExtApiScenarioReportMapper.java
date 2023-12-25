package com.supretest.base.mapper.ext;

import com.supretest.api.dto.QueryAPIReportRequest;
import com.supretest.api.dto.automation.APIScenarioReportResult;
import com.supretest.api.dto.datacount.ApiDataCountResult;
import com.supretest.base.domain.ApiScenarioReport;
import com.supretest.dto.ApiReportCountDTO;
import com.supretest.track.dto.PlanReportCaseDTO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface ExtApiScenarioReportMapper {
    List<APIScenarioReportResult> list(@Param("request") QueryAPIReportRequest request);

    APIScenarioReportResult get(@Param("reportId") String reportId);

    long countByProjectID(String projectId);

    long countByProjectIdAndCreateInThisWeek(@Param("projectId") String projectId, @Param("firstDayTimestamp") long firstDayTimestamp, @Param("lastDayTimestamp") long lastDayTimestamp);

    long countByProjectIdAndCreateAndByScheduleInThisWeek(@Param("projectId") String projectId, @Param("subProjectId") String subProjectId, @Param("firstDayTimestamp") long firstDayTimestamp, @Param("lastDayTimestamp") long lastDayTimestamp);

    List<ApiDataCountResult> countByProjectIdGroupByExecuteResult(String projectId, String subProjectId);

    List<ApiScenarioReport> selectLastReportByIds(@Param("scenarioIdList") List<String> ids);

    ApiScenarioReport selectPreviousReportByScenarioId(@Param("scenarioId") String scenarioId, @Param("nowId") String nowId);

    List<String> idList(@Param("request") QueryAPIReportRequest request);

    List<ApiReportCountDTO> countByApiScenarioId();

    List<ApiScenarioReport> selectStatusByIds(@Param("ids") Collection<String> values);

    List<ApiScenarioReport> selectReportByProjectId(String projectId);

    List<PlanReportCaseDTO> selectForPlanReport(@Param("ids") List<String> reportIds);

    void update(@Param("ids") List<String> ids);

    @InsertProvider(type = ExtApiScenarioReportProvider.class, method = "insertListSql")
    void sqlInsert(List<APIScenarioReportResult> list);

}
