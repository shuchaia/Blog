package com.shuchaia.service.impl;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.LoginUser;
import com.shuchaia.domain.entity.User;
import com.shuchaia.domain.vo.BlogUserLoginVo;
import com.shuchaia.domain.vo.UserInfoVo;
import com.shuchaia.service.BlogLoginService;
import com.shuchaia.utils.BeanCopyUtils;
import com.shuchaia.utils.JwtUtil;
import com.shuchaia.utils.RedisCache;
import com.shuchaia.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.shuchaia.constants.SystemConstants.BLOG_LOGIN_KEY;

/**
 * @ClassName BlogLoginServiceImpl
 * @Description 关于登录登出的业务操作
 * @Author shuchaia
 * @Date 2023/7/3 16:12
 * @Version 1.0
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject(BLOG_LOGIN_KEY+userId,loginUser);

        //把token和userinfo封装 返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        // 获取userId
        Long id = SecurityUtils.getUserId();
        // 删除redis中的用户信息
        redisCache.deleteObject(BLOG_LOGIN_KEY+id);
        return ResponseResult.okResult();
    }
}
