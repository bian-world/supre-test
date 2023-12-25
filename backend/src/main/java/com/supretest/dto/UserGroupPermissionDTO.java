package com.supretest.dto;

import com.supretest.base.domain.Group;
import com.supretest.base.domain.UserGroup;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserGroupPermissionDTO {
    List<GroupResourceDTO> list = new ArrayList<>();
    List<Group> groups = new ArrayList<>();
    List<UserGroup> userGroups = new ArrayList<>();
}
