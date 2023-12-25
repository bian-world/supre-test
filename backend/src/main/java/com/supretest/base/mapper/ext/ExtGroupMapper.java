package com.supretest.base.mapper.ext;

import com.supretest.controller.request.group.EditGroupRequest;
import com.supretest.dto.GroupDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtGroupMapper {

    List<GroupDTO> getGroupList(@Param("request") EditGroupRequest request);


}
