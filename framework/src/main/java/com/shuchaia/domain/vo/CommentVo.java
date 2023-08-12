package com.shuchaia.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CommentVo
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/7/4 20:30
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Long id;

    private Long articleId;

    private Long rootId;

    private String content;

    private Long toCommentUserId;

    private String toCommentUserName;

    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private String username;

    private List<CommentVo> children;
}
