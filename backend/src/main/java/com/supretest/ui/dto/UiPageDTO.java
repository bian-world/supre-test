package com.supretest.ui.dto;

import com.supretest.base.domain.UiPage;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UiPageDTO extends UiPage {

    private Integer elementTotal;

    private String subProjectName;

    private String userName;

    private String parentPageName;
}
