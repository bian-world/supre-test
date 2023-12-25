package com.supretest.ui.controller.request;

import com.supretest.base.domain.UiPageModule;
import com.supretest.ui.dto.UiPageModuleDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DragUiModuleRequest extends UiPageModule {

    List<String> nodeIds;
    UiPageModuleDTO nodeTree;
}