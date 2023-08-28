package com.shuchaia.service;

import com.shuchaia.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Wang
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-06-30 16:05:12
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
