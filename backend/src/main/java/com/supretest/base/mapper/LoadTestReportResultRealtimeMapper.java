package com.supretest.base.mapper;

import com.supretest.base.domain.LoadTestReportResultRealtime;
import com.supretest.base.domain.LoadTestReportResultRealtimeExample;
import com.supretest.base.domain.LoadTestReportResultRealtimeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoadTestReportResultRealtimeMapper {
    long countByExample(LoadTestReportResultRealtimeExample example);

    int deleteByExample(LoadTestReportResultRealtimeExample example);

    int deleteByPrimaryKey(LoadTestReportResultRealtimeKey key);

    int insert(LoadTestReportResultRealtime record);

    int insertSelective(LoadTestReportResultRealtime record);

    List<LoadTestReportResultRealtime> selectByExampleWithBLOBs(LoadTestReportResultRealtimeExample example);

    List<LoadTestReportResultRealtime> selectByExample(LoadTestReportResultRealtimeExample example);

    LoadTestReportResultRealtime selectByPrimaryKey(LoadTestReportResultRealtimeKey key);

    int updateByExampleSelective(@Param("record") LoadTestReportResultRealtime record, @Param("example") LoadTestReportResultRealtimeExample example);

    int updateByExampleWithBLOBs(@Param("record") LoadTestReportResultRealtime record, @Param("example") LoadTestReportResultRealtimeExample example);

    int updateByExample(@Param("record") LoadTestReportResultRealtime record, @Param("example") LoadTestReportResultRealtimeExample example);

    int updateByPrimaryKeySelective(LoadTestReportResultRealtime record);

    int updateByPrimaryKeyWithBLOBs(LoadTestReportResultRealtime record);
}