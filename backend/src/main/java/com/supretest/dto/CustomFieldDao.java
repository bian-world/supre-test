package com.supretest.dto;

import com.supretest.base.domain.CustomField;
import lombok.Data;

@Data
public class CustomFieldDao extends CustomField {
    private Boolean required;

    private Integer order;

    private String defaultValue;

    private String customData;

    private String key;
}
