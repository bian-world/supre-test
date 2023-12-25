package com.supretest.api.service;

import com.supretest.base.domain.*;
import com.supretest.base.mapper.ApiDefinitionMapper;
import com.supretest.base.mapper.ApiModuleMapper;
import com.supretest.base.mapper.ApiScenarioMapper;
import com.supretest.base.mapper.ApiScenarioModuleMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
/**
 * 接口测试所有级联删除资源
 */
public class ApiTestDelService {
    @Resource
    private ApiDefinitionService apiDefinitionService;
    @Resource
    private ApiAutomationService apiAutomationService;
    @Resource
    private ApiScenarioMapper apiScenarioMapper;
    @Resource
    private ApiDefinitionMapper apiDefinitionMapper;
    @Resource
    private ApiModuleMapper apiModuleMapper;
    @Resource
    private ApiScenarioModuleMapper apiScenarioModuleMapper;

    public void delete(String projectId) {
        ApiDefinitionExample example = new ApiDefinitionExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        // api and case
        List<String> apiIds = apiDefinitionMapper.selectByExample(example).stream().map(ApiDefinition::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(apiIds)) {
            apiDefinitionService.deleteBatch(apiIds);
        }
        // scenario
        ApiScenarioExample scenarioExample = new ApiScenarioExample();
        scenarioExample.createCriteria().andProjectIdEqualTo(projectId);
        List<String> scenarios = apiScenarioMapper.selectByExample(scenarioExample).stream().map(ApiScenario::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(scenarios)) {
            apiAutomationService.deleteBatch(scenarios);
        }
        // api module
        ApiModuleExample moduleExample = new ApiModuleExample();
        moduleExample.createCriteria().andProjectIdEqualTo(projectId);
        apiModuleMapper.deleteByExample(moduleExample);

        // scenario module
        ApiScenarioModuleExample scenarioModuleExample = new ApiScenarioModuleExample();
        scenarioModuleExample.createCriteria().andProjectIdEqualTo(projectId);
        apiScenarioModuleMapper.deleteByExample(scenarioModuleExample);

    }
}
