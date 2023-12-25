package com.supretest.base.mapper.ext;

import com.supretest.base.domain.ApiScenarioReportDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtApiScenarioReportDetailMapper {
    List<ApiScenarioReportDetail> selectByIds(@Param("ids") String ids, @Param("oderId") String oderId);
}
