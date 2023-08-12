package com.shuchaia.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文章标签关联表
 * @TableName tb_article_tag
 */
@TableName(value ="tb_article_tag")
@Data
public class ArticleTag implements Serializable {
    /**
     * 文章id
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long article_id;

    /**
     * 标签id
     */
    @TableId(value = "tag_id")
    private Long tag_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}