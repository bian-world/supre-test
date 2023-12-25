package com.supretest.dto;

import com.supretest.base.domain.TestCaseTest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestCaseTestDao extends TestCaseTest {
    private String name;
    private String num;
    private String projectName;
    private String versionName;
}
