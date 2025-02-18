package com.supretest.base.mapper.ext;

import com.supretest.task.dto.TaskCenterDTO;
import com.supretest.task.dto.TaskCenterRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtTaskMapper {

    List<TaskCenterDTO> getTasks(@Param("request") TaskCenterRequest request);

    int getRunningTasks(@Param("request") TaskCenterRequest request);

    List<TaskCenterDTO> getCases(@Param("id") String id);

    List<TaskCenterDTO> getScenario(@Param("id") String id);

    List<String> checkActuator (@Param("actuator") String actuator);

}