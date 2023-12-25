package com.supretest.ui.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.supretest.api.service.MsHashTreeService;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.ApiScenarioReferenceIdMapper;
import com.supretest.base.mapper.UiScenarioReferenceMapper;
import com.supretest.base.mapper.ext.ExtApiScenarioReferenceIdMapper;
import com.supretest.commons.utils.SessionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class UiScenarioReferenceIdService {

//    @Resource
//    private ApiScenarioReferenceIdMapper apiScenarioReferenceIdMapper;

    @Resource
    private UiScenarioReferenceMapper uiScenarioReferenceMapper;

//    @Resource
//    private ExtApiScenarioReferenceIdMapper extApiScenarioReferenceIdMapper;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    public List<UiScenarioReference> findByReferenceIds(List<String> deleteIds) {
        if (CollectionUtils.isEmpty(deleteIds)) {
            return new ArrayList<>(0);
        } else {
            UiScenarioReferenceExample example = new UiScenarioReferenceExample();
            example.createCriteria().andReferenceIdIn(deleteIds);
            return uiScenarioReferenceMapper.selectByExample(example);
        }
    }

    public void deleteByScenarioId(String scenarioId) {
        UiScenarioReferenceExample example = new UiScenarioReferenceExample();
        example.createCriteria().andUiScenarioIdEqualTo(scenarioId);
        uiScenarioReferenceMapper.deleteByExample(example);
    }

    public void deleteByScenarioIds(List<String> scenarioIds) {
        UiScenarioReferenceExample example = new UiScenarioReferenceExample();
        example.createCriteria().andUiScenarioIdIn(scenarioIds);
        uiScenarioReferenceMapper.deleteByExample(example);
    }

    public void saveApiAndScenarioRelation(UiScenarioWithBLOBs scenario) {
        if (scenario.getId() == null) {
            return;
        }
        this.deleteByScenarioId(scenario.getId());
        List<UiScenarioReference> savedList = this.getApiAndScenarioRelation(scenario);
        this.insertUiScenarioReferenceIds(savedList);
    }

    public void saveApiAndScenarioRelation(List<UiScenarioWithBLOBs> scenarios) {
        if (CollectionUtils.isNotEmpty(scenarios)) {
            List<String> idList = new ArrayList<>(scenarios.size());
            LinkedList<UiScenarioReference> savedList = new LinkedList<>();
            scenarios.forEach(scenario -> {
                if (StringUtils.isNotEmpty(scenario.getId())) {
                    idList.add(scenario.getId());
                    savedList.addAll(this.getApiAndScenarioRelation(scenario));
                }
            });
            if (CollectionUtils.isNotEmpty(idList)) {
                UiScenarioReferenceExample example = new UiScenarioReferenceExample();
                example.createCriteria().andUiScenarioIdIn(idList);
                uiScenarioReferenceMapper.deleteByExample(example);
            }
            this.insertUiScenarioReferenceIds(savedList);
        }
    }

    public void insertUiScenarioReferenceIds(List<UiScenarioReference> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            UiScenarioReferenceMapper referenceIdMapper = sqlSession.getMapper(UiScenarioReferenceMapper.class);
            for (UiScenarioReference uiScenarioReference : list) {
                referenceIdMapper.insert(uiScenarioReference);
            }
            sqlSession.flushStatements();
            if (sqlSession != null && sqlSessionFactory != null) {
                SqlSessionUtils.closeSqlSession(sqlSession, sqlSessionFactory);
            }
        }
    }

    public List<UiScenarioReference> getApiAndScenarioRelation(UiScenarioWithBLOBs scenario) {
        List<UiScenarioReference> returnList = new ArrayList<>();
        if (StringUtils.isNotEmpty(scenario.getScenarioDefinition())) {
            JSONObject jsonObject = JSONObject.parseObject(scenario.getScenarioDefinition(), Feature.DisableSpecialKeyDetect);
            if (!jsonObject.containsKey(MsHashTreeService.HASH_TREE)) {
                return returnList;
            }
            JSONArray hashTree = jsonObject.getJSONArray(MsHashTreeService.HASH_TREE);
            for (int index = 0; index < hashTree.size(); index++) {
                JSONObject item = hashTree.getJSONObject(index);
                if (item == null) {
                    continue;
                }

                if (item.containsKey(MsHashTreeService.ID) && item.containsKey(MsHashTreeService.REFERENCED)) {
                    String url = null;
                    String method = null;
                    if (item.containsKey(MsHashTreeService.PATH) && StringUtils.isNotEmpty(MsHashTreeService.PATH)) {
                        url = item.getString(MsHashTreeService.PATH);
                    } else if (item.containsKey(MsHashTreeService.URL)) {
                        url = item.getString(MsHashTreeService.URL);
                    }
                    if (item.containsKey(MsHashTreeService.METHOD)) {
                        method = item.getString(MsHashTreeService.METHOD);
                    }
                    UiScenarioReference saveItem = new UiScenarioReference();
                    saveItem.setId(UUID.randomUUID().toString());
                    saveItem.setUiScenarioId(scenario.getId());
                    saveItem.setReferenceId(item.getString(MsHashTreeService.ID));
                    saveItem.setReferenceType(item.getString(MsHashTreeService.REFERENCED));
                    saveItem.setDataType(item.getString(MsHashTreeService.REF_TYPE));
//                    saveItem.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    saveItem.setCreateUserId(SessionUtils.getUserId());
                    returnList.add(saveItem);
                }
                if (item.containsKey(MsHashTreeService.HASH_TREE)) {
                    returnList.addAll(this.deepElementRelation(scenario.getId(), item.getJSONArray(MsHashTreeService.HASH_TREE)));
                }
            }
        }
        if (CollectionUtils.isEmpty(returnList)) {
            UiScenarioReference saveItem = new UiScenarioReference();
            saveItem.setId(UUID.randomUUID().toString());
            saveItem.setUiScenarioId(scenario.getId());
//            saveItem.setCreateTime(System.currentTimeMillis());
            saveItem.setCreateUserId(SessionUtils.getUserId());
            returnList.add(saveItem);
        }
        return returnList;
    }

    public List<UiScenarioReference> deepElementRelation(String scenarioId, JSONArray hashTree) {
        List<UiScenarioReference> deepRelations = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(hashTree)) {
            for (int index = 0; index < hashTree.size(); index++) {
                JSONObject item = hashTree.getJSONObject(index);
                if (item.containsKey(MsHashTreeService.ID) && item.containsKey(MsHashTreeService.REFERENCED)) {
                    String method = null;
                    String url = null;
                    if (item.containsKey(MsHashTreeService.PATH) && StringUtils.isNotEmpty(MsHashTreeService.PATH)) {
                        url = item.getString(MsHashTreeService.PATH);
                    } else if (item.containsKey(MsHashTreeService.URL)) {
                        url = item.getString(MsHashTreeService.URL);
                    }
                    if (item.containsKey(MsHashTreeService.METHOD)) {
                        method = item.getString(MsHashTreeService.METHOD);
                    }
                    UiScenarioReference saveItem = new UiScenarioReference();
                    saveItem.setId(UUID.randomUUID().toString());
                    saveItem.setUiScenarioId(scenarioId);
                    saveItem.setReferenceId(item.getString(MsHashTreeService.ID));
                    saveItem.setReferenceType(item.getString(MsHashTreeService.REFERENCED));
                    saveItem.setDataType(item.getString(MsHashTreeService.REF_TYPE));
//                    saveItem.setCreateTime(System.currentTimeMillis());
                    saveItem.setCreateUserId(SessionUtils.getUserId());
                    deepRelations.add(saveItem);
                }
                if (item.containsKey(MsHashTreeService.HASH_TREE)) {
                    deepRelations.addAll(this.deepElementRelation(scenarioId, item.getJSONArray(MsHashTreeService.HASH_TREE)));
                }
            }
        }
        return deepRelations;
    }

    public List<UiScenarioReference> findByReferenceIdsAndRefType(List<String> deleteIds, String referenceType) {
        if (CollectionUtils.isEmpty(deleteIds)) {
            return new ArrayList<>(0);
        } else {
            UiScenarioReferenceExample example = new UiScenarioReferenceExample();
            example.createCriteria().andReferenceIdIn(deleteIds).andReferenceTypeEqualTo(referenceType);
            return uiScenarioReferenceMapper.selectByExample(example);
        }
    }

//    public List<ApiScenarioReferenceId> selectUrlByProjectId(String projectId, String subProjectId) {
//        return extApiScenarioReferenceIdMapper.selectUrlByProjectId(projectId, subProjectId);
//    }
}
