package com.supretest.track.request.testcase;

import com.supretest.base.domain.TestCaseWithBLOBs;
import com.supretest.controller.request.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestCaseBatchRequest extends TestCaseWithBLOBs {
    private List<String> ids;
    private List<OrderRequest> orders;
    private String projectId;
    private CustomFiledRequest customField;
    private QueryTestCaseRequest condition;
    private String customTemplateFieldId;

    @Getter
    @Setter
    public static class CustomFiledRequest {
        private String id;
        private String name;
        private Object value;
    }
}
