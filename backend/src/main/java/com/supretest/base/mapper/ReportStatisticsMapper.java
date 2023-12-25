package com.supretest.base.mapper;

import com.supretest.base.domain.ReportStatistics;
import com.supretest.base.domain.ReportStatisticsExample;
import com.supretest.base.domain.ReportStatisticsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportStatisticsMapper {
    long countByExample(ReportStatisticsExample example);

    int deleteByExample(ReportStatisticsExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportStatisticsWithBLOBs record);

    int insertSelective(ReportStatisticsWithBLOBs record);

    List<ReportStatisticsWithBLOBs> selectByExampleWithBLOBs(ReportStatisticsExample example);

    List<ReportStatistics> selectByExample(ReportStatisticsExample example);

    ReportStatisticsWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportStatisticsWithBLOBs record, @Param("example") ReportStatisticsExample example);

    int updateByExampleWithBLOBs(@Param("record") ReportStatisticsWithBLOBs record, @Param("example") ReportStatisticsExample example);

    int updateByExample(@Param("record") ReportStatistics record, @Param("example") ReportStatisticsExample example);

    int updateByPrimaryKeySelective(ReportStatisticsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ReportStatisticsWithBLOBs record);

    int updateByPrimaryKey(ReportStatistics record);
}