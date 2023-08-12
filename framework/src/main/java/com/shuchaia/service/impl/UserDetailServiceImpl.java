package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuchaia.domain.entity.LoginUser;
import com.shuchaia.domain.entity.User;
import com.shuchaia.mapper.UserMapper;
import kotlin.jvm.internal.Lambda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName UserDetailServiceImpl
 * @Description 重写UserDetailsService实现类，覆盖springboot自动配置的实现类
 *              使得程序从数据库取User数据
 * @Author shuchaia
 * @Date 2023/7/3 16:23
 * @Version 1.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据username从数据库获取数据
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }

        // 返回用户信息
        // todo 查询权限信息并封装

        return new LoginUser(user);
    }
}
