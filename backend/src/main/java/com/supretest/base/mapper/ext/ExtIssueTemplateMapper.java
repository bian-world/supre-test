package com.supretest.base.mapper.ext;

import com.supretest.base.domain.IssueTemplate;
import com.supretest.controller.request.BaseQueryRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtIssueTemplateMapper {
    List<IssueTemplate> list(@Param("request") BaseQueryRequest request);
}
