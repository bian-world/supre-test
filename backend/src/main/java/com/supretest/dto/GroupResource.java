package com.supretest.dto;

import lombok.Data;

@Data
public class GroupResource {
    private String id;
    private String name;
    private Boolean license = false;
}
