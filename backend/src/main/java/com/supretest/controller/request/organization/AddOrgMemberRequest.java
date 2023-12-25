package com.supretest.controller.request.organization;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddOrgMemberRequest {

    private List<String> userIds;
    private List<String> roleIds;
    private List<String> groupIds;
}
