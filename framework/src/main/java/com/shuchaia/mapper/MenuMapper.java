package com.shuchaia.mapper;

import com.shuchaia.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Wang
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2023-06-30 16:05:12
* @Entity com.shuchaia.domain.entity.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}




