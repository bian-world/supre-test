package com.supretest.controller.request;

import com.supretest.base.domain.CustomFieldTemplate;
import com.supretest.base.domain.IssueTemplate;
import lombok.Data;

import java.util.List;

@Data
public class UpdateIssueTemplateRequest extends IssueTemplate {
    List<CustomFieldTemplate> customFields;
}
