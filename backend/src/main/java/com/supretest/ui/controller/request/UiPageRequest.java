package com.supretest.ui.controller.request;


import com.supretest.controller.request.BaseQueryRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UiPageRequest extends BaseQueryRequest {

    private String id;

    private String name;

    private List<String> pageIds;

    private String userId;

    private String nodeId;

    private String statusIsNot;

    private long createTime = 0;
    private long relevanceCreateTime = 0;

    private String moduleId;

}

