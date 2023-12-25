package com.supretest.base.mapper.ext;

import com.supretest.base.domain.Group;
import com.supretest.base.domain.User;
import com.supretest.controller.request.group.EditGroupRequest;
import com.supretest.controller.request.member.QueryMemberRequest;
import com.supretest.dto.RelatedSource;
import com.supretest.dto.UserGroupDTO;
import com.supretest.dto.UserGroupInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtUserGroupMapper {

    List<UserGroupDTO> getUserGroup(@Param("userId") String userId , @Param("projectId") String projectId);

    List<Group> getWorkspaceMemberGroups(@Param("workspaceId") String workspaceId, @Param("userId") String userId);

    List<User> getMemberList(@Param("member") QueryMemberRequest request);

    List<User> getProjectMemberList(@Param("request") QueryMemberRequest request);

    List<Group> getProjectMemberGroups(@Param("projectId") String projectId,@Param("userId") String userId);

    List<RelatedSource> getRelatedSource(@Param("userId") String userId);

    List<User> getGroupUser(@Param("request")EditGroupRequest request);

    int checkSourceRole(@Param("sourceId") String sourceId, @Param("userId") String userId, @Param("groupId") String groupId);

    List<UserGroupInfoDTO> getUserGroupInfo();
}
