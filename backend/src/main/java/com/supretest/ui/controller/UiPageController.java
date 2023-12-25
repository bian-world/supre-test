package com.supretest.ui.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supretest.base.domain.UiElement;
import com.supretest.base.domain.UiPage;
import com.supretest.commons.constants.PermissionConstants;
import com.supretest.commons.utils.PageUtils;
import com.supretest.commons.utils.Pager;
import com.supretest.service.CheckPermissionService;
import com.supretest.ui.controller.request.UiPageRequest;
import com.supretest.ui.dto.UiElementDTO;
import com.supretest.ui.dto.UiPageDTO;
import com.supretest.ui.dto.UiPageElementsDTO;
import com.supretest.ui.service.UiPageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/ui/page")
public class UiPageController {

    @Resource
    private CheckPermissionService checkPermissionService;

    @Resource
    private UiPageService uiPageService;

    @PostMapping(value = "/list/{goPage}/{pageSize}", consumes = {"application/json"})
    public Pager<List<UiPageDTO>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody UiPageRequest request) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        // 查询场景环境
        return PageUtils.setPageInfo(page, uiPageService.list(request));
    }

    @PostMapping(value = "/listAll", consumes = {"application/json"})
    public List<UiPageDTO> list(@RequestBody UiPageRequest request) {
        return  uiPageService.list(request);
    }

    @PostMapping(value = "/create", consumes = {"application/json"})
    @RequiresPermissions(PermissionConstants.PROJECT_UI_PAGE_READ_CREATE)
    public UiPage create(@RequestBody UiPageDTO request) {
//        checkPermissionService.checkProjectOwner(request.getProjectId());
        return uiPageService.create(request);
    }

    @PostMapping(value = "/update", consumes = {"application/json"})
    public int update(@RequestBody UiPageDTO request) {
        return uiPageService.update(request);
    }

    @PostMapping(value = "/delete", consumes = {"application/json"})
    public int delete(@RequestBody UiPageDTO request) {
        return uiPageService.delete(request);
    }

    @PostMapping(value = "/edit/pageElements", consumes = {"application/json"})
    public UiPageElementsDTO editPageElements(@RequestBody UiPageElementsDTO request) {
        return uiPageService.editPageElements(request);
    }

    @GetMapping(value = "/get/{id}")
    public UiPage getPage(@PathVariable String id) {
        return uiPageService.getPage(id);
    }

    @GetMapping(value = "/releated/element/{id}")
    public List<UiElement> getPageElements(@PathVariable String id) {
        return uiPageService.getPageElements(id);
    }


}
