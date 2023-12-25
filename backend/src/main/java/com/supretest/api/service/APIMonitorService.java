package com.supretest.api.service;

import com.supretest.api.dto.ApiMonitorSearch;
import com.supretest.api.dto.ApiResponseCodeMonitor;
import com.supretest.api.dto.ApiResponseTimeMonitor;
import com.supretest.base.mapper.ApiDataViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class APIMonitorService {

    @Resource
    private ApiDataViewMapper apiDataViewMapper;

    public List<ApiMonitorSearch> list() {
        return apiDataViewMapper.selectAll();
    }

    public List<ApiResponseTimeMonitor> getApiResponseTimeData(String apiUrl, String startTime, String endTime) {
        return apiDataViewMapper.selectResponseTimeByUrl(apiUrl, startTime, endTime);
    }

    public List<ApiResponseCodeMonitor> getApiResponseCodeData(String apiUrl, String startTime, String endTime) {
        return apiDataViewMapper.selectResponseCodeByUrl(apiUrl, startTime, endTime);
    }

    public String getReportId(String apiUrl, String startTime) {
        return apiDataViewMapper.selectReportIdByUrlAndStartTime(apiUrl, startTime);
    }
}
