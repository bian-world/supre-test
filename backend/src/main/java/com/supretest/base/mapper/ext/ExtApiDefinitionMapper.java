package com.supretest.base.mapper.ext;

import com.supretest.api.dto.datacount.ApiDataCountResult;
import com.supretest.api.dto.definition.ApiComputeResult;
import com.supretest.api.dto.definition.ApiDefinitionRequest;
import com.supretest.api.dto.definition.ApiDefinitionResult;
import com.supretest.api.dto.definition.ApiSwaggerUrlDTO;
import com.supretest.api.dto.scenario.Scenario;
import com.supretest.base.domain.ApiDefinition;
import com.supretest.base.domain.ApiDefinitionExample;
import com.supretest.base.domain.ApiDefinitionExampleWithOperation;
import com.supretest.controller.request.BaseQueryRequest;
import com.supretest.dto.RelationshipGraphData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ExtApiDefinitionMapper {
    List<ApiSwaggerUrlDTO> selectScheduleList(@Param("projectId") String projectId);

    List<ApiDefinitionResult> list(@Param("request") ApiDefinitionRequest request);

    List<ApiDefinitionResult> weekList(@Param("request") ApiDefinitionRequest request , @Param("startTimestamp") long startTimestamp );

    List<Scenario> scenarioList(@Param("apiDefinitionId") String apiDefinitionId);

    int moduleCount(@Param("request") ApiDefinitionRequest request);

    //List<ApiComputeResult> selectByIds(@Param("ids") List<String> ids);

    List<ApiComputeResult> selectByIds(@Param("ids") List<String> ids, @Param("projectId") String projectId);

    List<ApiComputeResult> selectByIdsAndStatusIsNotTrash(@Param("ids") List<String> ids, @Param("projectId") String projectId);

//    int removeToGc(@Param("ids") List<String> ids);

    int removeToGcByExample(ApiDefinitionExampleWithOperation example);

    int reduction(@Param("ids") List<String> ids);

    List<ApiDataCountResult> countProtocolByProjectID(String projectId, String subProjectId);

    Long countByProjectIDAndCreateInThisWeek(@Param("projectId") String projectId, @Param("subProjectId") String subProjectId, @Param("firstDayTimestamp") long firstDayTimestamp, @Param("lastDayTimestamp") long lastDayTimestamp);

    List<ApiDataCountResult> countStateByProjectID(String projectId, String subProjectId);

    List<ApiDataCountResult> countApiCoverageByProjectID(String projectId, String subProjectId);

    ApiDefinition getNextNum(@Param("projectId") String projectId);

    List<ApiDefinitionResult> listRelevance(@Param("request") ApiDefinitionRequest request);

    List<ApiDefinitionResult> listRelevanceReview(@Param("request") ApiDefinitionRequest request);

    List<String> selectIds(@Param("request") BaseQueryRequest query);

    List<ApiDefinition> selectEffectiveIdByProjectId(String projectId, String subProjectId );

    List<ApiDefinitionResult> listByIds(@Param("ids") List<String> ids);

    List<Map<String, Object>> moduleCountByCollection(@Param("request") ApiDefinitionRequest request);

    ApiDefinition selectUrlAndMethodById(String id);

    int checkOriginalStatusByIds(@Param("ids")List<String> ids);

    List<String> selectProjectIds();

    List<String> getIdsOrderByUpdateTime(@Param("projectId") String projectId);

    Long getPreOrder(@Param("projectId")String projectId, @Param("baseOrder") Long baseOrder);

    Long getLastOrder(@Param("projectId") String projectId, @Param("baseOrder") Long baseOrder);

    long countQuotedApiByProjectId(String projectId, String subProjectId);

    List<RelationshipGraphData.Node> getForGraph(@Param("ids") Set<String> ids);

    int countByIds(@Param("ids") List<String> ids);

    long countByExample(ApiDefinitionExample example);

    void clearLatestVersion(String refId);

    void addLatestVersion(String refId);

    List<String> selectRefIdsForVersionChange(@Param("versionId") String versionId, @Param("projectId") String projectId);

}
