package com.supretest.api.dto;

import com.supretest.base.domain.ApiTest;
import com.supretest.base.domain.Schedule;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class APITestResult extends ApiTest {

    private String projectName;

    private String userName;

    private Schedule schedule;
}
