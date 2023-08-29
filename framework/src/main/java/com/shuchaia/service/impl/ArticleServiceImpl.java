package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.dto.AddArticleDto;
import com.shuchaia.domain.dto.EditArticleDto;
import com.shuchaia.domain.entity.Article;
import com.shuchaia.domain.entity.ArticleTag;
import com.shuchaia.domain.entity.Category;
import com.shuchaia.domain.vo.*;
import com.shuchaia.mapper.ArticleMapper;
import com.shuchaia.service.ArticleService;
import com.shuchaia.service.ArticleTagService;
import com.shuchaia.service.CategoryService;
import com.shuchaia.utils.BeanCopyUtils;
import com.shuchaia.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Wang
 * @description 针对表【tb_article(文章表)】的数据库操作Service实现
 * @createDate 2023-06-26 20:55:51
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

        // Bean拷贝
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }

    @Override
    @Transactional
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 置顶的放在最前面
        queryWrapper.orderByDesc(Article::getIsTop);
        // 分类页面
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);

        // 分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

        // 根据categoryId获得categoryName
        for (Article article : articles) {
            article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
        }

        // 封装成vo
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        Article article = getById(id);
        // 根据categoryId获得categoryName

        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            article.setCategoryName(category.getName());
        }

        // 从redis中获得reviewCount
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.VIEW_COUNT_KEY, id.toString());
        article.setViewCount(viewCount.longValue());

        // 封装到vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        // 更新redis中对应id的浏览量
        redisCache.incrementCacheMapValue(SystemConstants.VIEW_COUNT_KEY, id.toString(), 1);
        return ResponseResult.okResult();
    }

    /**
     * @param articleDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        // 增加博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        // 增加博客跟tag之间的关联关系
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        articleTagService.saveBatch(articleTags);

        return ResponseResult.okResult();
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param article
     * @return
     */
    @Override
    public ResponseResult listPage(Integer pageNum, Integer pageSize, Article article) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(article.getTitle()), Article::getTitle, article.getTitle())
                .like(StringUtils.hasText(article.getSummary()), Article::getSummary, article.getSummary())
                .eq(Article::getDelFlag, 0);

        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseResult getInfo(Long id) {
        Article article = getById(id);

        // 通过article_tag表找到对应的tagId
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId, article.getId());
        List<Long> tags = articleTagService.list(wrapper).stream()
                .map(ArticleTag::getTagId)
                .collect(Collectors.toList());
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        articleVo.setTags(tags);

        return ResponseResult.okResult(articleVo);
    }

    /**
     * @param articleDto
     * @return
     */
    @Override
    public ResponseResult edit(EditArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        updateById(article);

        return ResponseResult.okResult();
    }
}




