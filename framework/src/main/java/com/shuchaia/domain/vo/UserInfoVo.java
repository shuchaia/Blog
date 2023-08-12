package com.shuchaia.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName UserInfoVo
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/30 18:10
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    private String avatar;

    private String email;

    private Long id;

    private String nickName;

    private String sex;
}
