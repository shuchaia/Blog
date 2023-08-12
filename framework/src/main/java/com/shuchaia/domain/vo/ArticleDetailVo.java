package com.shuchaia.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName ArticleDetailVo
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/27 21:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {
    private Long id;

    /**
     * 标题
     */
    private String title;

    private String content;

    private Long categoryId;

    private String categoryName;

    private Long viewCount;

    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;

    private Date createTime;

}
