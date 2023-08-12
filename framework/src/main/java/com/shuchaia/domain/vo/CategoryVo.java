package com.shuchaia.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CategoryVo
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/27 17:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {
    /**
     *
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;
}
