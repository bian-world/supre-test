package com.supretest.base.mapper;

import com.supretest.base.domain.ProjectVersion;
import com.supretest.base.domain.ProjectVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectVersionMapper {
    long countByExample(ProjectVersionExample example);

    int deleteByExample(ProjectVersionExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectVersion record);

    int insertSelective(ProjectVersion record);

    List<ProjectVersion> selectByExample(ProjectVersionExample example);

    ProjectVersion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectVersion record, @Param("example") ProjectVersionExample example);

    int updateByExample(@Param("record") ProjectVersion record, @Param("example") ProjectVersionExample example);

    int updateByPrimaryKeySelective(ProjectVersion record);

    int updateByPrimaryKey(ProjectVersion record);
}