package com.shuchaia.service.impl;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.LoginUser;
import com.shuchaia.domain.entity.User;
import com.shuchaia.service.LoginService;
import com.shuchaia.utils.JwtUtil;
import com.shuchaia.utils.RedisCache;
import com.shuchaia.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.shuchaia.constants.SystemConstants.LOGIN_KEY;

/**
 * @ClassName LoginServiceImpl
 * @Description 关于前台的登录登出的业务操作
 * @Author shuchaia
 * @Date 2023/8/15 16:12
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    /**
     * @param user
     * @return
     */

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
    * 登录
    * @param user:  存有用户名和密码的对象
    * @return ResponseResult
    */
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject(LOGIN_KEY + userId, loginUser);

        //把token封装 返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }

    /**
    * 退出接口
    * @return 退出成功
    */
    @Override
    public ResponseResult logout() {
        // 获取userId
        Long id = SecurityUtils.getUserId();
        // 删除redis中的用户信息
        redisCache.deleteObject(LOGIN_KEY + id);
        return ResponseResult.okResult();
    }
}
