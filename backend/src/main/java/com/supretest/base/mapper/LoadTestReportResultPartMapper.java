package com.supretest.base.mapper;

import com.supretest.base.domain.LoadTestReportResultPart;
import com.supretest.base.domain.LoadTestReportResultPartExample;
import com.supretest.base.domain.LoadTestReportResultPartKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoadTestReportResultPartMapper {
    long countByExample(LoadTestReportResultPartExample example);

    int deleteByExample(LoadTestReportResultPartExample example);

    int deleteByPrimaryKey(LoadTestReportResultPartKey key);

    int insert(LoadTestReportResultPart record);

    int insertSelective(LoadTestReportResultPart record);

    List<LoadTestReportResultPart> selectByExampleWithBLOBs(LoadTestReportResultPartExample example);

    List<LoadTestReportResultPart> selectByExample(LoadTestReportResultPartExample example);

    LoadTestReportResultPart selectByPrimaryKey(LoadTestReportResultPartKey key);

    int updateByExampleSelective(@Param("record") LoadTestReportResultPart record, @Param("example") LoadTestReportResultPartExample example);

    int updateByExampleWithBLOBs(@Param("record") LoadTestReportResultPart record, @Param("example") LoadTestReportResultPartExample example);

    int updateByExample(@Param("record") LoadTestReportResultPart record, @Param("example") LoadTestReportResultPartExample example);

    int updateByPrimaryKeySelective(LoadTestReportResultPart record);

    int updateByPrimaryKeyWithBLOBs(LoadTestReportResultPart record);
}