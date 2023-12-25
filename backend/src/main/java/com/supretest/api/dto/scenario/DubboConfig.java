package com.supretest.api.dto.scenario;

import com.supretest.api.dto.scenario.request.dubbo.ConfigCenter;
import com.supretest.api.dto.scenario.request.dubbo.ConsumerAndService;
import com.supretest.api.dto.scenario.request.dubbo.RegistryCenter;
import lombok.Data;

@Data
public class DubboConfig {
    private ConfigCenter configCenter;
    private RegistryCenter registryCenter;
    private ConsumerAndService consumerAndService;
}
