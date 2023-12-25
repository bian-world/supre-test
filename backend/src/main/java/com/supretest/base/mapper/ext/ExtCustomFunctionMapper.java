package com.supretest.base.mapper.ext;

import com.supretest.base.domain.CustomFunction;
import com.supretest.controller.request.CustomFunctionRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtCustomFunctionMapper {

    List<CustomFunction> queryAll(@Param("request")CustomFunctionRequest request);
}
