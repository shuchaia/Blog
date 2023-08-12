package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Article;
import com.shuchaia.domain.entity.Category;
import com.shuchaia.domain.vo.ArticleListVo;
import com.shuchaia.domain.vo.HotArticleVo;
import com.shuchaia.domain.vo.PageVo;
import com.shuchaia.domain.vo.ArticleDetailVo;
import com.shuchaia.mapper.CategoryMapper;
import com.shuchaia.service.ArticleService;
import com.shuchaia.mapper.ArticleMapper;
import com.shuchaia.utils.BeanCopyUtils;
import com.shuchaia.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author Wang
 * @description 针对表【tb_article(文章表)】的数据库操作Service实现
 * @createDate 2023-06-26 20:55:51
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryMapper categoryMapper;

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
            article.setCategoryName(categoryMapper.selectById(article.getCategoryId()).getName());
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
        Category category = categoryMapper.selectById(article.getCategoryId());
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
}




