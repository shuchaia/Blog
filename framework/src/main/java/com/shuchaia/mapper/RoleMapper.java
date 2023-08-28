package com.shuchaia.mapper;

import com.shuchaia.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Wang
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2023-06-30 16:05:12
* @Entity com.shuchaia.domain.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);
}




