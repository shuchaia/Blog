package com.shuchaia.filter;

import com.alibaba.fastjson.JSON;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.LoginUser;
import com.shuchaia.enums.AppHttpCodeEnum;
import com.shuchaia.utils.JwtUtil;
import com.shuchaia.utils.RedisCache;
import com.shuchaia.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName JwtAuthenticationTokenFilter
 * @Description 校验token是否合法
 * @Author shuchaia
 * @Date 2023/7/4 16:32
 * @Version 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    /**
     * 获得token
     * 解析token
     * 根据token的内容（userId）从redis中获得数据
     * 存入SecurityContextHolder
     *
     * @param request     http请求
     * @param response    http响应
     * @param filterChain 过滤器链
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获得token
        String token = request.getHeader("token");
        // token不存在，说明该接口不需要登录，直接放行
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            // 直接返回，避免执行下面的代码
            return;
        }

        // 解析token
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            // token超时
            // 响应告诉前端，需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();
        // 从redis中获取数据
        LoginUser loginUser = redisCache.getCacheObject(SystemConstants.LOGIN_KEY + userId);

        if (Objects.isNull(loginUser)) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        // 将数据存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}
