package com.shuchaia.service;

import com.shuchaia.domain.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Wang
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-06-30 16:05:12
*/
public interface MenuService extends IService<Menu> {
    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
