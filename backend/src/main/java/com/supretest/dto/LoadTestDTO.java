package com.supretest.dto;

import com.supretest.base.domain.LoadTest;
import com.supretest.base.domain.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoadTestDTO extends LoadTest {
    private String projectName;
    private String userName;
    private String versionName;
    private Schedule schedule;
    private Boolean isNeedUpdate;
    private List<String> follows;
}
