package com.supretest.dto;

import com.supretest.base.domain.IssueTemplate;
import lombok.Data;

import java.util.List;

@Data
public class IssueTemplateDao extends IssueTemplate {
    List<CustomFieldDao> customFields;
}
