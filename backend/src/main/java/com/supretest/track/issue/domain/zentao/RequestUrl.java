package com.supretest.track.issue.domain.zentao;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter
@Setter
public class RequestUrl {
    private String login;
    private String sessionGet;
    private String bugCreate;
    private String createMetaData;
    private String bugUpdate;
    private String bugList;
    private String bugDelete;
    private String bugGet;
    private String storyGet;
    private String userGet;
    private String buildsGet;
    private String fileUpload;
    private String replaceImgUrl;
    private String productGet;
    private Pattern imgPattern;
    private String productListGet;
    private String projectStatsGet;
    private String branchGet;
    private String treeGet;
    private String projectGet;
}
