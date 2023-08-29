package com.shuchaia.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ClassName EditArticleDto
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/8/29 14:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditArticleDto {
    private Long id;

    private String title;

    private String content;

    private String summary;

    private Long categoryId;

    private String thumbnail;

    private String isTop;

    private Long viewCount;

    private String isComment;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag;

    private List<Long> tags;
}
