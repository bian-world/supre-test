package com.supretest.ui.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.api.dto.automation.ApiScenarioDTO;
import com.supretest.api.dto.automation.ApiScenarioRequest;
import com.supretest.base.domain.UiElement;
import com.supretest.base.domain.UiElementLocationType;
import com.supretest.base.domain.UiElementOperation;
import com.supretest.base.domain.UiPage;
import com.supretest.commons.constants.PermissionConstants;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.service.CheckPermissionService;
import com.supretest.ui.dto.UiElementDTO;
import com.supretest.ui.dto.UiPageDTO;
import com.supretest.ui.service.UiElementService;
import com.supretest.ui.service.UiPageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/ui/element")
public class UiElementController {

    @Resource
    private CheckPermissionService checkPermissionService;

    @Resource
    private UiElementService uiElementService;

    @PostMapping(value = "/create", consumes = {"application/json"})
    @RequiresPermissions(PermissionConstants.PROJECT_UI_PAGE_READ_CREATE)
    public UiElement create(@RequestBody UiElementDTO request) {
//        checkPermissionService.checkProjectOwner(request.getProjectId());
        return uiElementService.create(request);
    }

    @PostMapping(value = "/update", consumes = {"application/json"})
    public int update(@RequestBody UiElementDTO request) {
        return uiElementService.update(request);
    }

    @PostMapping(value = "/delete", consumes = {"application/json"})
    public int delete(@RequestBody UiElementDTO request) {
        return uiElementService.delete(request);
    }

    @GetMapping(value = "/get/{id}", consumes = {"application/json"})
    public UiElement getElement(@PathVariable String id) {
        return uiElementService.getElement(id);
    }

    @GetMapping(value = "/get/LocationType/{id}", consumes = {"application/json"})
    public String getLocationTypeById(@PathVariable String id) {
        return uiElementService.getLocationTypeById(id);
    }

    @GetMapping(value = "/get/operation")
    public List<UiElementOperation> getOperation() {
        return uiElementService.getOperation();
    }

    @GetMapping(value = "/get/locationType")
    public List<UiElementLocationType> getLocationType() {
        return uiElementService.getLocationType();
    }

}
