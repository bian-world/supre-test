package com.supretest.base.mapper.ext;

import com.supretest.api.dto.share.ApiDocumentInfoDTO;
import com.supretest.api.dto.share.ApiDocumentRequest;
import com.supretest.base.domain.ShareInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtShareInfoMapper {
    List<ApiDocumentInfoDTO> findApiDocumentSimpleInfoByRequest(@Param("request") ApiDocumentRequest request);
    List<ShareInfo> selectByShareTypeAndShareApiIdWithBLOBs(@Param("shareType") String shareType, @Param("customData") String customData);
}
