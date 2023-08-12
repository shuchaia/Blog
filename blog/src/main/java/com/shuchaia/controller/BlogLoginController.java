package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.User;
import com.shuchaia.enums.AppHttpCodeEnum;
import com.shuchaia.exception.SystemException;
import com.shuchaia.service.BlogLoginService;
import com.shuchaia.validate.group.LoginGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BlogLoginController
 * @Description 处理博客前台登录登出请求
 * @Author shuchaia
 * @Date 2023/7/3 16:10
 * @Version 1.0
 */
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    public ResponseResult login(@RequestBody @Validated(LoginGroup.class) User user) {
//        if (!StringUtils.hasText(user.getUserName())){
//            // 提示 必须传用户名
//            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
//        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @SystemLog(businessName = "登出")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
