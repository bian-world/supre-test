package com.supretest.api.dto.parse.postman;

import lombok.Data;
@Data
public class PostmanEvent {
    private String listen;
    private PostmanScript script;
}
