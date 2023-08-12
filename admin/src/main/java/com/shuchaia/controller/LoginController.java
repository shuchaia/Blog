package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.User;
import com.shuchaia.validate.group.LoginGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CliassName LoginController
 * @Description: 处理博客前台登录登出请求
 * @Author shuchaia
 * @Date 2023/7/11 21:56
 * @version 1.0
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    public ResponseResult login(@RequestBody @Validated(LoginGroup.class) User user) {
//        if (!StringUtils.hasText(user.getUserName())){
//            // 提示 必须传用户名
//            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
//        }
        return loginService.login(user);
    }
}
