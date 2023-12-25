package com.supretest.base.mapper.ext;

import com.supretest.base.domain.TestPlanReport;
import com.supretest.track.dto.TestPlanReportDTO;
import com.supretest.track.request.report.QueryTestPlanReportRequest;
import org.apache.ibatis.annotations.InsertProvider;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author song.tianyang
 * @Date 2021/1/8 4:58 下午
 * @Description
 */
public interface ExtTestPlanReportMapper {
    List<TestPlanReportDTO> list(@Param("request")QueryTestPlanReportRequest request);
}
