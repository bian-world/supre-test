package com.supretest.base.mapper.ext;

import com.supretest.base.domain.CustomField;
import com.supretest.controller.request.QueryCustomFieldRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtCustomFieldMapper {

    List<CustomField> list(@Param("request") QueryCustomFieldRequest request);

    List<CustomField> listRelate(@Param("request") QueryCustomFieldRequest request);

    List<String> listIds(@Param("request") QueryCustomFieldRequest request);
}
