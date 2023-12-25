package com.supretest.base.mapper.ext;

import com.supretest.base.domain.ApiExecutionQueue;
import com.supretest.base.domain.ApiExecutionQueueDetail;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

public interface ExtApiExecutionQueueMapper {
    void delete();

    List<ApiExecutionQueue> findTestPlanReportQueue();

    List<String> findTestPlanRunningReport();

    @InsertProvider(type = ExtApiExecutionQueueProvider.class, method = "insertListSql")
    void sqlInsert(List<ApiExecutionQueueDetail> list);

}