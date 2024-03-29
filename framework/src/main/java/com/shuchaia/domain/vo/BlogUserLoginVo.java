package com.shuchaia.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName BlogUserLoginVo
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/30 18:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVo {
    private String token;

    private UserInfoVo userInfo;
}
