package com.supretest.api.controller;

import com.supretest.api.dto.share.ApiDocumentInfoDTO;
import com.supretest.api.dto.share.ApiDocumentRequest;
import com.supretest.api.dto.share.ApiDocumentShareRequest;
import com.supretest.api.dto.share.ShareInfoDTO;
import com.supretest.api.service.ApiDefinitionService;
import com.supretest.api.service.ShareInfoService;
import com.supretest.base.domain.ApiDefinitionWithBLOBs;
import com.supretest.base.domain.ReportStatisticsWithBLOBs;
import com.supretest.base.domain.ShareInfo;
import com.supretest.base.domain.User;
import com.supretest.commons.utils.LogUtil;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.reportstatistics.dto.ReportStatisticsSaveRequest;
import com.supretest.reportstatistics.service.ReportStatisticsService;
import com.supretest.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author song.tianyang
 * @Date 2021/2/5 6:25 下午
 * @Description
 */
@RestController
@RequestMapping(value = "/share/info")
public class ShareInfoController {
    @Resource
    ShareInfoService shareInfoService;
    @Resource
    ApiDefinitionService apiDefinitionService;
    @Resource
    ReportStatisticsService reportStatisticsService;
    @Resource
    UserService userService;

    @PostMapping("/selectApiSimpleInfo")
    public List<ApiDocumentInfoDTO> list(@RequestBody ApiDocumentRequest request) {
        List<ApiDocumentInfoDTO> returnList = shareInfoService.findApiDocumentSimpleInfoByRequest(request);
        return returnList;
    }

    @GetMapping("/get/{id}")
    public ShareInfo get(@PathVariable String id) {
        return shareInfoService.get(id);
    }

    @PostMapping("/selectApiInfoByParam")
    public List<ApiDocumentInfoDTO> selectApiInfoByParam(@RequestBody ApiDocumentRequest request) {
        List<ApiDocumentInfoDTO> returnList = new ArrayList<>();
        if (request.getApiIdList() != null) {
            //要根据ids的顺序进行返回排序
            List<ApiDefinitionWithBLOBs> apiModels = apiDefinitionService.getBLOBs(request.getApiIdList());
            Map<String, ApiDefinitionWithBLOBs> apiModelMaps = apiModels.stream().collect(Collectors.toMap(ApiDefinitionWithBLOBs::getId, a -> a, (k1, k2) -> k1));
            Map<String, User> userIdMap = userService.queryName();
            for (String id : request.getApiIdList()) {
                ApiDefinitionWithBLOBs model = apiModelMaps.get(id);
                if (model == null) {
                    model = new ApiDefinitionWithBLOBs();
                    model.setId(id);
                    model.setName(id);
                }
                ApiDocumentInfoDTO returnDTO = shareInfoService.conversionModelToDTO(model,userIdMap);
                returnList.add(returnDTO);
            }
        }
        return returnList;
    }

    @GetMapping("/selectApiInfoById/{id}")
    public ApiDocumentInfoDTO selectApiInfoById(@PathVariable String id) {
        ApiDefinitionWithBLOBs apiModel = apiDefinitionService.getBLOBs(id);
        ApiDocumentInfoDTO returnDTO = new ApiDocumentInfoDTO();
        try {
            Map<String, User> userIdMap = userService.queryName();
            returnDTO = shareInfoService.conversionModelToDTO(apiModel,userIdMap);
        } catch (Exception e) {
            LogUtil.error(e);
        }
        returnDTO.setSelectedFlag(true);
        return returnDTO;
    }

    @PostMapping("/generateApiDocumentShareInfo")
    public ShareInfoDTO generateApiDocumentShareInfo(@RequestBody ApiDocumentShareRequest request) {
        request.setCreateUserId(SessionUtils.getUserId());
        ShareInfo apiShare = shareInfoService.generateApiDocumentShareInfo(request);
        ShareInfoDTO returnDTO = shareInfoService.conversionShareInfoToDTO(apiShare);
        return returnDTO;
    }

    @PostMapping("/generateShareInfoWithExpired")
    public ShareInfoDTO generateShareInfo(@RequestBody ShareInfo request) {
        request.setCreateUserId(SessionUtils.getUserId());
        ShareInfo apiShare = shareInfoService.createShareInfo(request);
        ShareInfoDTO returnDTO = shareInfoService.conversionShareInfoToDTO(apiShare);
        return returnDTO;
    }

    @PostMapping("/selectHistoryReportById")
    public ReportStatisticsWithBLOBs selectById(@RequestBody ReportStatisticsSaveRequest request) {
        return reportStatisticsService.selectById(request.getId());
    }
}
