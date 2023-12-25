package com.supretest.controller.request;

import com.supretest.base.domain.CustomFieldTemplate;
import com.supretest.base.domain.TestCaseTemplateWithBLOBs;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCaseFieldTemplateRequest extends TestCaseTemplateWithBLOBs {
    List<CustomFieldTemplate> customFields;
}
