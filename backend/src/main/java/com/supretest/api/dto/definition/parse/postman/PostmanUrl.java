package com.supretest.api.dto.definition.parse.postman;

import lombok.Data;

import java.util.List;

@Data
public class PostmanUrl {

    private String raw;
    private String protocol;
    private String port;
    private List<PostmanKeyValue> query;
}
