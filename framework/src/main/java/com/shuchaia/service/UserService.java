package com.shuchaia.service;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Wang
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-06-30 16:05:12
*/
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    boolean checkUserNameUnique(User user);

    boolean checkPhoneUnique(User user);

    boolean checkEmailUnique(User user);

    boolean checkNickNameUnique(User user);
}
