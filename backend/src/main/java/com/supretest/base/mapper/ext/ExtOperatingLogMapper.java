package com.supretest.base.mapper.ext;

import com.supretest.log.vo.OperatingLogDTO;
import com.supretest.log.vo.OperatingLogRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtOperatingLogMapper {

    List<OperatingLogDTO> list(@Param("request") OperatingLogRequest request);

    List<OperatingLogDTO> findBySourceId(@Param("request") OperatingLogRequest request);

    List<OperatingLogDTO> findBySourceIdEnv(@Param("request") OperatingLogRequest request);


}