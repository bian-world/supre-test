package com.supretest.base.mapper.ext;

import com.supretest.base.domain.ApiDefinition;
import com.supretest.base.domain.TestPlanExecutionQueue;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtTestPlanExecutionQueueMapper {

    @InsertProvider(type = ExtTestPlanExecutionQueueProvider.class, method = "insertListSql")
    void sqlInsert(List<TestPlanExecutionQueue> list);

    TestPlanExecutionQueue getNextNum(@Param("resourceId") String resourceId);
}
