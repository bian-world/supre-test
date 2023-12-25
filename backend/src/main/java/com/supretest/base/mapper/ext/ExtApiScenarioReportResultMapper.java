package com.supretest.base.mapper.ext;

import com.supretest.base.domain.ApiScenarioReportResultWithBLOBs;

import java.util.List;

public interface ExtApiScenarioReportResultMapper {
    List<ApiScenarioReportResultWithBLOBs> selectBaseInfoResultByReportId(String reportId);
}
