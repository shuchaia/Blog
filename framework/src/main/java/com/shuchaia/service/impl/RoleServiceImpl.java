package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.domain.entity.Role;
import com.shuchaia.service.RoleService;
import com.shuchaia.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Wang
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2023-06-30 16:05:12
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




