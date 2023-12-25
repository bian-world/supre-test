package com.supretest.base.mapper.ext;

import com.supretest.base.domain.FileMetadata;
import com.supretest.base.domain.LoadTest;
import com.supretest.controller.request.BaseQueryRequest;
import com.supretest.dto.LoadTestDTO;
import com.supretest.performance.request.QueryProjectFileRequest;
import com.supretest.performance.request.QueryTestPlanRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ExtLoadTestMapper {
    List<LoadTestDTO> list(@Param("request") QueryTestPlanRequest params);

    List<LoadTest> getLoadTestByProjectId(String projectId);

    int checkLoadTestOwner(@Param("testId") String testId, @Param("projectIds") Set<String> projectIds);

    LoadTest getNextNum(@Param("projectId") String projectId);

    List<FileMetadata> getProjectFiles(@Param("projectId") String projectId, @Param("loadTypes") List<String> loadType,
                                       @Param("request") QueryProjectFileRequest request);

    List<String> selectProjectIds();

    List<String> getIdsOrderByUpdateTime(@Param("projectId") String projectId);

    Long getPreOrder(@Param("projectId") String projectId, @Param("baseOrder") Long baseOrder);

    Long getLastOrder(@Param("projectId") String projectId, @Param("baseOrder") Long baseOrder);

    int moduleCount(@Param("request") QueryTestPlanRequest request);

    void addLatestVersion(String refId);

    void clearLatestVersion(String refId);

    List<String> selectRefIdsForVersionChange(@Param("versionId") String versionId, @Param("projectId") String projectId);

    List<FileMetadata> getFileMetadataByIds(@Param("testId") String testId);

    List<String> selectIds(@Param("request") BaseQueryRequest request);
}
