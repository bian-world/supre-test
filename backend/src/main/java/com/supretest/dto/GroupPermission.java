package com.supretest.dto;

import lombok.Data;

@Data
public class GroupPermission {
    private String id;
    private String name;
    private String resourceId;
    private Boolean checked = false;
    private Boolean license = false;
}
