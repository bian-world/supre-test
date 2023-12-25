package com.supretest.track.issue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.supretest.api.dto.automation.DragApiScenarioModuleRequest;
import com.supretest.api.dto.definition.DragModuleRequest;
import com.supretest.api.dto.scenario.request.RequestType;
import com.supretest.base.domain.*;
import com.supretest.base.mapper.ProjectMapper;
import com.supretest.base.mapper.TestCaseNodeMapper;
import com.supretest.base.mapper.ext.ExtProjectMapper;
import com.supretest.commons.constants.IssuesManagePlatform;
import com.supretest.commons.constants.IssuesStatus;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.DateUtils;
import com.supretest.commons.utils.LogUtil;
import com.supretest.controller.request.AddProjectRequest;
import com.supretest.dto.BugCountBaseDTO;
import com.supretest.dto.UserDTO;
import com.supretest.i18n.Translator;
import com.supretest.track.dto.DemandDTO;
import com.supretest.track.dto.TestCaseNodeDTO;
import com.supretest.track.issue.client.ZentaoClient;
import com.supretest.track.issue.domain.PlatformUser;
import com.supretest.track.issue.domain.zentao.AddIssueResponse;
import com.supretest.track.issue.domain.zentao.GetIssueResponse;
import com.supretest.track.issue.domain.zentao.ZentaoBuild;
import com.supretest.track.issue.domain.zentao.ZentaoConfig;
import com.supretest.track.request.testcase.DragNodeRequest;
import com.supretest.track.request.testcase.IssuesRequest;
import com.supretest.track.request.testcase.IssuesUpdateRequest;
import com.supretest.track.service.TestCaseNodeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ZentaoPlatform extends AbstractIssuePlatform {
    protected final ZentaoClient zentaoClient;

    protected final String[] imgArray = {
            "bmp", "jpg", "png", "tif", "gif", "jpeg"
    };
    protected final String DEFAULT_WORKSPACE = "aad4b966-cbd0-11ec-a0b1-0242ac110002";

    public ZentaoPlatform() {
        this.workspaceId = DEFAULT_WORKSPACE;
        ZentaoConfig zentaoConfig = getConfig();
        this.zentaoClient = ZentaoFactory.getInstance(zentaoConfig.getUrl(), zentaoConfig.getRequest());
        this.zentaoClient.setConfig(zentaoConfig);
    }

    public ZentaoPlatform(IssuesRequest issuesRequest) {
        super(issuesRequest);
        this.key = IssuesManagePlatform.Zentao.name();
        ZentaoConfig zentaoConfig = getConfig();
        this.workspaceId = issuesRequest.getWorkspaceId();
        this.zentaoClient = ZentaoFactory.getInstance(zentaoConfig.getUrl(), zentaoConfig.getRequest());
        this.zentaoClient.setConfig(zentaoConfig);
    }

    @Override
    public String getProjectId(String projectId) {
        return getProjectId(projectId, Project::getZentaoId);
    }

    @Override
    public List<IssuesDao> getIssue(IssuesRequest issuesRequest) {
        issuesRequest.setPlatform(key);
        List<IssuesDao> issues;
        if (StringUtils.isNotBlank(issuesRequest.getProjectId())) {
            issues = extIssuesMapper.getIssues(issuesRequest);
        } else {
            issues = extIssuesMapper.getIssuesByCaseId(issuesRequest);
        }
        return issues;
    }

    public IssuesDao getZentaoAssignedAndBuilds(IssuesDao issue) {
        JSONObject zentaoIssue = zentaoClient.getBugById(issue.getPlatformId());
        String assignedTo = zentaoIssue.getString("assignedTo");
        String openedBuild = zentaoIssue.getString("openedBuild");
        List<String> zentaoBuilds = new ArrayList<>();
        if (Strings.isNotBlank(openedBuild)) {
            zentaoBuilds = Arrays.asList(openedBuild.split(","));
        }
        issue.setZentaoAssigned(assignedTo);
        issue.setZentaoBuilds(zentaoBuilds);
        return issue;
    }

    @Override
    public List<DemandDTO> getDemandList(String projectId) {
        //getTestStories
        List<DemandDTO> list = new ArrayList<>();
        try {
            String session = zentaoClient.login();
            String key = getProjectId(projectId);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(new HttpHeaders());
            RestTemplate restTemplate = new RestTemplate();
            String storyGet = zentaoClient.requestUrl.getStoryGet();
            ResponseEntity<String> responseEntity = restTemplate.exchange(storyGet + session,
                    HttpMethod.POST, requestEntity, String.class, key);
            String body = responseEntity.getBody();
            JSONObject obj = JSONObject.parseObject(body);

            LogUtil.info("project story: " + key + obj);

            if (obj != null) {
                String data = obj.getString("data");
                if (StringUtils.isBlank(data)) {
                    return list;
                }
                // 兼容处理11.5版本格式 [{obj},{obj}]
                if (data.charAt(0) == '[') {
                    JSONArray array = obj.getJSONArray("data");
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        DemandDTO demandDTO = new DemandDTO();
                        demandDTO.setId(o.getString("id"));
                        demandDTO.setName(o.getString("title"));
                        demandDTO.setPlatform(key);
                        list.add(demandDTO);
                    }
                }
                // 处理格式 {{"id": {obj}},{"id",{obj}}}
                else if (data.charAt(0) == '{') {
                    JSONObject dataObject = obj.getJSONObject("data");
                    String s = JSON.toJSONString(dataObject);
                    Map<String, Object> map = JSONArray.parseObject(s, new TypeReference<Map<String, Object>>() {
                    });
                    Collection<Object> values = map.values();
                    values.forEach(v -> {
                        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(v));
                        DemandDTO demandDTO = new DemandDTO();
                        demandDTO.setId(jsonObject.getString("id"));
                        demandDTO.setName(jsonObject.getString("title"));
                        demandDTO.setPlatform(key);
                        list.add(demandDTO);
                    });
                }
            }
        } catch (Exception e) {
            LogUtil.error("get zentao demand fail " + e.getMessage());
        }
        return list;
    }

    /**
     * 更新缺陷数据
     *
     * @param issue 待更新缺陷数据
     * @param bug   平台缺陷数据
     * @return
     */
    public IssuesWithBLOBs getUpdateIssues(IssuesWithBLOBs issue, JSONObject bug) {
        GetIssueResponse.Issue bugObj = JSONObject.parseObject(bug.toJSONString(), GetIssueResponse.Issue.class);
        String description = bugObj.getSteps();
        String steps = description;
        try {
            steps = htmlDesc2MsDesc(zentao2MsDescription(description));
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
        }
        if (issue == null) {
            issue = new IssuesWithBLOBs();
            issue.setCustomFields(defaultCustomFields);
        } else {
            mergeCustomField(issue, defaultCustomFields);
        }
        issue.setPlatformStatus(bugObj.getStatus());
        if (StringUtils.equals(bugObj.getDeleted(), "1")) {
            issue.setPlatformStatus(IssuesStatus.DELETE.toString());
            issuesMapper.updateByPrimaryKeySelective(issue);
        }
        issue.setTitle(bugObj.getTitle());
        issue.setDescription(steps);
        issue.setReporter(bugObj.getOpenedBy());
        issue.setPlatform(key);
        try {
            String openedDate = bug.getString("openedDate");
            String lastEditedDate = bug.getString("lastEditedDate");
            if (StringUtils.isNotBlank(openedDate) && !openedDate.startsWith("0000-00-00"))
                issue.setCreateTime(bug.getLong("openedDate"));
            if (StringUtils.isNotBlank(lastEditedDate) && !lastEditedDate.startsWith("0000-00-00"))
                issue.setUpdateTime(bug.getLong("lastEditedDate"));
        } catch (Exception e) {
            LogUtil.error("update zentao time" + e.getMessage());
        }
        if (issue.getUpdateTime() == null) {
            issue.setUpdateTime(System.currentTimeMillis());
        }
        issue.setCustomFields(syncIssueCustomField(issue.getCustomFields(), bug));
        return issue;
    }

    @Override
    public IssuesWithBLOBs addIssue(IssuesUpdateRequest issuesRequest) {
        setUserConfig();

        MultiValueMap<String, Object> param = buildUpdateParam(issuesRequest);
        AddIssueResponse.Issue issue = zentaoClient.addIssue(param);
        issuesRequest.setPlatformStatus(issue.getStatus());

        IssuesWithBLOBs issues = null;

        String id = issue.getId();
        if (StringUtils.isNotBlank(id)) {
            issuesRequest.setPlatformId(id);
            issuesRequest.setId(UUID.randomUUID().toString());

            IssuesExample issuesExample = new IssuesExample();
            issuesExample.createCriteria().andIdEqualTo(id)
                    .andPlatformEqualTo(key);
            if (issuesMapper.selectByExample(issuesExample).size() <= 0) {
                // 插入缺陷表
                issues = insertIssues(issuesRequest);
            }

            // 用例与第三方缺陷平台中的缺陷关联
            handleTestCaseIssues(issuesRequest);
        } else {
            MSException.throwException("请确认该Zentao账号是否开启超级modle调用接口权限");
        }
        return issues;
    }

    @Override
    public void updateIssue(IssuesUpdateRequest request) {
        setUserConfig();
        MultiValueMap<String, Object> param = buildUpdateParam(request);
        handleIssueUpdate(request);
        zentaoClient.updateIssue(request.getPlatformId(), param);
    }

    private MultiValueMap<String, Object> buildUpdateParam(IssuesUpdateRequest issuesRequest) {
        issuesRequest.setPlatform(key);
        String projectId = getProjectId(issuesRequest.getProjectId());
        if (StringUtils.isBlank(projectId)) {
            MSException.throwException("未关联禅道项目ID.");
        }
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("product", projectId);
        paramMap.add("title", issuesRequest.getTitle());

        addCustomFields(issuesRequest, paramMap);

        String description = issuesRequest.getDescription();
        String zentaoSteps = description;

        // transfer description
        try {
            zentaoSteps = ms2ZentaoDescription(description);
            zentaoSteps = zentaoSteps.replaceAll("\\n", "<br/>");
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
        }
        LogUtil.info("zentao description transfer: " + zentaoSteps);

        paramMap.add("steps", zentaoSteps);
        if (!CollectionUtils.isEmpty(issuesRequest.getZentaoBuilds())) {
            List<String> builds = issuesRequest.getZentaoBuilds();
            builds.forEach(build -> paramMap.add("openedBuild[]", build));
        } else {
            paramMap.add("openedBuild", "trunk");
        }
        if (StringUtils.isNotBlank(issuesRequest.getZentaoAssigned())) {
            paramMap.add("assignedTo", issuesRequest.getZentaoAssigned());
        }
        return paramMap;
    }

    @Override
    public void deleteIssue(String id) {
        IssuesWithBLOBs issuesWithBLOBs = issuesMapper.selectByPrimaryKey(id);
        super.deleteIssue(id);
        zentaoClient.deleteIssue(issuesWithBLOBs.getPlatformId());
    }

    @Override
    public void testAuth() {
        zentaoClient.login();
    }

    @Override
    public void userAuth(UserDTO.PlatformInfo userInfo) {
        setUserConfig(userInfo);
        zentaoClient.login();
    }

    public ZentaoConfig getConfig() {
        return getConfig(key, ZentaoConfig.class);
    }

    public ZentaoConfig setConfig() {
        ZentaoConfig config = getConfig();
        zentaoClient.setConfig(config);
        return config;
    }

    public ZentaoConfig setUserConfig() {
        return setUserConfig(getUserPlatInfo(this.workspaceId));
    }

    public ZentaoConfig setUserConfig(UserDTO.PlatformInfo userPlatInfo) {
        ZentaoConfig zentaoConfig = getConfig();
        if (userPlatInfo != null && StringUtils.isNotBlank(userPlatInfo.getZentaoUserName())
                && StringUtils.isNotBlank(userPlatInfo.getZentaoPassword())) {
            zentaoConfig.setAccount(userPlatInfo.getZentaoUserName());
            zentaoConfig.setPassword(userPlatInfo.getZentaoPassword());
        }
        zentaoClient.setConfig(zentaoConfig);
        return zentaoConfig;
    }

    @Override
    public List<PlatformUser> getPlatformUser() {
        String session = zentaoClient.login();
        ;
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String getUser = zentaoClient.requestUrl.getUserGet();
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUser + session,
                HttpMethod.GET, requestEntity, String.class);
        String body = responseEntity.getBody();
        JSONObject obj = JSONObject.parseObject(body);

        LogUtil.info("zentao user " + obj);

        JSONArray data = obj.getJSONArray("data");

        List<PlatformUser> users = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject o = data.getJSONObject(i);
            PlatformUser platformUser = new PlatformUser();
            String account = o.getString("account");
            String username = o.getString("realname");
            platformUser.setName(username);
            platformUser.setUser(account);
            users.add(platformUser);
        }
        return users;
    }

    @Override
    public void syncIssues(Project project, List<IssuesDao> issues) {
        issues.forEach(item -> {
            IssuesWithBLOBs issue = issuesMapper.selectByPrimaryKey(item.getId());
            JSONObject bug = zentaoClient.getBugById(item.getPlatformId());
            issue = getUpdateIssues(issue, bug);
            issue.setId(item.getId());
            issuesMapper.updateByPrimaryKeySelective(issue);
        });
    }

    public List<ZentaoBuild> getBuilds() {
        Map<String, Object> builds = zentaoClient.getBuildsByCreateMetaData(getProjectId(projectId));
        if (builds == null || builds.isEmpty()) {
            builds = zentaoClient.getBuilds(getProjectId(projectId));
        }
        List<ZentaoBuild> res = new ArrayList<>();
        builds.forEach((k, v) -> {
            if (StringUtils.isNotBlank(k)) {
                res.add(new ZentaoBuild(k, v.toString()));
            }
        });
        return res;
    }

    private String uploadFile(FileSystemResource resource) {
        String id = "";
        String session = zentaoClient.login();
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap, httpHeaders);
        paramMap.add("files", resource);
        RestTemplate restTemplate = new RestTemplate();
        try {
            String fileUpload = zentaoClient.requestUrl.getFileUpload();
            ResponseEntity<String> responseEntity = restTemplate.exchange(fileUpload + session,
                    HttpMethod.POST, requestEntity, String.class);
            String body = responseEntity.getBody();
            JSONObject obj = JSONObject.parseObject(body);
            JSONObject data = obj.getJSONObject("data");
            Set<String> set = data.getInnerMap().keySet();
            if (!set.isEmpty()) {
                id = (String) set.toArray()[0];
            }
        } catch (Exception e) {
            LogUtil.error(e, e.getMessage());
        }
        LogUtil.info("upload file id: " + id);
        return id;
    }

    private String ms2ZentaoDescription(String msDescription) {
        String imgUrlRegex = "!\\[.*?]\\(/resource/md/get(.*?\\..*?)\\)";
        String zentaoSteps = msDescription.replaceAll(imgUrlRegex, zentaoClient.requestUrl.getReplaceImgUrl());
        Matcher matcher = zentaoClient.requestUrl.getImgPattern().matcher(zentaoSteps);
        while (matcher.find()) {
            // get file name
            String originSubUrl = matcher.group(1);
            String fileName = originSubUrl.substring(10);
            fileName = resourceService.decodeFileName(fileName);
            // get file
            ResponseEntity<FileSystemResource> mdImage = resourceService.getMdImage(fileName);
            // upload zentao
            String id = uploadFile(mdImage.getBody());
            // todo delete local file
            int index = fileName.lastIndexOf(".");
            String suffix = "";
            if (index != -1) {
                suffix = fileName.substring(index);
            }
            // replace id
            zentaoSteps = zentaoSteps.replaceAll(Pattern.quote(originSubUrl), id + suffix);
        }
        // image link
        String netImgRegex = "!\\[(.*?)]\\((http.*?)\\)";
        return zentaoSteps.replaceAll(netImgRegex, "<img src=\"$2\" alt=\"$1\"/>");
    }

    private String zentao2MsDescription(String ztDescription) {
        String imgRegex = "<img src.*?/>";
        Pattern pattern = Pattern.compile(imgRegex);
        Matcher matcher = pattern.matcher(ztDescription);
        while (matcher.find()) {
            if (StringUtils.isNotEmpty(matcher.group())) {
                // img标签内容
                String imgPath = matcher.group();
                // 解析标签内容为图片超链接格式，进行替换，
                String src = getMatcherResultForImg("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)", imgPath);
                String alt = getMatcherResultForImg("alt\\s*=\\s*\"?(.*?)(\"|>|\\s+)", imgPath);
                String hyperLinkPath = packageDescriptionByPathAndName(src, alt);
                imgPath = transferSpecialCharacter(imgPath);
                ztDescription = ztDescription.replaceAll(imgPath, hyperLinkPath);
            }
        }

        return ztDescription;
    }

    private String packageDescriptionByPathAndName(String path, String name) {
        String result = "";

        if (StringUtils.isNotEmpty(path)) {
            if (path.startsWith("{") && path.endsWith("}")) {
                String srcContent = path.substring(1, path.length() - 1);
                if (StringUtils.isEmpty(name)) {
                    name = srcContent;
                }

                if (Arrays.stream(imgArray).anyMatch(imgType -> StringUtils.equals(imgType, srcContent.substring(srcContent.indexOf('.') + 1)))) {
                    if (zentaoClient.getBaseUrl().contains("biz")) {
                        // 禅道企业版
                        path = zentaoClient.getBaseUrl() + "/index.php?m=file&f=read&fileID=" + srcContent;
                    } else {
                        // 禅道开源版
                        path = zentaoClient.getBaseUrl() + "/file-read-" + srcContent;
                    }
                } else {
                    return result;
                }
            }
            // 图片与描述信息之间需换行，否则无法预览图片
            result = "\n\n![" + name + "](" + path + ")";
        }

        return result;
    }

    private String getMatcherResultForImg(String regex, String targetStr) {
        String result = "";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(targetStr);
        while (matcher.find()) {
            result = matcher.group(1);
        }

        return result;
    }

    @Override
    public Boolean checkProjectExist(String relateId) {
        return zentaoClient.checkProjectExist(relateId);
    }

    //同步禅道的产品数据到本地
    public void syncProducts() {
        Map<String, Project> dbProjects = extProjectMapper.queryAllZenTaoProjects();
        Map<String, Object> products = zentaoClient.getProductList();
        products.keySet().stream().forEach(key -> {
            JSONObject jProject = JSONObject.parseObject(products.get(key).toString());
            if (dbProjects.containsKey(key)) {
                Project project = dbProjects.get(key);
                project.setDescription(jProject.get("desc").toString());
                project.setName(jProject.get("name").toString());
                project.setZentaoProductType(jProject.get("type").toString());
                long updateTime = System.currentTimeMillis();
                project.setUpdateTime(updateTime);
                projectMapper.updateByPrimaryKey(project);
            } else {
                AddProjectRequest project = new AddProjectRequest();
                project.setPlatform("Zentao");
                project.setZentaoId(jProject.get("id").toString());
                project.setName(jProject.get("name").toString());
                project.setZentaoProductType(jProject.get("type").toString());
                //设置成默认空间
                project.setWorkspaceId(DEFAULT_WORKSPACE);
                projectService.addProject(project);
            }
            syncBranchAndModules(key);
            syncProjects(key);
        });
    }


    public void syncProduct(String productId) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andZentaoIdEqualTo(productId);
        List<Project> dbProject = projectMapper.selectByExample(projectExample);
        JSONObject jProject = zentaoClient.getProduct(productId);
            if (dbProject.size() == 1) {
                Project project = dbProject.get(0);
                project.setDescription(jProject.get("desc").toString());
                project.setName(jProject.get("name").toString());
                project.setZentaoProductType(jProject.get("type").toString());
                long updateTime = System.currentTimeMillis();
                project.setUpdateTime(updateTime);
                projectMapper.updateByPrimaryKey(project);
            }
            else if(dbProject.size() == 0){
                AddProjectRequest project = new AddProjectRequest();
                project.setPlatform("Zentao");
                project.setZentaoId(jProject.get("id").toString());
                project.setName(jProject.get("name").toString());
                project.setZentaoProductType(jProject.get("type").toString());
                //设置成默认空间
                project.setWorkspaceId(DEFAULT_WORKSPACE);
                projectService.addProject(project);
            } else {
                MSException.throwException("本地禅道产品数据有重复！！");
            }
            syncBranchAndModules(productId);
            syncProjects(productId);
    }

    public void syncProjects(String productId){
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andZentaoIdEqualTo(productId);
        Project product = projectMapper.selectByExample(projectExample).get(0);
        Optional.ofNullable(product).orElseThrow(() -> new MSException(Translator.get("project_is_not_exists")));
        List<Object> projects = zentaoClient.getProjects(productId);
        Map<String, ZendaoProject> dbZendaoProjects= zendaoProjectMapper.selectByProductId(productId);
        Set<String> dbZendaoProjectKeys = dbZendaoProjects.keySet();
        projects.stream().forEach(project -> {
        JSONObject jZendaoProject = JSONObject.parseObject(project.toString());
        String projectId = jZendaoProject.getString("id");
        ZendaoProject zendaoProject;
        if(dbZendaoProjectKeys.contains(projectId)){
            zendaoProject = dbZendaoProjects.get(projectId);
            zendaoProject.setZendaoProjectName(jZendaoProject.getString("name"));
            zendaoProject.setZendaoProjectStatus(jZendaoProject.getString("status"));
            zendaoProjectMapper.updateByZendaoProjectId(zendaoProject);
        }else{
            zendaoProject = new ZendaoProject();
            zendaoProject.setZendaoProjectId(projectId);
            zendaoProject.setZendaoProductId(productId);
            zendaoProject.setZendaoProjectName(jZendaoProject.getString("name"));
            zendaoProject.setZendaoProjectStatus(jZendaoProject.getString("status"));
            zendaoProject.setProductId(product.getId());
            long createTime = System.currentTimeMillis();
            zendaoProject.setCreateTime(createTime);
            zendaoProject.setUpdateTime(createTime);
            zendaoProject.setId(UUID.randomUUID().toString());
            zendaoProjectMapper.insert(zendaoProject);
        }

        });
    }

    //同步禅道产品下的分支平台及模块数据到本地
    public void syncBranchAndModules(String productId) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andZentaoIdEqualTo(productId);
        Project project = projectMapper.selectByExample(projectExample).get(0);

        Optional.ofNullable(project).orElseThrow(() -> new MSException(Translator.get("project_is_not_exists")));
        List<Object> branches = zentaoClient.getBranch(productId);
        List<JSONObject> jzendaoBranchTreeList = new ArrayList<JSONObject>();
        if (branches.size() == 0) {
            getBranchesAndTrees(productId, "0", "0", jzendaoBranchTreeList);
            LogUtil.info("normal类型产品模块数据：个数 {},详情 {}", jzendaoBranchTreeList.size(), jzendaoBranchTreeList);
        } else {
            branches.stream().forEach(branch -> {
                List<JSONObject> temp = new ArrayList<JSONObject>();
                JSONObject jBranch = JSONObject.parseObject(branch.toString());
                String branchId = jBranch.getString("id");
                getBranchesAndTrees(productId, "0", branchId, temp);
                temp.stream().forEach(jsonObject -> {
                    jsonObject.put("branch_name", jBranch.getString("name"));
                    jsonObject.put("branch_status", jBranch.getString("status"));
                });
                jzendaoBranchTreeList.addAll(temp);
            });
            LogUtil.info("非normal类型产品模块数据：个数 {},详情 {}", jzendaoBranchTreeList.size(), jzendaoBranchTreeList);
        }
        if (jzendaoBranchTreeList.size() == 0) {
            LogUtil.info("该产品下没有分支、模块可同步");
        }
        ZendaoBranchTreeExample example = new ZendaoBranchTreeExample();
        ZendaoBranchTreeExample.Criteria zCriteria = example.createCriteria();
        zCriteria.andZendaoProductIdEqualTo(productId);
        Map<String, ZendaoBranchTree> dbBranchTreeMap = zendaoBranchTreeMapper.selectByExample(example);
        Set<String> dbBranchTreeKey = dbBranchTreeMap.keySet();
        jzendaoBranchTreeList.stream().forEach(jzendaoBranchTree -> {
            ZendaoBranchTree zendaoBranchTree = new ZendaoBranchTree();
            zendaoBranchTree.setProjectId(project.getId());
            zendaoBranchTree.setZendaoModuleId(jzendaoBranchTree.getString("id"));
            zendaoBranchTree.setZendaoModuleName(jzendaoBranchTree.getString("name"));
            zendaoBranchTree.setZendaoProductId(productId);
            zendaoBranchTree.setZendaoParentModuleId(jzendaoBranchTree.getString("parent"));
            zendaoBranchTree.setZendaoBranchId(jzendaoBranchTree.getString("branch"));
            zendaoBranchTree.setZendaoModulePath(jzendaoBranchTree.getString("path"));
            if (jzendaoBranchTree.containsKey("branch_name")) {
                zendaoBranchTree.setZendaoBranchName(jzendaoBranchTree.getString("branch_name"));
            }
            if (jzendaoBranchTree.containsKey("branch_status")) {
                zendaoBranchTree.setZendaoBranchStatus(jzendaoBranchTree.getString("branch_status"));
            }
            long createTime = System.currentTimeMillis();
            zendaoBranchTree.setUpdateTime(createTime);
            LogUtil.info("zendaoBranchTree：详情 {}", zendaoBranchTree);

            String moduleId = jzendaoBranchTree.getString("id");
            if (dbBranchTreeKey.contains(moduleId)) {
                zendaoBranchTree.setId(dbBranchTreeMap.get(moduleId).getId());
                zendaoBranchTree.setCreateTime(dbBranchTreeMap.get(moduleId).getCreateTime());
                zendaoBranchTreeMapper.updateByPrimaryKey(zendaoBranchTree);
            } else {
                zendaoBranchTree.setCreateTime(createTime);
                zendaoBranchTree.setId(UUID.randomUUID().toString());
                zendaoBranchTreeMapper.insert(zendaoBranchTree);
            }
            createOrUpdateTestCaseNode(zendaoBranchTree);
            createOrUpdateApiModule(zendaoBranchTree);
            createOrUpdateApiScenarioModule(zendaoBranchTree);
        });
    }

    public void getBranchesAndTrees(String productId, String moduleId, String branchId, List<JSONObject> result) {

        JSONArray jTrees = zentaoClient.getTree(productId, moduleId, branchId);
        jTrees.stream().forEach(jTree -> {
            JSONObject jsonjTree = JSONObject.parseObject(jTree.toString());
            jsonjTree.put("branch", branchId);
            result.add(jsonjTree);
            getBranchesAndTrees(productId, jsonjTree.getString("id"), branchId, result);
        });
    }

    public void createOrUpdateTestCaseNode(ZendaoBranchTree zendaoBranchTree){
        String projectId = zendaoBranchTree.getProjectId();
        Map<String,TestCaseNode> testCaseNodeMap= testCaseNodeMapper.selectByProjectId(projectId);
        String[] nodeList = zendaoBranchTree.getZendaoModulePath().split(",");
        if(nodeList.length == 0){
            LogUtil.info("该条数据没有module path，请检查确认");
            return;
        }
        Set<String> zentaoTreeIds = testCaseNodeMap.keySet();
        String nextParentId = null;
        TestCaseNode testCaseNode =  new TestCaseNode();
        ZendaoBranchTreeExample zendaoBranchTreeExample = new ZendaoBranchTreeExample();
        zendaoBranchTreeExample.createCriteria().andProjectIdEqualTo(projectId);

        Map<String,ZendaoBranchTree> treeMap = zendaoBranchTreeMapper.selectByExample(zendaoBranchTreeExample);

        for(int i = 1; i < nodeList.length; i++){
            String currParentId = nextParentId;
            ZendaoBranchTree tree = treeMap.get(nodeList[i]);
            if(zentaoTreeIds.contains(nodeList[i])){
                TestCaseNode dbTestCaseNode = testCaseNodeMap.get(nodeList[i]);
                DragNodeRequest request = new DragNodeRequest();
                request.setLevel(i);
                request.setName(tree.getZendaoModuleName());
                if(StringUtils.isNotEmpty(nodeList[i-1])){
                    request.setParentId(nextParentId);
                }
                List<String> list = new ArrayList<String>();
                list.add(dbTestCaseNode.getId());
                request.setNodeIds(list);
                request.setId(dbTestCaseNode.getId());
                request.setProjectId(dbTestCaseNode.getProjectId());
                request.setZentaoTreeId(dbTestCaseNode.getZentaoTreeId());
                testCaseNodeService.editNode(request);
                 nextParentId = dbTestCaseNode.getId();
            }else{

                     testCaseNode.setId(UUID.randomUUID().toString());
                     testCaseNode.setProjectId(zendaoBranchTree.getProjectId());
                     testCaseNode.setName(zendaoBranchTree.getZendaoModuleName());
                     testCaseNode.setZentaoTreeId(zendaoBranchTree.getId());
                     testCaseNode.setLevel(i);
                     testCaseNode.setZentaoTreeId(zendaoBranchTree.getZendaoModuleId());
                     testCaseNode.setZentaoBranchId(zendaoBranchTree.getZendaoBranchId());
                     testCaseNode.setZentaoBranchName(zendaoBranchTree.getZendaoBranchName());

                if(StringUtils.isNotEmpty(nodeList[i-1]) && StringUtils.isNotEmpty(currParentId)){
                    testCaseNode.setParentId(currParentId);
                }
                nextParentId = testCaseNodeService.addNode(testCaseNode);

                }
            }
        }

    public void createOrUpdateApiModule(ZendaoBranchTree zendaoBranchTree){
        String projectId = zendaoBranchTree.getProjectId();

        String[] nodeList = zendaoBranchTree.getZendaoModulePath().split(",");
        if(nodeList.length == 0){
            LogUtil.info("该条数据没有module path，请检查确认");
            return;
        }
        ZendaoBranchTreeExample zendaoBranchTreeExample = new ZendaoBranchTreeExample();
        zendaoBranchTreeExample.createCriteria().andProjectIdEqualTo(projectId);

        Map<String,ZendaoBranchTree> treeMap = zendaoBranchTreeMapper.selectByExample(zendaoBranchTreeExample);

        ApiModule apiModule =  new ApiModule();
        Arrays.stream(RequestType.class.getFields()).forEach(field -> {
            String nextParentId = null;
            String protocol = field.getName();
            Map<String,ApiModule> apiModuleMap= apiModuleMapper.selectByProjectIdAndProtocol(projectId,protocol);
            Set<String> zentaoTreeIds = apiModuleMap.keySet();
            for (int i = 1; i < nodeList.length; i++) {

                String currParentId = nextParentId;
                ZendaoBranchTree tree = treeMap.get(nodeList[i]);
                if(zentaoTreeIds.contains(nodeList[i])){
                    ApiModule dbApiModule = apiModuleMap.get(nodeList[i]);
                    DragModuleRequest request = new DragModuleRequest();
                    request.setLevel(i);
                    request.setName(tree.getZendaoModuleName());
                    if(StringUtils.isNotEmpty(nodeList[i-1])){
                        request.setParentId(nextParentId);
                    }
                    List<String> list = new ArrayList<String>();
                    list.add(dbApiModule.getId());
                    request.setNodeIds(list);
                    request.setId(dbApiModule.getId());
                    request.setProjectId(dbApiModule.getProjectId());
                    request.setZentaoTreeId(dbApiModule.getZentaoTreeId());
                    apiModuleService.editNode(request);
                    nextParentId = dbApiModule.getId();

                } else {
                    apiModule.setId(UUID.randomUUID().toString());
                    apiModule.setProjectId(zendaoBranchTree.getProjectId());
                    apiModule.setName(zendaoBranchTree.getZendaoModuleName());
                    apiModule.setZentaoTreeId(zendaoBranchTree.getId());
                    apiModule.setLevel(i);
                    apiModule.setZentaoTreeId(zendaoBranchTree.getZendaoModuleId());
                    apiModule.setZentaoBranchId(zendaoBranchTree.getZendaoBranchId());
                    apiModule.setZentaoBranchName(zendaoBranchTree.getZendaoBranchName());
                    apiModule.setProtocol(protocol);
                    if (StringUtils.isNotEmpty(nodeList[i - 1]) && StringUtils.isNotEmpty(currParentId)) {
                        apiModule.setParentId(currParentId);
                    }
                nextParentId = apiModuleService.addNode(apiModule);

                }
            }
        });
    }

    public void createOrUpdateApiScenarioModule(ZendaoBranchTree zendaoBranchTree){
        String projectId = zendaoBranchTree.getProjectId();
        Map<String,ApiScenarioModule> apiScenarioModuleMap= apiScenarioModuleMapper.selectByProjectId(projectId);
        String[] nodeList = zendaoBranchTree.getZendaoModulePath().split(",");
        if(nodeList.length == 0){
            LogUtil.info("该条数据没有module path，请检查确认");
            return;
        }
        Set<String> zentaoTreeIds = apiScenarioModuleMap.keySet();
        String nextParentId = null;
        ApiScenarioModule apiScenarioModule =  new ApiScenarioModule();
        ZendaoBranchTreeExample zendaoBranchTreeExample = new ZendaoBranchTreeExample();
        zendaoBranchTreeExample.createCriteria().andProjectIdEqualTo(projectId);

        Map<String,ZendaoBranchTree> treeMap = zendaoBranchTreeMapper.selectByExample(zendaoBranchTreeExample);

        for(int i = 1; i < nodeList.length; i++){
            String currParentId = nextParentId;
            ZendaoBranchTree tree = treeMap.get(nodeList[i]);
            if(zentaoTreeIds.contains(nodeList[i])){
                ApiScenarioModule dbApiScenarioModule = apiScenarioModuleMap.get(nodeList[i]);
                DragApiScenarioModuleRequest request = new DragApiScenarioModuleRequest();
                request.setLevel(i);
                request.setName(tree.getZendaoModuleName());
                if(StringUtils.isNotEmpty(nodeList[i-1])){
                    request.setParentId(nextParentId);
                }
                List<String> list = new ArrayList<String>();
                list.add(dbApiScenarioModule.getId());
                request.setNodeIds(list);
                request.setId(dbApiScenarioModule.getId());
                request.setProjectId(dbApiScenarioModule.getProjectId());
                request.setZentaoTreeId(dbApiScenarioModule.getZentaoTreeId());
                apiScenarioModuleService.editNode(request);
                nextParentId = dbApiScenarioModule.getId();
            }else{

                apiScenarioModule.setId(UUID.randomUUID().toString());
                apiScenarioModule.setProjectId(zendaoBranchTree.getProjectId());
                apiScenarioModule.setName(zendaoBranchTree.getZendaoModuleName());
                apiScenarioModule.setZentaoTreeId(zendaoBranchTree.getId());
                apiScenarioModule.setLevel(i);
                apiScenarioModule.setZentaoTreeId(zendaoBranchTree.getZendaoModuleId());
                apiScenarioModule.setZentaoBranchId(zendaoBranchTree.getZendaoBranchId());
                apiScenarioModule.setZentaoBranchName(zendaoBranchTree.getZendaoBranchName());

                if(StringUtils.isNotEmpty(nodeList[i-1]) && StringUtils.isNotEmpty(currParentId)){
                    apiScenarioModule.setParentId(currParentId);
                }
                nextParentId = apiScenarioModuleService.addNode(apiScenarioModule);

            }
        }
    }

    public List getBugListByCondition(String productId, String projectId, String status){
        if(StringUtils.isEmpty(productId)){
            return new ArrayList();
        }
        String pId ;
        final String sId ;
        Project project = projectMapper.selectByPrimaryKey(productId);
        pId = project.getZentaoId();
        if(StringUtils.isEmpty(pId)){return new ArrayList(); }
        if(StringUtils.isNotEmpty(projectId)) {
            ZendaoProject zendaoProject = zendaoProjectMapper.selectByPrimaryKey(projectId);
            sId = zendaoProject.getZendaoProjectId();
        }else{
            sId = null;
        }
        JSONArray buglist = zentaoClient.getBugList(pId);
        if(buglist.size() == 0){ return new ArrayList(); }
        return buglist.stream().filter(bug -> {
            JSONObject jsonBug = JSONObject.parseObject(bug.toString());
            if(StringUtils.isNotEmpty(status) && !StringUtils.equals(jsonBug.get("status").toString(), status)){
                return false;
            }
            if(StringUtils.isNotEmpty(sId) && !StringUtils.equals(jsonBug.get("execution").toString(), sId)){
                return false;
            }
            return true;
        }).collect(Collectors.toList());
    }


    public Map getBugCount(String productId, String projectId){
        //从禅道获取产品/项目下的未修复bug列表
        List list = getBugListByCondition(productId, projectId, "active");
        //结构化成页面图表需要的格式
        Map<String, List> map = (HashMap)list.stream().map(bug ->
                    JSONObject.parseObject(bug.toString(), BugCountBaseDTO.class)
           ).collect(Collectors.groupingBy(BugCountBaseDTO::getAssignedTo)) ;
        Map result = new HashMap();
        map.keySet().forEach(key -> result.put(key, map.get(key).size()));
        return result;
    }

    public Map getBugDuration(String productId, String projectId){

        List list = getBugListByCondition(productId, projectId, "active");
        Map result = new HashMap();
        //结构化成页面图表需要的格式，按3天、7天、30天、90天分档统计
        list.stream().forEach(bug ->
        {
            BugCountBaseDTO bugCountBaseDTO = JSONObject.parseObject(bug.toString(), BugCountBaseDTO.class);
            long days = DateUtils.differentDaysByMillisecond(bugCountBaseDTO.getAssignedDate().getTime(), new Date().getTime());
            if(days - 90 > 0){
               result.put("90", (int)result.getOrDefault("90", 0) + 1);
            }if(days - 30 > 0){
                result.put("30", (int)result.getOrDefault("30", 0) + 1);
            }if(days - 7 > 0){
                result.put("7", (int)result.getOrDefault("7", 0) + 1);
            }if(days - 3 > 0){
                result.put("3", (int)result.getOrDefault("3", 0) + 1);
            }
        });
        return result;
    }

}
