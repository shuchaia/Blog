package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.domain.entity.Role;
import com.shuchaia.mapper.RoleMapper;
import com.shuchaia.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Wang
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2023-06-30 16:05:12
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    RoleMapper roleMapper;

    /**
     * @param id
     * @return 当前用户的role信息
     */
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 判断是否是管理员
        if (id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        return roleMapper.selectRoleKeyByUserId(id);
    }
}




