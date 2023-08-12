package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.domain.entity.Menu;
import com.shuchaia.service.MenuService;
import com.shuchaia.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author Wang
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2023-06-30 16:05:12
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




