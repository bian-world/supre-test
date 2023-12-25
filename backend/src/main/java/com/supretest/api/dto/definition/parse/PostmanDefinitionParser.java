package com.supretest.api.dto.definition.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.supretest.api.dto.ApiTestImportRequest;
import com.supretest.api.dto.definition.request.sampler.MsHTTPSamplerProxy;
import com.supretest.api.dto.parse.postman.PostmanCollection;
import com.supretest.api.dto.parse.postman.PostmanItem;
import com.supretest.api.dto.parse.postman.PostmanKeyValue;
import com.supretest.api.parse.PostmanAbstractParserParser;
import com.supretest.base.domain.ApiDefinitionWithBLOBs;
import com.supretest.base.domain.ApiModule;
import com.supretest.base.domain.ApiTestCaseWithBLOBs;
import com.supretest.base.domain.Project;
import com.supretest.base.mapper.ProjectMapper;
import com.supretest.commons.constants.ProjectApplicationType;
import com.supretest.commons.utils.BeanUtils;
import com.supretest.commons.utils.CommonBeanFactory;
import com.supretest.dto.ProjectConfig;
import com.supretest.service.ProjectApplicationService;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.*;

public class PostmanDefinitionParser extends PostmanAbstractParserParser<ApiDefinitionImport> {

    private ApiModule selectModule;

    private String selectModulePath;

    @Override
    public ApiDefinitionImport parse(InputStream source, ApiTestImportRequest request) {
        String testStr = getApiTestStr(source);
        this.projectId = request.getProjectId();
        PostmanCollection postmanCollection = JSON.parseObject(testStr, PostmanCollection.class, Feature.DisableSpecialKeyDetect);
        List<PostmanKeyValue> variables = postmanCollection.getVariable();
        ApiDefinitionImport apiImport = new ApiDefinitionImport();
        List<ApiDefinitionWithBLOBs> results = new ArrayList<>();
        this.selectModule = ApiDefinitionImportUtil.getSelectModule(request.getModuleId());
        if (this.selectModule != null) {
            this.selectModulePath = ApiDefinitionImportUtil.getSelectModulePath(this.selectModule.getName(), this.selectModule.getParentId());
        }

        ApiModule apiModule = ApiDefinitionImportUtil.buildModule(this.selectModule, postmanCollection.getInfo().getName(), this.projectId);
        List<ApiTestCaseWithBLOBs> cases = new ArrayList<>();
        Map<String, String> repeatMap = new HashMap();
        ProjectMapper projectMapper = CommonBeanFactory.getBean(ProjectMapper.class);
        Project project = projectMapper.selectByPrimaryKey(request.getProjectId());
        ProjectApplicationService projectApplicationService = CommonBeanFactory.getBean(ProjectApplicationService.class);
        ProjectConfig config = projectApplicationService.getSpecificTypeValue(project.getId(), ProjectApplicationType.URL_REPEATABLE.name());
        boolean urlRepeat = config.getUrlRepeatable();
        parseItem(postmanCollection.getItem(), variables, results,
                apiModule, apiModule.getName(), cases, repeatMap, urlRepeat);
        Collections.reverse(results); // 调整顺序
        Collections.reverse(cases);
        apiImport.setData(results);
        apiImport.setCases(cases);
        return apiImport;
    }

    protected void parseItem(List<PostmanItem> items, List<PostmanKeyValue> variables, List<ApiDefinitionWithBLOBs> results,
                             ApiModule parentModule, String path, List<ApiTestCaseWithBLOBs> cases, Map<String, String> repeatMap, Boolean repeatable) {
        for (PostmanItem item : items) {
            List<PostmanItem> childItems = item.getItem();
            if (childItems != null) {
                ApiModule module = null;
                module = ApiDefinitionImportUtil.buildModule(parentModule, item.getName(), this.projectId);
                parseItem(childItems, variables, results, module,  path + "/" + module.getName(), cases, repeatMap, repeatable);
            } else {
                MsHTTPSamplerProxy msHTTPSamplerProxy = parsePostman(item);
                ApiDefinitionWithBLOBs request = buildApiDefinition(msHTTPSamplerProxy.getId(), msHTTPSamplerProxy.getName(),
                        msHTTPSamplerProxy.getPath(), msHTTPSamplerProxy.getMethod(), new ApiTestImportRequest());
                request.setPath(msHTTPSamplerProxy.getPath());
                request.setRequest(JSON.toJSONString(msHTTPSamplerProxy));
                if (parentModule != null) {
                    request.setModuleId(parentModule.getId());
                    if (StringUtils.isNotBlank(this.selectModulePath)) {
                        request.setModulePath(this.selectModulePath + "/" + path);
                    } else {
                        request.setModulePath("/" + path);
                    }
                }
                if (request != null) {
                    if (repeatMap.keySet().contains(request.getMethod() + request.getPath())
                            && (repeatable == null || repeatable == false)) {
                        ApiTestCaseWithBLOBs apiTestCase =  new ApiTestCaseWithBLOBs();
                        BeanUtils.copyBean(apiTestCase, request);
                        apiTestCase.setApiDefinitionId(repeatMap.get(request.getMethod() + request.getPath()));
                        apiTestCase.setPriority("P0");
                        cases.add(apiTestCase);
                    } else {
                        repeatMap.put(request.getMethod() + request.getPath(), request.getId());
                        results.add(request);
                    }
                }
            }
        }
    }
}
