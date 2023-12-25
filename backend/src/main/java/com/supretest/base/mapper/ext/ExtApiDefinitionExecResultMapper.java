package com.supretest.base.mapper.ext;

import com.supretest.api.dto.QueryAPIReportRequest;
import com.supretest.api.dto.automation.APIScenarioReportResult;
import com.supretest.api.dto.datacount.ExecutedCaseInfoResult;
import com.supretest.base.domain.ApiDefinitionExecResult;
import com.supretest.base.domain.ApiDefinitionExecResultExpand;
import com.supretest.track.dto.PlanReportCaseDTO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface ExtApiDefinitionExecResultMapper {

    List<ApiDefinitionExecResultExpand> list(@Param("request") QueryAPIReportRequest request);

    void deleteByResourceId(String id);

    ApiDefinitionExecResult selectMaxResultByResourceId(String resourceId);

    ApiDefinitionExecResult selectMaxResultByResourceIdAndType(String resourceId, String type);


    long countByProjectIDAndCreateInThisWeek(@Param("projectId") String projectId, @Param("subProjectId") String subProjectId, @Param("firstDayTimestamp") long firstDayTimestamp, @Param("lastDayTimestamp") long lastDayTimestamp);

    long countByTestCaseIDInProject(String projectId, String subProjectId);

    List<ExecutedCaseInfoResult> findFaliureCaseInfoByProjectIDAndExecuteTimeAndLimitNumber(@Param("projectId") String projectId, @Param("subProjectId") String subProjectId, @Param("startTimestamp") long startTimestamp);

    String selectExecResult(String resourceId);

    ApiDefinitionExecResult selectPlanApiMaxResultByTestIdAndType(String resourceId, String type);

    List<ApiDefinitionExecResult> selectStatusByIdList(@Param("ids") Collection<String> values);

    List<ApiDefinitionExecResult> selectApiResultByProjectId(String projectId);

    List<PlanReportCaseDTO> selectForPlanReport(@Param("ids") List<String> apiReportIds);

    void update(@Param("ids") List<String> ids);

    @InsertProvider(type = ExtApiDefinitionExecResultProvider.class, method = "insertListSql")
    void sqlInsert(List<ApiDefinitionExecResult> list);

}
