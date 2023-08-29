package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.LoginUser;
import com.shuchaia.domain.entity.Menu;
import com.shuchaia.domain.entity.User;
import com.shuchaia.domain.vo.AdminUserInfoVo;
import com.shuchaia.domain.vo.RoutersVo;
import com.shuchaia.domain.vo.UserInfoVo;
import com.shuchaia.service.LoginService;
import com.shuchaia.service.MenuService;
import com.shuchaia.service.RoleService;
import com.shuchaia.utils.BeanCopyUtils;
import com.shuchaia.utils.SecurityUtils;
import com.shuchaia.validate.group.LoginGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CliassName LoginController
 * @Description: 处理博客前台登录登出请求
 * @Author shuchaia
 * @Date 2023/7/11 21:56
 * @version 1.0
 */
@RestController
@Api(tags = "后台登录")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    @SystemLog(businessName = "登录")
    @ApiOperation(value = "后台登录接口")
    @ApiImplicitParam(name = "user", value = "前端传来的存有用户名的密码的实体类")
    public ResponseResult login(@RequestBody @Validated(LoginGroup.class) User user) {
//        if (!StringUtils.hasText(user.getUserName())){
//            // 提示 必须传用户名
//            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
//        }
        return loginService.login(user);
    }

    /**
    * 获得当前用户（根据token）的权限、角色等信息
    * @param :
    * @return ResponseResult<AdminUserInfoVo>
    */
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();

        // 根据用户id查询权限、角色等信息
        List<String> permissions = menuService.selectPermsByUserId(SecurityUtils.getUserId());
        List<String> roleKeys = roleService.selectRoleKeyByUserId(SecurityUtils.getUserId());

        // 封装成AdminUserInfoVo返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        AdminUserInfoVo vo = new AdminUserInfoVo(permissions, roleKeys, userInfoVo);

        return ResponseResult.okResult(vo);
    }

    /**
    * 获得当前用户的路由信息
    * @param :
    * @return ResponseResult
    */
    @GetMapping("/getRouters")
    public ResponseResult getRouters(){
        // 查询menu 返回结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(SecurityUtils.getUserId());

        // 封装成RoutersVo返回
        RoutersVo routersVo = new RoutersVo(menus);

        return ResponseResult.okResult(routersVo);
    }

    @PostMapping("/user/logout")
    @SystemLog(businessName = "后台登出")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
