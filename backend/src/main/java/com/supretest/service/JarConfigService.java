package com.supretest.service;

import com.alibaba.fastjson.JSON;
import com.supretest.api.jmeter.NewDriverManager;
import com.supretest.base.domain.JarConfig;
import com.supretest.base.domain.JarConfigExample;
import com.supretest.base.mapper.JarConfigMapper;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.FileUtils;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.i18n.Translator;
import com.supretest.log.utils.ReflexObjectUtil;
import com.supretest.log.vo.DetailColumn;
import com.supretest.log.vo.OperatingLogDetails;
import com.supretest.log.vo.system.SystemReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class JarConfigService {

    private static final String JAR_FILE_DIR = "/opt/supretest/data/jar";

    @Resource
    private JarConfigMapper jarConfigMapper;

    public List<JarConfig> list() {
        JarConfigExample example = new JarConfigExample();
        return jarConfigMapper.selectByExample(example);
    }

    public List<JarConfig> list(JarConfig jarConfig) {
        JarConfigExample example = new JarConfigExample();
        if (StringUtils.isNotBlank(jarConfig.getName())) {
            example.createCriteria().andNameLike("%" + jarConfig.getName() + "%");
        }
        example.setOrderByClause("update_time desc");
        return jarConfigMapper.selectByExample(example);
    }

    public List<JarConfig> searchList(JarConfig jarConfig) {
        JarConfigExample nameExample = new JarConfigExample();
        JarConfigExample jarExample = new JarConfigExample();
        if (StringUtils.isNotBlank(jarConfig.getName())) {
            nameExample.createCriteria().andNameLike("%" + jarConfig.getName() + "%");
            jarExample.createCriteria().andFileNameLike("%" + jarConfig.getName() + "%");
        }   //  根据jar包的文件名和自定义名称查找
        nameExample.setOrderByClause("update_time desc");
        jarExample.setOrderByClause("update_time desc");
        List<JarConfig> jarConfigList = jarConfigMapper.selectByExample(jarExample);
        //  合并两个查找结果并去重，按时间降序
        jarConfigList.addAll(jarConfigMapper.selectByExample(nameExample));
        jarConfigList = jarConfigList.stream().distinct().collect(Collectors.toList());
        Collections.sort(jarConfigList, Comparator.comparing(JarConfig::getUpdateTime).reversed());
        return jarConfigList;
    }

    public JarConfig get(String id) {
        return jarConfigMapper.selectByPrimaryKey(id);
    }

    public void delete(String id) {
        JarConfig JarConfig = jarConfigMapper.selectByPrimaryKey(id);
        FileUtils.deleteFile(JarConfig.getPath());
        jarConfigMapper.deleteByPrimaryKey(id);
    }

    public void update(JarConfig jarConfig, MultipartFile file) {
        checkExist(jarConfig);
        jarConfig.setEnable(true);// todo 审批机制时需修改
        jarConfig.setModifier(SessionUtils.getUser().getId());
        jarConfig.setUpdateTime(System.currentTimeMillis());
        String deletePath = jarConfig.getPath();
        if (file != null) {
            jarConfig.setFileName(file.getOriginalFilename());
            jarConfig.setPath(getJarPath(file));
        }
        jarConfigMapper.updateByPrimaryKey(jarConfig);
        if (file != null) {
            FileUtils.deleteFile(deletePath);
            FileUtils.uploadFile(file, JAR_FILE_DIR);
            NewDriverManager.loadJar(jarConfig.getPath());
        }
    }

    public String add(JarConfig jarConfig, MultipartFile file) {
        if (file != null && !file.getOriginalFilename().endsWith(".jar")) {
            MSException.throwException("上传文件类型错误，请上传正确jar文件");
        }
        jarConfig.setId(UUID.randomUUID().toString());
        jarConfig.setCreator(SessionUtils.getUser().getId());
        jarConfig.setModifier(SessionUtils.getUser().getId());
        checkExist(jarConfig);
        jarConfig.setEnable(true);// todo 审批机制时需修改
        jarConfig.setCreateTime(System.currentTimeMillis());
        jarConfig.setUpdateTime(System.currentTimeMillis());
        jarConfig.setPath(getJarPath(file));
        jarConfig.setFileName(file.getOriginalFilename());
        jarConfigMapper.insert(jarConfig);
        FileUtils.uploadFile(file, JAR_FILE_DIR);
        NewDriverManager.loadJar(jarConfig.getPath());
        return jarConfig.getId();
    }

    public String getJarPath(MultipartFile file) {
        return JAR_FILE_DIR + "/" + file.getOriginalFilename();
    }

    private void checkExist(JarConfig jarConfig) {
        if (jarConfig.getName() != null) {
            JarConfigExample example = new JarConfigExample();
            JarConfigExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(jarConfig.getName());
            if (StringUtils.isNotBlank(jarConfig.getId())) {
                criteria.andIdNotEqualTo(jarConfig.getId());
            }
            if (jarConfigMapper.selectByExample(example).size() > 0) {
                MSException.throwException(Translator.get("already_exists"));
            }
        }
    }

    public String getLogDetails(String id) {
        JarConfig jarConfig = jarConfigMapper.selectByPrimaryKey(id);
        if (jarConfig != null) {
            List<DetailColumn> columns = ReflexObjectUtil.getColumns(jarConfig, SystemReference.jarColumns);
            OperatingLogDetails details = new OperatingLogDetails(JSON.toJSONString(jarConfig.getId()), null, jarConfig.getName(), jarConfig.getCreator(), columns);
            return JSON.toJSONString(details);
        }
        return null;
    }

}
