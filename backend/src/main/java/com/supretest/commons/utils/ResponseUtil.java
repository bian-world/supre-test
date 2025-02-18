package com.supretest.commons.utils;

import com.supretest.api.dto.ErrorReportLibraryParseDTO;
import com.supretest.api.dto.RequestResultExpandDTO;
import com.supretest.commons.constants.ExecuteResult;
import io.metersphere.dto.RequestResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求返回解析工具
 */
public class ResponseUtil {

    public static RequestResultExpandDTO parseByRequestResult(RequestResult baseResult) {
        //解析是否含有误报库信息
        ErrorReportLibraryParseDTO errorCodeDTO = ErrorReportLibraryUtil.parseAssertions(baseResult);
        RequestResult requestResult = errorCodeDTO.getResult();
        RequestResultExpandDTO expandDTO = new RequestResultExpandDTO();
        BeanUtils.copyBean(expandDTO, requestResult);

        if (CollectionUtils.isNotEmpty(errorCodeDTO.getErrorCodeList())) {
            Map<String, String> expandMap = new HashMap<>();
            expandMap.put(ExecuteResult.errorReportResult.name(), errorCodeDTO.getErrorCodeStr());
            if (StringUtils.equalsIgnoreCase(errorCodeDTO.getRequestStatus(), ExecuteResult.errorReportResult.name())) {
                expandMap.put("status", ExecuteResult.errorReportResult.name());
            }
            expandDTO.setAttachInfoMap(expandMap);
        }
        if (StringUtils.equalsIgnoreCase(errorCodeDTO.getRequestStatus(), ExecuteResult.errorReportResult.name())) {
            expandDTO.setStatus(errorCodeDTO.getRequestStatus());
        }
        return expandDTO;
    }
}
