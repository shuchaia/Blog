package com.shuchaia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.dto.AddArticleDto;
import com.shuchaia.domain.dto.EditArticleDto;
import com.shuchaia.domain.entity.Article;

/**
* @author Wang
* @description 针对表【tb_article(文章表)】的数据库操作Service
* @createDate 2023-06-26 20:55:51
*/
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto articleDto);

    ResponseResult listPage(Integer pageNum, Integer pageSize, Article article);

    ResponseResult getInfo(Long id);

    ResponseResult edit(EditArticleDto articleDto);
}
