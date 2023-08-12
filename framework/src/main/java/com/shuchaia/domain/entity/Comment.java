package com.shuchaia.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 评论表
 * @TableName tb_comment
 */
@TableName(value ="tb_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论类型（0代表文章评论，1代表友链评论）
     */
    @TableField(value = "type")
    private String type;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 根评论id
     */
    @TableField(value = "root_id")
    private Long rootId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    @NotBlank(message = "评论内容不能为空")
    @Length(max = 120, message = "评论长度在0-120之间")
    private String content;

    /**
     * 所回复的目标评论的userid
     */
    @TableField(value = "to_comment_user_id")
    private Long toCommentUserId;

    /**
     * 回复目标评论id
     */
    @TableField(value = "to_comment_id")
    private Long toCommentId;

    /**
     * 创建人的用户id
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}