package com.supretest.base.mapper.ext;

import com.supretest.base.domain.MockExpectConfigWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtMockExpectConfigMapper {

    List<MockExpectConfigWithBLOBs> selectByProjectIdAndStatusIsOpen(String projectId);

    List<MockExpectConfigWithBLOBs> selectByApiId(String apiId);

    List<MockExpectConfigWithBLOBs> selectByApiIdIn(@Param("values") List<String> apiIds);

    List<String> selectExlectNumByMockConfigId(String mockConfigId);

    String selectApiNumberByMockConfigId(String mockConfigId);

}