package com.supretest.base.mapper;

import com.supretest.base.domain.EnterpriseTestReportSendRecord;
import com.supretest.base.domain.EnterpriseTestReportSendRecordExample;
import com.supretest.base.domain.EnterpriseTestReportSendRecordWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnterpriseTestReportSendRecordMapper {
    long countByExample(EnterpriseTestReportSendRecordExample example);

    int deleteByExample(EnterpriseTestReportSendRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(EnterpriseTestReportSendRecordWithBLOBs record);

    int insertSelective(EnterpriseTestReportSendRecordWithBLOBs record);

    List<EnterpriseTestReportSendRecordWithBLOBs> selectByExampleWithBLOBs(EnterpriseTestReportSendRecordExample example);

    List<EnterpriseTestReportSendRecord> selectByExample(EnterpriseTestReportSendRecordExample example);

    EnterpriseTestReportSendRecordWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EnterpriseTestReportSendRecordWithBLOBs record, @Param("example") EnterpriseTestReportSendRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") EnterpriseTestReportSendRecordWithBLOBs record, @Param("example") EnterpriseTestReportSendRecordExample example);

    int updateByExample(@Param("record") EnterpriseTestReportSendRecord record, @Param("example") EnterpriseTestReportSendRecordExample example);

    int updateByPrimaryKeySelective(EnterpriseTestReportSendRecordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EnterpriseTestReportSendRecordWithBLOBs record);

    int updateByPrimaryKey(EnterpriseTestReportSendRecord record);
}