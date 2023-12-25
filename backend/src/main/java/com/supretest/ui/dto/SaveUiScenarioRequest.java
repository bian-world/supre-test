package com.supretest.ui.dto;

import com.supretest.ui.dto.definition.request.StUiScenario;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SaveUiScenarioRequest {

    private String id;

    private String projectId;

    private String tags;

    private String userId;

    private String moduleId;

    private String modulePath;

    private String name;

    private String level;

    private String status;

    private String principal;

    private Integer stepTotal;

    private String schedule;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String passRate;

    private String lastResult;

    private String reportId;

    private Integer num;

    private String originalState;

    private String customNum;

    private String createUser;

    private String useUrl;

    private Integer version;

    private Timestamp deleteTime;

    private String deleteUserId;

    private Timestamp executeTimes;

    private Long order;

    private String environmentType;

    private String environmentGroupId;

    private String environmentJson;

    private String versionId;

    private String refId;

    private Boolean latest;

    private String subProjectId;

    private List<String> follows;

    private String description;

    private StUiScenario scenarioDefinition;

    List<String> bodyFileRequestIds;

    List<String> scenarioFileIds;

    private List<String> scenarioIds;

    private boolean isSelectAllDate;

    private Map<String, List<String>> filters;

    private List<String> moduleIds;

    private List<String> unSelectIds;

}
