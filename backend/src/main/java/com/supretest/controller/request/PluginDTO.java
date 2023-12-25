package com.supretest.controller.request;

import com.supretest.base.domain.Plugin;
import lombok.Data;

@Data
public class PluginDTO extends Plugin {
    private Boolean license;
}
