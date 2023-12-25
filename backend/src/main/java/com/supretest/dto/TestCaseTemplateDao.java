package com.supretest.dto;

import com.supretest.base.domain.TestCaseTemplateWithBLOBs;
import lombok.Data;

import java.util.List;

@Data
public class TestCaseTemplateDao extends TestCaseTemplateWithBLOBs {
    List<CustomFieldDao> customFields;
}
