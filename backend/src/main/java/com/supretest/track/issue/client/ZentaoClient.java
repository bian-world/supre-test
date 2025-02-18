package com.supretest.track.issue.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.supretest.commons.exception.MSException;
import com.supretest.commons.utils.LogUtil;
import com.supretest.i18n.Translator;
import com.supretest.track.issue.ZentaoFactory;
import com.supretest.track.issue.domain.zentao.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ZentaoClient extends BaseClient {

    protected String ENDPOINT;

    protected String USER_NAME;

    protected String PASSWD;

    public RequestUrl requestUrl;
    protected String url;

    public ZentaoClient(String url) {
        ENDPOINT = url;
    }

    public String login() {
        GetUserResponse getUserResponse = new GetUserResponse();
        String sessionId = "";
        try {
            sessionId = getSessionId();
            String loginUrl = requestUrl.getLogin();
            MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("account", USER_NAME);
            paramMap.add("password", PASSWD);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(paramMap, new HttpHeaders());
            ResponseEntity<String> response = restTemplate.exchange(loginUrl + sessionId, HttpMethod.POST, requestEntity, String.class);
            getUserResponse = (GetUserResponse) getResultForObject(GetUserResponse.class, response);
        } catch (JSONException e) {
            MSException.throwException(Translator.get("zentao_test_type_error"));
        } catch (Exception e) {
            LogUtil.error(e);
            MSException.throwException(e.getMessage());
        }
        GetUserResponse.User user = getUserResponse.getUser();
        if (user == null) {
            LogUtil.error(JSONObject.toJSON(getUserResponse));
            // 登录失败，获取的session无效，置空session
            MSException.throwException("zentao login fail, user null");
        }
        if (!StringUtils.equals(user.getAccount(), USER_NAME)) {
            LogUtil.error("login fail，inconsistent users");
            MSException.throwException("zentao login fail, inconsistent user");
        }
        return sessionId;
    }

    public String getSessionId() {
        String getSessionUrl = requestUrl.getSessionGet();
        ResponseEntity<String> response = restTemplate.exchange(getSessionUrl,
                HttpMethod.GET, null, String.class);
        GetSessionResponse getSessionResponse = (GetSessionResponse) getResultForObject(GetSessionResponse.class, response);
        return JSONObject.parseObject(getSessionResponse.getData(), GetSessionResponse.Session.class).getSessionID();
    }

    public AddIssueResponse.Issue addIssue(MultiValueMap<String, Object> paramMap) {
        String sessionId = login();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap, new HttpHeaders());
        ResponseEntity<String> response = null;
        try {
            String bugCreate = requestUrl.getBugCreate();
            response = restTemplate.exchange(bugCreate + sessionId,
                    HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
            MSException.throwException(e.getMessage());
        }
        AddIssueResponse addIssueResponse = (AddIssueResponse) getResultForObject(AddIssueResponse.class, response);
        return JSONObject.parseObject(addIssueResponse.getData(), AddIssueResponse.Issue.class);
    }

    public void updateIssue(String id, MultiValueMap<String, Object> paramMap) {
        String sessionId = login();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap, new HttpHeaders());
        try {
            restTemplate.exchange(requestUrl.getBugUpdate(),
                    HttpMethod.POST, requestEntity, String.class, id, sessionId);
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
            MSException.throwException(e.getMessage());
        }
    }

    public void deleteIssue(String id) {
        String sessionId = login();
        try {
            restTemplate.exchange(requestUrl.getBugDelete(),
                    HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class, id, sessionId);
        } catch (Exception e) {
            LogUtil.error(e.getMessage(), e);
            MSException.throwException(e.getMessage());
        }
    }

    public JSONObject getBugById(String id) {
        String sessionId = login();
        String bugGet = requestUrl.getBugGet();
        ResponseEntity<String> response = restTemplate.exchange(bugGet,
                HttpMethod.GET, null, String.class, id, sessionId);
        GetIssueResponse getIssueResponse = (GetIssueResponse) getResultForObject(GetIssueResponse.class, response);
        if(StringUtils.equalsIgnoreCase(getIssueResponse.getStatus(),"fail")){
            GetIssueResponse.Issue issue = new GetIssueResponse.Issue();
            issue.setId(id);
            issue.setSteps(" ");
            issue.setTitle(" ");
            issue.setStatus("closed");
            issue.setDeleted("1");
            issue.setOpenedBy(" ");
            getIssueResponse.setData(JSON.toJSON(issue).toString());
        }
        return JSONObject.parseObject(getIssueResponse.getData());
    }

    public GetCreateMetaDataResponse.MetaData getCreateMetaData(String productID) {
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getCreateMetaData(),
                HttpMethod.GET, null, String.class, productID, sessionId);
        GetCreateMetaDataResponse getCreateMetaDataResponse = (GetCreateMetaDataResponse) getResultForObject(GetCreateMetaDataResponse.class, response);
        return JSONObject.parseObject(getCreateMetaDataResponse.getData(), GetCreateMetaDataResponse.MetaData.class);
    }

    public JSONObject getCustomFields(String productID) {
        return getCreateMetaData(productID).getCustomFields();
    }

    public Map<String, Object> getBuildsByCreateMetaData(String projectId) {
        return getCreateMetaData(projectId).getBuilds();
    }

    public Map<String, Object> getBuilds(String projectId) {
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getBuildsGet(),
                HttpMethod.GET, null, String.class, projectId, sessionId);
        return JSONObject.parseObject(response.getBody()).getJSONObject("data").getInnerMap();
    }

    public JSONArray getBugsByProjectId(String projectId, int pageNum, int pageSize) {
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getBugList(),
                HttpMethod.GET, null, String.class, projectId, 9999999, pageSize, pageNum, sessionId);
        return JSONObject.parseObject(response.getBody()).getJSONObject("data").getJSONArray("bugs");
    }

    public String getBaseUrl() {
        if (ENDPOINT.endsWith("/")) {
            return ENDPOINT.substring(0, ENDPOINT.length() - 1);
        }
        return ENDPOINT;
    }

    public void setConfig(ZentaoConfig config) {
        if (config == null) {
            MSException.throwException("config is null");
        }
        USER_NAME = config.getAccount();
        PASSWD = config.getPassword();
        ENDPOINT = config.getUrl();
    }


    public String getReplaceImgUrl(String replaceImgUrl) {
        String baseUrl = getBaseUrl();
        String[] split = baseUrl.split("/");
        String suffix = split[split.length - 1];
        if (StringUtils.equals("biz", suffix)) {
            suffix = baseUrl;
        } else if (!StringUtils.equalsAny(suffix, "zentao", "pro", "zentaopms", "zentaopro", "zentaobiz")) {
            suffix = "";
        } else {
            suffix = "/" + suffix;
        }
        return String.format(replaceImgUrl, suffix);
    }

    public boolean checkProjectExist(String relateId) {
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getProductGet(),
                HttpMethod.GET, null, String.class, relateId, sessionId);
        try {
            Object data = JSONObject.parseObject(response.getBody()).get("data");
            if (!StringUtils.equals((String) data, "false")) {
                return true;
            }
        } catch (Exception e) {
            LogUtil.info("query zentao product info error. product id: " + relateId);
        }
        return false;
    }


    public Map<String, Object>  getProductList() {
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getProductListGet(),
                HttpMethod.GET, null, String.class,sessionId);
        System.out.println(getResult( response));
        return JSONObject.parseObject(getResult( response)).getJSONObject("data").getInnerMap();
    }

    public JSONObject  getProduct(String productId) {
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getProductGet(),
                HttpMethod.GET, null, String.class,productId,sessionId);
        return JSONObject.parseObject(getResult( response)).getJSONObject("data");
    }

   public Map<String,Object> getProjectStat(String productId){
       String sessionId = login();
       ResponseEntity<String> response = restTemplate.exchange(requestUrl.getProjectStatsGet(),
               HttpMethod.GET, null, String.class, productId, sessionId);
       return JSONObject.parseObject(getResult( response)).getJSONObject("data").getInnerMap();
   }

    public List<Object> getBranch(String productId){
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getBranchGet(),
                HttpMethod.GET, null, String.class, productId, sessionId);
        //多分支产品只有一个主干时，data为list；否则data是map形式
        try {
            JSONArray jsonStr = JSONObject.parseObject(getResult( response)).getJSONArray("data");
            return jsonStr;
        } catch (Exception e) {
            Map<String,Object> branchesMap = JSONObject.parseObject(getResult( response)).getJSONObject("data").getInnerMap();;
            return branchesMap.values().stream().collect(Collectors.toList());
        }
    }

    public JSONArray getTree(String rootId,String moduleId, String branchId){
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getTreeGet(),
                HttpMethod.GET, null, String.class, rootId,moduleId, branchId, sessionId);
        return JSONObject.parseObject(getResult( response)).getJSONArray("data");
    }

    public List<Object> getProjects(String productId) {
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getProjectGet(),
                HttpMethod.GET, null, String.class, productId, sessionId);
        LogUtil.info("产品id：{},  返回结果： {}", productId, getResult(response));
        try {
            JSONArray jsonStr = JSONObject.parseObject(getResult(response)).getJSONArray("data");
            return jsonStr;
        } catch (Exception e) {
            Map<String, Object> branchesMap = JSONObject.parseObject(getResult(response)).getJSONObject("data").getInnerMap();
            return branchesMap.values().stream().collect(Collectors.toList());
        }
    }


    public JSONArray getBugList(String productId){
        String sessionId = login();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl.getBugList(),
                HttpMethod.GET, null, String.class, productId, 0, 10000, 1, sessionId);
        JSONArray bugList = JSONObject.parseObject(getResult( response)).getJSONObject("data").getJSONArray("bugs");
        return bugList;
    }
}

