package com.supretest.base.mapper;

import com.supretest.base.domain.ProjectApplication;
import com.supretest.base.domain.ProjectApplicationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectApplicationMapper {
    long countByExample(ProjectApplicationExample example);

    int deleteByExample(ProjectApplicationExample example);

    int insert(ProjectApplication record);

    int insertSelective(ProjectApplication record);

    List<ProjectApplication> selectByExample(ProjectApplicationExample example);

    int updateByExampleSelective(@Param("record") ProjectApplication record, @Param("example") ProjectApplicationExample example);

    int updateByExample(@Param("record") ProjectApplication record, @Param("example") ProjectApplicationExample example);
}