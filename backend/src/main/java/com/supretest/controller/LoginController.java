package com.supretest.controller;

import com.supretest.commons.constants.OperLogConstants;
import com.supretest.commons.constants.OperLogModule;
import com.supretest.commons.constants.UserSource;
import com.supretest.commons.user.SessionUser;
import com.supretest.commons.utils.RsaKey;
import com.supretest.commons.utils.RsaUtil;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.controller.request.LoginRequest;
import com.supretest.dto.UserDTO;
import com.supretest.i18n.Translator;
import com.supretest.log.annotation.MsAuditLog;
import com.supretest.service.BaseDisplayService;
import com.supretest.service.UserService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private BaseDisplayService baseDisplayService;

    @GetMapping(value = "/isLogin")
    public ResultHolder isLogin() throws NoSuchAlgorithmException {
        RsaKey rsaKey = RsaUtil.getRsaKey();
        if (SecurityUtils.getSubject().isAuthenticated()) {
            UserDTO user = userService.getUserDTO(SessionUtils.getUserId());
            if (user == null) {
                return ResultHolder.error(rsaKey.getPublicKey());
            }
            if (StringUtils.isBlank(user.getLanguage())) {
                user.setLanguage(LocaleContextHolder.getLocale().toString());
            }
            SessionUser sessionUser = SessionUser.fromUser(user);
            SessionUtils.putUser(sessionUser);
            return ResultHolder.success(sessionUser);
        }
        return ResultHolder.error(rsaKey.getPublicKey());
    }

    @PostMapping(value = "/signin")
    @MsAuditLog(module = OperLogModule.AUTH_TITLE, type = OperLogConstants.LOGIN, title = "登录")
    public ResultHolder login(@RequestBody LoginRequest request) {
        SessionUser sessionUser = SessionUtils.getUser();
        if (sessionUser != null) {
            if (!StringUtils.equals(sessionUser.getId(), request.getUsername())) {
                return ResultHolder.error(Translator.get("please_logout_current_user"));
            }
        }
        SecurityUtils.getSubject().getSession().setAttribute("authenticate", UserSource.LOCAL.name());
        ResultHolder result = userService.login(request);
        // 登录是否提示修改密码
        boolean changePassword = userService.checkWhetherChangePasswordOrNot(request);
        result.setMessage(BooleanUtils.toStringTrueFalse(changePassword));
        return result;
    }

    @GetMapping(value = "/currentUser")
    public ResultHolder currentUser() {
        return ResultHolder.success(SecurityUtils.getSubject().getSession().getAttribute("user"));
    }

    @GetMapping(value = "/signout")
    @MsAuditLog(module = OperLogModule.AUTH_TITLE, beforeEvent = "#msClass.getUserId(id)", type = OperLogConstants.LOGIN, title = "登出", msClass = SessionUtils.class)
    public ResultHolder logout() throws Exception {
        userService.logout();
        SecurityUtils.getSubject().logout();
        return ResultHolder.success("");
    }

    /*Get default language*/
    @GetMapping(value = "/language")
    public String getDefaultLanguage() {
        return userService.getDefaultLanguage();
    }

    @GetMapping("display/file/{imageName}")
    public ResponseEntity<byte[]> image(@PathVariable("imageName") String imageName) throws IOException {
        return baseDisplayService.getImage(imageName);
    }
}
