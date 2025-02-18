package com.supretest.base.mapper;

import com.supretest.base.domain.ApiScenarioReportResult;
import com.supretest.base.domain.ApiScenarioReportResultExample;
import com.supretest.base.domain.ApiScenarioReportResultWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiScenarioReportResultMapper {
    long countByExample(ApiScenarioReportResultExample example);

    int deleteByExample(ApiScenarioReportResultExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApiScenarioReportResultWithBLOBs record);

    int insertSelective(ApiScenarioReportResultWithBLOBs record);

    List<ApiScenarioReportResultWithBLOBs> selectByExampleWithBLOBs(ApiScenarioReportResultExample example);

    List<ApiScenarioReportResult> selectByExample(ApiScenarioReportResultExample example);

    ApiScenarioReportResultWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApiScenarioReportResultWithBLOBs record, @Param("example") ApiScenarioReportResultExample example);

    int updateByExampleWithBLOBs(@Param("record") ApiScenarioReportResultWithBLOBs record, @Param("example") ApiScenarioReportResultExample example);

    int updateByExample(@Param("record") ApiScenarioReportResult record, @Param("example") ApiScenarioReportResultExample example);

    int updateByPrimaryKeySelective(ApiScenarioReportResultWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ApiScenarioReportResultWithBLOBs record);

    int updateByPrimaryKey(ApiScenarioReportResult record);
}