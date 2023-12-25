package com.supretest.base.mapper;

import com.supretest.api.dto.ApiMonitorSearch;
import com.supretest.api.dto.ApiResponseCodeMonitor;
import com.supretest.api.dto.ApiResponseTimeMonitor;
import com.supretest.base.domain.ApiDataView;

import java.util.List;

public interface ApiDataViewMapper {

    List<ApiMonitorSearch> selectAll();

    List<ApiResponseTimeMonitor> selectResponseTimeByUrl(String url,String startTime,String endTime);

    List<ApiResponseCodeMonitor> selectResponseCodeByUrl(String url,String startTime,String endTime);

    Integer insertListApiData(List<ApiDataView> list);

    Integer deleteByReportId(String reportId);

    String selectReportIdByUrlAndStartTime(String apiUrl,String startTime);
}