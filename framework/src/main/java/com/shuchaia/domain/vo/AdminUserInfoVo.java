package com.shuchaia.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName AdminUserInfoVo
 * @Description 用于封装getInfo接口的返回数据
 * @Author shuchaia
 * @Date 2023/8/27 21:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AdminUserInfoVo {
    private List<String> permissions;
    private List<String> roles;
    private UserInfoVo user;
}
