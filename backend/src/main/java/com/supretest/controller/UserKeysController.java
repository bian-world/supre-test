package com.supretest.controller;

import com.supretest.base.domain.UserKey;
import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.security.ApiKeyHandler;
import com.supretest.service.UserKeyService;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("user/key")
public class UserKeysController {

    @Resource
    private UserKeyService userKeyService;

    @GetMapping("info")
    public List<UserKey> getUserKeysInfo() {
        String userId = SessionUtils.getUser().getId();
        return userKeyService.getUserKeysInfo(userId);
    }

    @GetMapping("validate")
    public String validate(ServletRequest request) {
        return ApiKeyHandler.getUser(WebUtils.toHttp(request));
    }

    @GetMapping("generate")
    @MsAuditLog(module = OperLogModule.PERSONAL_INFORMATION_APIKEYS, type = OperLogConstants.CREATE, title = "API KEY")
    public void generateUserKey() {
        String userId = SessionUtils.getUser().getId();
        userKeyService.generateUserKey(userId);
    }

    @GetMapping("delete/{id}")
    @MsAuditLog(module = OperLogModule.PERSONAL_INFORMATION_APIKEYS, type = OperLogConstants.DELETE, title = "API KEY")
    public void deleteUserKey(@PathVariable String id) {
        userKeyService.deleteUserKey(id);
    }

    @GetMapping("active/{id}")
    public void activeUserKey(@PathVariable String id) {
        userKeyService.activeUserKey(id);
    }

    @GetMapping("disable/{id}")
    @MsAuditLog(module = OperLogModule.PERSONAL_INFORMATION_APIKEYS, type = OperLogConstants.UPDATE, title = "API KEY")
    public void disabledUserKey(@PathVariable String id) {
        userKeyService.disableUserKey(id);
    }
}
