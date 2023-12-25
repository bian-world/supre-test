package com.supretest.track.request.testcase;


import com.supretest.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserIssueRequest extends UserDTO.PlatformInfo {
    private String workspaceId;
    private String platform;
}
