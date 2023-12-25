package com.supretest.ui.service;

import com.alibaba.fastjson.JSON;
import com.supretest.api.dto.definition.ApiDefinitionResult;
import com.supretest.api.dto.definition.ApiModuleDTO;
import com.supretest.api.dto.definition.DragModuleRequest;
import com.supretest.api.service.ApiDefinitionService;
import com.supretest.api.service.ApiTestCaseService;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.*;
import com.supretest.commons.constants.TestCaseConstants;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.i18n.Translator;
import com.supretest.log.utils.ReflexObjectUtil;
import com.supretest.log.vo.DetailColumn;
import com.supretest.log.vo.OperatingLogDetails;
import com.supretest.log.vo.api.ModuleReference;
import com.supretest.service.NodeTreeService;
import com.supretest.service.ProjectService;
import com.supretest.track.service.TestPlanApiCaseService;
import com.supretest.track.service.TestPlanProjectService;
import com.supretest.ui.controller.request.DragUiModuleRequest;
import com.supretest.ui.controller.request.UiPageRequest;
import com.supretest.ui.dto.UiPageDTO;
import com.supretest.ui.dto.UiPageModuleDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class UiModuleService extends NodeTreeService<UiPageModuleDTO> {

    @Resource
    UiPageModuleMapper uiPageModuleMapper;
    @Resource
    private UiPageMapper uiPageMapper;
    @Resource
    private UiScenarioMapper uiScenarioMapper;
    @Resource
    private ApiDefinitionMapper apiDefinitionMapper;
    @Resource
    private TestPlanProjectService testPlanProjectService;
    @Resource
    private ProjectService projectService;
    @Resource
    private TestPlanApiCaseService testPlanApiCaseService;
    @Resource
    private ApiTestCaseService apiTestCaseService;
    @Resource
    private ApiDefinitionService apiDefinitionService;

    @Resource
    SqlSessionFactory sqlSessionFactory;
    @Resource
    ProjectMapper projectMapper;

    public UiModuleService() {
        super(ApiModuleDTO.class);
    }

    public UiPageModule get(String id) {
        return uiPageModuleMapper.selectByPrimaryKey(id);
    }

    public List<UiPageModuleDTO> getNodeTreeByProjectId(String projectId, String subProjectId, String type, String versionId) {
        // 判断当前项目下是否有默认模块，没有添加默认模块
        this.getDefaultNode(projectId);
        List<UiPageModuleDTO> uiPageModules = uiPageModuleMapper.getNodeTreeByProjectId(projectId);
        UiPageRequest request = new UiPageRequest();
        request.setProjectId(projectId);
        request.setSubProjectId(subProjectId);
        List<String> list = new ArrayList<>();
        list.add("Prepare");
        list.add("Underway");
        list.add("Completed");
        Map<String, List<String>> filters = new LinkedHashMap<>();
        filters.put("status", list);
//        request.setFilters(filters);
//        apiModules.forEach(node -> {
//            List<String> moduleIds = new ArrayList<>();
//            moduleIds = this.nodeList(apiModules, node.getId(), moduleIds);
//            moduleIds.add(node.getId());
//            request.setModuleIds(moduleIds);
//            node.setCaseNum(extApiDefinitionMapper.moduleCount(request));
//        });

        //优化： 所有统计SQL一次查询出来
        List<String> allModuleIdList = new ArrayList<>();
        for (UiPageModuleDTO node : uiPageModules) {
            List<String> moduleIds = new ArrayList<>();
            moduleIds = this.nodeList(uiPageModules, node.getId(), moduleIds);
            moduleIds.add(node.getId());
            for (String moduleId : moduleIds) {
                if (!allModuleIdList.contains(moduleId)) {
                    allModuleIdList.add(moduleId);
                }
            }
        }
        request.setModuleIds(allModuleIdList);
        if (StringUtils.isNotBlank(versionId)) {
            request.setVersionId(versionId);
        }
        List<Map<String, Object>> moduleCountList = new ArrayList<>();
        if(StringUtils.isNotEmpty(type) && StringUtils.equals(type, "UIScenario")){
            moduleCountList = uiScenarioMapper.moduleCountByCollection(request);
        }
        else {
            moduleCountList = uiPageMapper.moduleCountByCollection(request);
        }
        Map<String, Integer> moduleCountMap = this.parseModuleCountList(moduleCountList);
        Project project = projectMapper.selectByPrimaryKey(projectId);
        uiPageModules.forEach(node -> {
            List<String> moduleIds = new ArrayList<>();
            moduleIds = this.nodeList(uiPageModules, node.getId(), moduleIds);
            moduleIds.add(node.getId());
            int countNum = 0;
            for (String moduleId : moduleIds) {
                if (moduleCountMap.containsKey(moduleId)) {
                    countNum += moduleCountMap.get(moduleId).intValue();
                }
            }
            node.setCaseNum(countNum);
            Boolean editable = StringUtils.equals(project.getPlatform(),"Zentao")?false:true;
            node.setIsTreeNodeEditable(editable);
        });
        return getNodeTrees(uiPageModules);
    }

    private Map<String, Integer> parseModuleCountList(List<Map<String, Object>> moduleCountList) {
        Map<String,Integer> returnMap = new HashMap<>();
        for (Map<String, Object> map: moduleCountList){
            Object moduleIdObj = map.get("moduleId");
            Object countNumObj = map.get("countNum");
            if(moduleIdObj!= null && countNumObj != null){
                String moduleId = String.valueOf(moduleIdObj);
                try {
                    Integer countNumInteger = new Integer(String.valueOf(countNumObj));
                    returnMap.put(moduleId,countNumInteger);
                }catch (Exception e){
                }
            }
        }
        return returnMap;
    }

    public static List<String> nodeList(List<UiPageModuleDTO> nodes, String pid, List<String> list) {
        for (UiPageModuleDTO node : nodes) {
            //遍历出父id等于参数的id，add进子节点集合
            if (StringUtils.equals(node.getParentId(), pid)) {
                list.add(node.getId());
                //递归遍历下一级
                nodeList(nodes, node.getId(), list);
            }
        }
        return list;
    }

    public String addNode(UiPageModule node) {
        validateNode(node);
        return addNodeWithoutValidate(node);
    }

    private double getNextLevelPos(String projectId, int level, String parentId) {
        List<UiPageModule> list = getPos(projectId, level, parentId, "pos desc");
        if (!CollectionUtils.isEmpty(list) && list.get(0) != null && list.get(0).getPos() != null) {
            return list.get(0).getPos() + DEFAULT_POS;
        } else {
            return DEFAULT_POS;
        }
    }

    private List<UiPageModule> getPos(String projectId, int level, String parentId, String order) {
        UiPageModuleExample example = new UiPageModuleExample();
        UiPageModuleExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId).andLevelEqualTo(level);
        if (level != 1 && StringUtils.isNotBlank(parentId)) {
            criteria.andParentIdEqualTo(parentId);
        }
        example.setOrderByClause(order);
        return uiPageModuleMapper.selectByExample(example);
    }

    public String addNodeWithoutValidate(UiPageModule node) {
        node.setCreateTime(System.currentTimeMillis());
        node.setUpdateTime(System.currentTimeMillis());
        node.setId(UUID.randomUUID().toString());
        if (StringUtils.isBlank(node.getCreateUser())) {
            node.setCreateUser(SessionUtils.getUserId());
        }
        double pos = getNextLevelPos(node.getProjectId(), node.getLevel(), node.getParentId());
        node.setPos(pos);
        uiPageModuleMapper.insertSelective(node);
        return node.getId();
    }

//    public List<ApiModuleDTO> getNodeByPlanId(String planId, String protocol) {
//        List<ApiModuleDTO> list = new ArrayList<>();
//        List<String> projectIds = testPlanProjectService.getProjectIdsByPlanId(planId);
//        projectIds.forEach(id -> {
//            Project project = projectService.getProjectById(id);
//            String name = project.getName();
//            List<ApiModuleDTO> nodeList = getNodeDTO(id, planId, protocol);
//            ApiModuleDTO apiModuleDTO = new ApiModuleDTO();
//            apiModuleDTO.setId(project.getId());
//            apiModuleDTO.setName(name);
//            apiModuleDTO.setLabel(name);
//            apiModuleDTO.setChildren(nodeList);
//            if (!org.springframework.util.CollectionUtils.isEmpty(nodeList)) {
//                list.add(apiModuleDTO);
//            }
//        });
//        return list;
//    }

//    private List<ApiModuleDTO> getNodeDTO(String projectId, String planId, String protocol) {
//        List<TestPlanApiCase> apiCases = testPlanApiCaseService.getCasesByPlanId(planId);
//        if (apiCases.isEmpty()) {
//            return null;
//        }
//        List<ApiModuleDTO> testCaseNodes = extApiModuleMapper.getNodeTreeByProjectId(projectId, protocol);
//
//        List<String> caseIds = apiCases.stream()
//                .map(TestPlanApiCase::getApiCaseId)
//                .collect(Collectors.toList());
//
//        List<String> definitionIds = apiTestCaseService.selectCasesBydIds(caseIds).stream()
//                .map(ApiTestCase::getApiDefinitionId)
//                .collect(Collectors.toList());
//
//        List<String> dataNodeIds = apiDefinitionService.selectApiDefinitionBydIds(definitionIds).stream()
//                .map(ApiDefinition::getModuleId)
//                .collect(Collectors.toList());
//
//        List<ApiModuleDTO> nodeTrees = getNodeTrees(testCaseNodes);
//
//        Iterator<ApiModuleDTO> iterator = nodeTrees.iterator();
//        while (iterator.hasNext()) {
//            ApiModuleDTO rootNode = iterator.next();
//            if (pruningTree(rootNode, dataNodeIds)) {
//                iterator.remove();
//            }
//        }
//        return nodeTrees;
//    }


    public UiPageModule getNewModule(String name, String projectId, int level) {
        UiPageModule node = new UiPageModule();
        buildNewModule(node);
        node.setLevel(level);
        node.setName(name);
        node.setProjectId(projectId);
        return node;
    }

    public UiPageModule buildNewModule(UiPageModule node) {
        node.setCreateTime(System.currentTimeMillis());
        node.setUpdateTime(System.currentTimeMillis());
        node.setId(UUID.randomUUID().toString());
        return node;
    }

    private void validateNode(UiPageModule node) {
        if (node.getLevel() > TestCaseConstants.MAX_NODE_DEPTH) {
            MSException.throwException(Translator.get("test_case_node_level_tip")
                    + TestCaseConstants.MAX_NODE_DEPTH + Translator.get("test_case_node_level"));
        }
        checkApiModuleExist(node);
    }

    private void checkApiModuleExist(UiPageModule node) {
        if (node.getName() != null) {
            UiPageModuleExample example = new UiPageModuleExample();
            UiPageModuleExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(node.getName())
                    .andProjectIdEqualTo(node.getProjectId());
            if (StringUtils.isNotBlank(node.getParentId())) {
                criteria.andParentIdEqualTo(node.getParentId());
            } else {
                criteria.andParentIdIsNull();
            }
            if (StringUtils.isNotBlank(node.getId())) {
                criteria.andIdNotEqualTo(node.getId());
            }
            if (uiPageModuleMapper.selectByExample(example).size() > 0) {
                MSException.throwException(Translator.get("test_case_module_already_exists") + ": " + node.getName());
            }
        }
    }

    public List<UiPageModule> selectSameModule(UiPageModule node) {
        UiPageModuleExample example = new UiPageModuleExample();
        UiPageModuleExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(node.getName())
                .andProjectIdEqualTo(node.getProjectId());
        if (StringUtils.isNotBlank(node.getParentId())) {
            criteria.andParentIdEqualTo(node.getParentId());
        } else {
            criteria.andParentIdIsNull();
        }
        if (StringUtils.isNotBlank(node.getId())) {
            criteria.andIdNotEqualTo(node.getId());
        }
        return uiPageModuleMapper.selectByExample(example);
    }

    private List<UiPage> queryByModuleIds(List<String> nodeIds) {
        UiPageRequest uiPageRequest = new UiPageRequest();
        uiPageRequest.setModuleIds(nodeIds);
        return uiPageMapper.listAll(uiPageRequest);
    }

    public int editNode(DragUiModuleRequest request) {
        request.setUpdateTime(System.currentTimeMillis());
        if(StringUtils.isEmpty(request.getZentaoTreeId())) {
            checkApiModuleExist(request);
        }
        List<UiPage> uiPages = queryByModuleIds(request.getNodeIds());
        if (CollectionUtils.isNotEmpty(uiPages)) {
            uiPages.forEach(uiPage -> {
                if (uiPage != null && StringUtils.isNotBlank(uiPage.getModulePath())) {
                    StringBuilder path = new StringBuilder(uiPage.getModulePath());
                    List<String> pathLists = Arrays.asList(path.toString().split("/"));
                    if (pathLists.size() > request.getLevel()) {
                        pathLists.set(request.getLevel(), request.getName());
                        path.delete(0, path.length());
                        for (int i = 1; i < pathLists.size(); i++) {
                            path = path.append("/").append(pathLists.get(i));
                        }
                        uiPage.setModulePath(path.toString());
                    }
                }
            });
            batchUpdateApiDefinition(uiPages);
        }
        return uiPageModuleMapper.updateByPrimaryKeySelective(request);
    }

//    public int deleteNode(List<String> nodeIds) {
//        ApiDefinitionExampleWithOperation apiDefinitionExample = new ApiDefinitionExampleWithOperation();
//        apiDefinitionExample.createCriteria().andModuleIdIn(nodeIds);
//        apiDefinitionExample.setOperator(SessionUtils.getUserId());
//        apiDefinitionExample.setOperationTime(System.currentTimeMillis());
//        apiDefinitionService.removeToGcByExample(apiDefinitionExample);
////        extApiDefinitionMapper.removeToGcByExample(apiDefinitionExample);   //  删除模块，则模块下的接口放入回收站
//
//        ApiModuleExample apiDefinitionNodeExample = new ApiModuleExample();
//        apiDefinitionNodeExample.createCriteria().andIdIn(nodeIds);
//        return uiPageModuleMapper.deleteByExample(apiDefinitionNodeExample);
//    }

    private void batchUpdateApiDefinition(List<UiPage> uiPages) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UiPageMapper uiPageMapper = sqlSession.getMapper(UiPageMapper.class);
        uiPages.forEach((value) -> {
            uiPageMapper.updateByPrimaryKeySelective(value);
        });
        sqlSession.flushStatements();
        if (sqlSession != null && sqlSessionFactory != null) {
            SqlSessionUtils.closeSqlSession(sqlSession, sqlSessionFactory);
        }
    }

    @Override
    public UiPageModuleDTO getNode(String id) {
        UiPageModule module = uiPageModuleMapper.selectByPrimaryKey(id);
        if (module == null) {
            return null;
        }
        UiPageModuleDTO dto = JSON.parseObject(JSON.toJSONString(module), UiPageModuleDTO.class);
        return dto;
    }

    @Override
    public void updatePos(String id, Double pos) {
        uiPageModuleMapper.updatePos(id, pos);
    }

    public void dragNode(DragUiModuleRequest request) {

        checkApiModuleExist(request);

        List<String> nodeIds = request.getNodeIds();

        List<UiPage> uiPage = queryByModuleIds(nodeIds);

        UiPageModuleDTO nodeTree = request.getNodeTree();

        List<UiPageModule> updateNodes = new ArrayList<>();
        if (nodeTree == null) {
            return;
        }
        buildUpdateDefinition(nodeTree, uiPage, updateNodes, "/", "0", 1);

        updateNodes = updateNodes.stream()
                .filter(item -> nodeIds.contains(item.getId()))
                .collect(Collectors.toList());

        batchUpdateModule(updateNodes);

        batchUpdateApiDefinition(uiPage);
    }

    private void buildUpdateDefinition(UiPageModuleDTO rootNode, List<UiPage> uiPages,
                                       List<UiPageModule> updateNodes, String rootPath, String pId, int level) {
        rootPath = rootPath + rootNode.getName();

        if (level > 8) {
            MSException.throwException(Translator.get("node_deep_limit"));
        }
        if ("root".equals(rootNode.getId())) {
            rootPath = "";
        }
        UiPageModule uiPageNode = new UiPageModule();
        uiPageNode.setId(rootNode.getId());
        uiPageNode.setLevel(level);
        uiPageNode.setParentId(pId);
        updateNodes.add(uiPageNode);

        for (UiPage item : uiPages) {
            if (StringUtils.equals(item.getModuleId(), rootNode.getId())) {
                item.setModulePath(rootPath);
            }
        }

        List<UiPageModuleDTO> children = rootNode.getChildren();
        if (children != null && children.size() > 0) {
            for (int i = 0; i < children.size(); i++) {
                buildUpdateDefinition(children.get(i), uiPages, updateNodes, rootPath + '/', rootNode.getId(), level + 1);
            }
        }
    }

    private void batchUpdateModule(List<UiPageModule> updateNodes) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UiPageModuleMapper apiModuleMapper = sqlSession.getMapper(UiPageModuleMapper.class);
        updateNodes.forEach((value) -> {
            apiModuleMapper.updateByPrimaryKeySelective(value);
        });
        sqlSession.flushStatements();
        if (sqlSession != null && sqlSessionFactory != null) {
            SqlSessionUtils.closeSqlSession(sqlSession, sqlSessionFactory);
        }
    }

    public UiPageModule getModuleByName(String projectId) {
        UiPageModuleExample example = new UiPageModuleExample();
        UiPageModuleExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo("bug")
                .andProjectIdEqualTo(projectId);
        List<UiPageModule> modules = uiPageModuleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(modules)) {
            return modules.get(0);
        } else {
            UiPageModule node = new UiPageModule();
            node.setName("bug");
            node.setLevel(1);
            node.setPos(0.0);
            node.setParentId(null);
            node.setProjectId(projectId);
            node.setCreateTime(System.currentTimeMillis());
            node.setUpdateTime(System.currentTimeMillis());
            node.setId(UUID.randomUUID().toString());
            node.setCreateUser(SessionUtils.getUserId());
            uiPageModuleMapper.insertSelective(node);
            return node;
        }
    }

    public String getLogDetails(List<String> ids) {
        UiPageModuleExample example = new UiPageModuleExample();
        UiPageModuleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        List<UiPageModule> nodes = uiPageModuleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(nodes)) {
            List<String> names = nodes.stream().map(UiPageModule::getName).collect(Collectors.toList());
            OperatingLogDetails details = new OperatingLogDetails(JSON.toJSONString(ids), nodes.get(0).getProjectId(), String.join(",", names), nodes.get(0).getCreateUser(), new LinkedList<>());
            return JSON.toJSONString(details);
        }
        return null;
    }

    public String getLogDetails(UiPageModule node) {
        UiPageModule module = null;
        if (StringUtils.isNotEmpty(node.getId())) {
            module = uiPageModuleMapper.selectByPrimaryKey(node.getId());
        }
        if (module == null && StringUtils.isNotEmpty(node.getName())) {
            UiPageModuleExample example = new UiPageModuleExample();
            UiPageModuleExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(node.getName()).andProjectIdEqualTo(node.getProjectId());
            if (StringUtils.isNotEmpty(node.getParentId())) {
                criteria.andParentIdEqualTo(node.getParentId());
            } else {
                criteria.andParentIdIsNull();
            }
            if (StringUtils.isNotEmpty(node.getId())) {
                criteria.andIdNotEqualTo(node.getId());
            }
            List<UiPageModule> list = uiPageModuleMapper.selectByExample(example);
            if (CollectionUtils.isNotEmpty(list)) {
                module = list.get(0);
            }
        }
        if (module != null) {
            List<DetailColumn> columns = ReflexObjectUtil.getColumns(module, ModuleReference.moduleColumns);
            OperatingLogDetails details = new OperatingLogDetails(JSON.toJSONString(module.getId()), module.getProjectId(), module.getCreateUser(), columns);
            return JSON.toJSONString(details);
        }
        return null;
    }

    public long countById(String nodeId) {
        UiPageModuleExample example = new UiPageModuleExample();
        example.createCriteria().andIdEqualTo(nodeId);
        return  uiPageModuleMapper.countByExample(example);
    }

    public UiPageModule getDefaultNode(String projectId) {
        UiPageModuleExample example = new UiPageModuleExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andNameEqualTo("未规划场景").andParentIdIsNull();
        List<UiPageModule> list = uiPageModuleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            UiPageModule record = new UiPageModule();
            record.setId(UUID.randomUUID().toString());
            record.setName("未规划场景");
            record.setPos(1.0);
            record.setLevel(1);
            record.setCreateTime(System.currentTimeMillis());
            record.setUpdateTime(System.currentTimeMillis());
            record.setProjectId(projectId);
            record.setCreateUser(SessionUtils.getUserId());
            uiPageModuleMapper.insert(record);
            return record;
        }else {
            return list.get(0);
        }
    }

//    public ApiModule getDefaultNodeUnCreateNew(String projectId,String protocol) {
//        ApiModuleExample example = new ApiModuleExample();
//        example.createCriteria().andProjectIdEqualTo(projectId).andProtocolEqualTo(protocol).andNameEqualTo("未规划接口").andParentIdIsNull();
//        List<ApiModule> list = uiPageModuleMapper.selectByExample(example);
//        if (CollectionUtils.isEmpty(list)) {
//            return null;
//        } else {
//            return list.get(0);
//        }
//    }

//    public long countTrashApiData(String projectId, String protocol) {
//        ApiDefinitionExample example = new ApiDefinitionExample();
//        example.createCriteria().andProjectIdEqualTo(projectId).andProtocolEqualTo(protocol).andStatusEqualTo("Trash");
//        return extApiDefinitionMapper.countByExample(example);
//    }

//    public String getModuleNameById(String moduleId) {
//        return extApiModuleMapper.getNameById(moduleId);
//    }
}
