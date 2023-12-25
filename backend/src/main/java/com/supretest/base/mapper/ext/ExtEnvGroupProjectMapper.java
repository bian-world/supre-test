package com.supretest.base.mapper.ext;

import com.supretest.dto.EnvironmentGroupProjectDTO;

import java.util.List;

public interface ExtEnvGroupProjectMapper {

    List<EnvironmentGroupProjectDTO> getList(String groupId);
}
