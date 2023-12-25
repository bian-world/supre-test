package com.supretest.consul;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.supretest.base.domain.TestResource;
import com.supretest.commons.constants.ResourcePoolTypeEnum;
import com.supretest.commons.constants.ResourceStatusEnum;
import com.supretest.controller.request.resourcepool.QueryResourcePoolRequest;
import com.supretest.dto.LoadTestDTO;
import com.supretest.dto.NodeDTO;
import com.supretest.dto.TestResourcePoolDTO;
import com.supretest.performance.request.QueryTestPlanRequest;
import com.supretest.performance.service.PerformanceTestService;
import com.supretest.service.TestResourcePoolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(rollbackFor = Exception.class)
public class ConsulService {
    private final Map<String, List<String>> cache = new ConcurrentHashMap<>();
    @Resource
    private TestResourcePoolService testResourcePoolService;
    @Resource
    private PerformanceTestService performanceTestService;

    public Map<String, List<String>> getActiveNodes() {
        if (cache.size() == 0) {
            updateCache();
        }
        return cache;
    }

    public void updateCache() {
        Map<String, List<String>> result = new HashMap<>();

        QueryResourcePoolRequest resourcePoolRequest = new QueryResourcePoolRequest();
        resourcePoolRequest.setStatus(ResourceStatusEnum.VALID.name());
        List<TestResourcePoolDTO> testResourcePoolDTOS = testResourcePoolService.listResourcePools(resourcePoolRequest);
        QueryTestPlanRequest request = new QueryTestPlanRequest();
        List<LoadTestDTO> list = performanceTestService.list(request);
        for (LoadTestDTO loadTestDTO : list) {
            String advancedConfiguration = performanceTestService.getAdvancedConfiguration(loadTestDTO.getId());
            JSONObject adv = JSON.parseObject(advancedConfiguration);
            Object o1 = adv.get("monitorParams");
            if (o1 == null) {
                continue;
            }

            JSONArray monitorParams = adv.getJSONArray("monitorParams");

            for (int i = 0; i < monitorParams.size(); i++) {
                JSONObject o = monitorParams.getJSONObject(i);
                result.put(o.getString("ip") + "-" + o.getInteger("port"), Collections.singletonList("supretest"));
            }
        }
        for (TestResourcePoolDTO pool : testResourcePoolDTOS) {
            if (!StringUtils.equals(pool.getType(), ResourcePoolTypeEnum.NODE.name())) {
                continue;
            }
            List<TestResource> resources = pool.getResources();
            for (TestResource resource : resources) {
                NodeDTO node = JSON.parseObject(resource.getConfiguration(), NodeDTO.class);
                // 资源池默认9100
                int port = 9100;
                if (node.getMonitorPort() != null) {
                    port = node.getMonitorPort();
                }
                result.put(node.getIp() + "-" + port, Collections.singletonList("supretest"));
            }
        }
        cache.clear();
        cache.putAll(result);
    }
}
