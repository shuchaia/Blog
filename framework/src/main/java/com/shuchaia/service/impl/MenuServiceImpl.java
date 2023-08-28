package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.entity.Menu;
import com.shuchaia.mapper.MenuMapper;
import com.shuchaia.service.MenuService;
import com.shuchaia.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wang
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2023-06-30 16:05:12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    /**
     * @param userId
     * @return 当前用户的权限信息
     */
    @Override
    public List<String> selectPermsByUserId(Long userId) {
        if (SecurityUtils.isAdmin()) {
            // 表示是管理员，返回所有权限
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU_TYPE_MENU, SystemConstants.MENU_TYPE_BUTTON)
                    .eq(Menu::getStatus, SystemConstants.MENU_STATUS_NORMAL);

            List<String> permissions = list(wrapper).stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return permissions;
        }
        return menuMapper.selectPermsByUserId(userId);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        // 判断是否是管理员
        List<Menu> menus = null;
        if (SecurityUtils.isAdmin()) {
            menus = menuMapper.selectAllRouterMenu();
        }else {
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        // 构建Menu Tree
        List<Menu> menuTree = buildMenuTree(menus, 0L);

        return menuTree;
    }

    private List<Menu> buildMenuTree(List<Menu> menus, long parentId) {
        List<Menu> menuTree = menus.stream()
                // 获得第一层的节点
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());

        return menuTree;
    }

    /**
    * 递归获得每一个menu的孩子
    * @param menu: 当前menu
    * @param menus: 所有menu的列表
    * @return List<Menu> menus中属于menu的children的列表
    */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> children = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
        return children;
    }
}




