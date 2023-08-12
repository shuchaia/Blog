package com.shuchaia.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName LinkVo
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/28 16:26
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {
    private Long id;

    private String name;

    private String logo;

    private String description;

    private String address;
}
