package com.supretest.base.mapper.ext;

import com.supretest.api.dto.datacount.response.TaskInfoResult;
import com.supretest.api.dto.definition.ApiSwaggerUrlDTO;
import com.supretest.controller.request.BaseQueryRequest;
import com.supretest.controller.request.QueryScheduleRequest;
import com.supretest.dto.ScheduleDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtScheduleMapper {
    List<ScheduleDao> list(@Param("request") QueryScheduleRequest request);

    long countTaskByProjectId(String projectId, String subProjectId);

    long countTaskByProjectIdAndCreateTimeRange(@Param("projectId")String projectId, @Param("subProjectId")String subProjectId, @Param("startTime") long startTime, @Param("endTime") long endTime);

    List<TaskInfoResult> findRunningTaskInfoByProjectID(String projectId, String subProjectId, @Param("request")BaseQueryRequest request);

    void insert(@Param("apiSwaggerUrlDTO") ApiSwaggerUrlDTO apiSwaggerUrlDTO);

    ApiSwaggerUrlDTO  select(String id);

    int updateNameByResourceID(@Param("resourceId") String resourceId, @Param("name") String name);

}
