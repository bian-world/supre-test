package com.supretest.base.mapper.ext;

import com.supretest.base.domain.Project;
import com.supretest.track.dto.TestCaseNodeDTO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExtTestCaseNodeMapper {

    List<TestCaseNodeDTO> getNodeTreeByProjectId(@Param("projectId") String projectId);

    List<TestCaseNodeDTO> getNodeTreeByProjectIds(@Param("projectIds") List<String> projectIds);

    TestCaseNodeDTO get(String id);

    void updatePos(String id, Double pos);

    List<String> getNodes(@Param("parentId") String parentId);

    List<TestCaseNodeDTO> getNodeTreeByIds(@Param("nodeIds") List<String> nodeIds);

    @MapKey("zentaoTreeId")
    Map<String, TestCaseNodeDTO> getZendaoTreeByProjectId(@Param("projectId") String projectId);
}
