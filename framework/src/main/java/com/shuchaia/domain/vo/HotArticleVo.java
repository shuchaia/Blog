package com.shuchaia.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName HotArticleVo
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/27 15:57
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    /**
     * 文章id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 访问量
     */
    private Long viewCount;
}
