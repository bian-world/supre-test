package com.supretest.api.service;

import com.supretest.base.domain.ApiDefinitionExecResult;
import com.supretest.base.mapper.ext.ExtApiDefinitionExecResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.Map;

@Service
public class ApiCaseResultService {
    @Resource
    private ExtApiDefinitionExecResultMapper resultMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void batchSave(Map<String, ApiDefinitionExecResult> executeQueue) {
        if (!executeQueue.isEmpty()) {
            resultMapper.sqlInsert(new LinkedList<>(executeQueue.values()));
        }
    }
}
