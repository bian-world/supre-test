package com.supretest.base.mapper.ext;

import com.supretest.base.domain.CustomFieldTemplate;
import com.supretest.dto.CustomFieldDao;
import com.supretest.dto.CustomFieldTemplateDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtCustomFieldTemplateMapper {
    List<String> getCustomFieldIds(@Param("templateId") String templateId);

    List<CustomFieldTemplateDao> list(@Param("request") CustomFieldTemplate request);

    List<CustomFieldDao> lisSimple(@Param("request") CustomFieldTemplate request);
}
