package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.User;
import com.shuchaia.service.UserService;
import com.shuchaia.validate.group.InsertGroup;
import com.shuchaia.validate.group.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/7/5 14:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查看个人信息
     * @return
     */
    @GetMapping("/userInfo")
    @SystemLog(businessName="查看个人信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    /**
     * 更新个人信息
     * @return
     */
    @PutMapping("/userInfo")
    @SystemLog(businessName="更新个人信息")
    public ResponseResult updateUserInfo(@RequestBody @Validated({UpdateGroup.class, Default.class}) User user) {
        return userService.updateUserInfo(user);
    }

    /**
     * 注册（新增用户）
     * @param user
     * @return
     */
    @PostMapping("/register")
    @SystemLog(businessName = "注册")
    public ResponseResult register(@RequestBody @Validated({InsertGroup.class, Default.class}) User user) {
        return userService.register(user);
    }
}
