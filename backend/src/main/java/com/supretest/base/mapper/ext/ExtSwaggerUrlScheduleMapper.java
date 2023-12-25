package com.supretest.base.mapper.ext;

import com.supretest.api.dto.swaggerurl.SwaggerTaskResult;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExtSwaggerUrlScheduleMapper {
    //接口列表定时导入
    List<SwaggerTaskResult> getSwaggerTaskList(@Param("projectId") String projectId, @Param("subProjectId") String subProjectId);
}
