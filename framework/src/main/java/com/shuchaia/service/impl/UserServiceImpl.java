package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.User;
import com.shuchaia.domain.vo.UserInfoVo;
import com.shuchaia.enums.AppHttpCodeEnum;
import com.shuchaia.exception.SystemException;
import com.shuchaia.service.UserService;
import com.shuchaia.mapper.UserMapper;
import com.shuchaia.utils.BeanCopyUtils;
import com.shuchaia.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author Wang
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-06-30 16:05:12
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 查看当前用户信息
     * @return
     */
    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);

        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        // 判空
//        if (!StringUtils.hasText(user.getUserName())) {
//            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
//        }
//        if (!StringUtils.hasText(user.getPassword())) {
//            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
//        }
//        if (!StringUtils.hasText(user.getEmail())) {
//            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
//        }
//        if (!StringUtils.hasText(user.getNickName())) {
//            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
//        }

        // 除了密码，其他都不能重复
        if (!checkUserNameUnique(user)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (!checkNickNameUnique(user)) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        if (!checkEmailUnique(user)) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }

        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        save(user);
        return ResponseResult.okResult();
    }

    /**
     * @param user
     * @return true: username唯一
     */
    @Override
    public boolean checkUserNameUnique(User user) {
        return count(Wrappers.lambdaQuery(User.class).eq(User::getUserName, user.getUserName())) == 0;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean checkPhoneUnique(User user) {
        return count(Wrappers.lambdaQuery(User.class).eq(User::getPhonenumber, user.getPhonenumber())) == 0;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean checkEmailUnique(User user) {
        return count(Wrappers.lambdaQuery(User.class).eq(User::getPhonenumber, user.getPhonenumber())) == 0;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean checkNickNameUnique(User user) {
        return count(Wrappers.lambdaQuery(User.class).eq(User::getNickName, user.getNickName())) == 0;
    }


}




