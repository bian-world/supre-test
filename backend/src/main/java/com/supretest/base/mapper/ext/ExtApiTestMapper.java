package com.supretest.base.mapper.ext;

import com.supretest.api.dto.APITestResult;
import com.supretest.api.dto.QueryAPITestRequest;
import com.supretest.base.domain.ApiTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ExtApiTestMapper {
    List<APITestResult> list(@Param("request") QueryAPITestRequest request);

    List<ApiTest> getApiTestByProjectId(String projectId, String subProjectId);

    List<ApiTest> listByIds(@Param("ids") List<String> ids);

    int checkApiTestOwner(@Param("testId") String testId, @Param("projectIds") Set<String> projectIds);

}
