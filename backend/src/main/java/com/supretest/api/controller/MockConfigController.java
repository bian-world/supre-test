package com.supretest.api.controller;

import com.supretest.api.dto.automation.TcpTreeTableDataStruct;
import com.supretest.api.dto.mock.ApiDefinitionResponseDTO;
import com.supretest.api.dto.mock.MockParamSuggestions;
import com.supretest.api.dto.mock.MockTestDataRequest;
import com.supretest.api.dto.mockconfig.MockConfigRequest;
import com.supretest.api.dto.mockconfig.MockExpectConfigRequest;
import com.supretest.api.dto.mockconfig.response.MockConfigResponse;
import com.supretest.api.dto.mockconfig.response.MockExpectConfigResponse;
import com.supretest.api.mock.utils.MockApiUtils;
import com.supretest.api.mock.utils.MockTestDataUtil;
import com.supretest.api.service.ApiDefinitionService;
import com.supretest.api.service.MockConfigService;
import com.supretest.base.domain.ApiDefinitionWithBLOBs;
import com.supretest.base.domain.MockExpectConfig;
import com.supretest.base.domain.MockExpectConfigWithBLOBs;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author song.tianyang
 * @Date 2021/4/12 5:11 下午
 * @Description
 */
@RestController
@RequestMapping("/mockConfig")
public class MockConfigController {

    @Resource
    private MockConfigService mockConfigService;
    @Resource
    private ApiDefinitionService apiDefinitionService;

    @PostMapping("/genMockConfig")
    public MockConfigResponse genMockConfig(@RequestBody MockConfigRequest request) {
        return mockConfigService.genMockConfig(request);
    }

    @PostMapping(value ="/updateMockExpectConfig", consumes = {"multipart/form-data"})
    public MockExpectConfig updateMockExpectConfig(@RequestPart("request")MockExpectConfigRequest request, @RequestPart(value = "files", required = false) List<MultipartFile> bodyFiles) {
        return mockConfigService.updateMockExpectConfig(request,bodyFiles);
    }

    @PostMapping(value ="/updateMockExpectConfigStatus")
    public MockExpectConfig updateMockExpectConfig(@RequestBody MockExpectConfigRequest request) {
        return mockConfigService.updateMockExpectConfigStatus(request);
    }

    @GetMapping("/mockExpectConfig/{id}")
    public MockExpectConfigResponse selectMockExpectConfig(@PathVariable String id) {
        MockExpectConfigWithBLOBs config = mockConfigService.findMockExpectConfigById(id);
        MockExpectConfigResponse response = new MockExpectConfigResponse(config);
        return response;
    }

    @GetMapping("/deleteMockExpectConfig/{id}")
    public String deleteMockExpectConfig(@PathVariable String id) {
        mockConfigService.deleteMockExpectConfig(id);
        return "SUCCESS";
    }

    @GetMapping("/getApiParams/{id}")
    public Map<String, List<MockParamSuggestions>> getApiParams(@PathVariable String id) {
        ApiDefinitionWithBLOBs apiDefinitionWithBLOBs = apiDefinitionService.getBLOBs(id);
        Map<String, List<MockParamSuggestions>> apiParams = mockConfigService.getApiParamsByApiDefinitionBLOBs(apiDefinitionWithBLOBs);
        return apiParams;
    }

    @GetMapping("/getApiResponse/{id}")
    public ApiDefinitionResponseDTO getApiResponse(@PathVariable String id) {
        ApiDefinitionWithBLOBs apiDefinitionWithBLOBs = apiDefinitionService.getBLOBs(id);
        ApiDefinitionResponseDTO returnMap = MockApiUtils.getApiResponse(apiDefinitionWithBLOBs.getResponse());
        return returnMap;
    }

    @PostMapping("/getMockTestData")
    public List<MockTestDataRequest> getMockTestData(@RequestBody List<MockTestDataRequest> requestArray) {
        MockTestDataUtil testDataUtil = new MockTestDataUtil();
        return testDataUtil.parseTestDataByRequest(requestArray);
    }

    @PostMapping("/getTcpMockTestData")
    public List<TcpTreeTableDataStruct> getTcpMockTestData(@RequestBody List<TcpTreeTableDataStruct> requestArray) {
        MockTestDataUtil testDataUtil = new MockTestDataUtil();
        return testDataUtil.parseTestDataByTcpTreeTableData(requestArray);
    }

}
