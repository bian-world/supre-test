package com.supretest.base.mapper.ext;

import com.supretest.base.domain.TestCaseTemplateWithBLOBs;
import com.supretest.controller.request.BaseQueryRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ExtTestCaseTemplateMapper {
    List<TestCaseTemplateWithBLOBs> list(@Param("request") BaseQueryRequest request);
}
