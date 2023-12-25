package com.supretest.ui.dto;

import com.supretest.base.domain.UiElement;
import com.supretest.base.domain.UiPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UiPageElementsDTO extends UiPageDTO {
    private Integer elementTotal;

    private List<UiElementDTO> uiElementDTOS;
}
