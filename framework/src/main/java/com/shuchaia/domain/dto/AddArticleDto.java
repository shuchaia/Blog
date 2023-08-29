package com.shuchaia.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName AddArticleDto
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/8/29 11:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddArticleDto {
    private String title;
    private String thumbnail;
    private String isTop;
    private String isComment;
    private String content;
    private List<Long> tags;
    private Long categoryId;
    private String summary;
    private String status;
    private Long viewCount;
    private Long id;
}
