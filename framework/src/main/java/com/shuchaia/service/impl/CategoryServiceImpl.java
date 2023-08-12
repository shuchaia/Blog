package com.shuchaia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Article;
import com.shuchaia.domain.entity.Category;
import com.shuchaia.domain.vo.CategoryVo;
import com.shuchaia.service.ArticleService;
import com.shuchaia.service.CategoryService;
import com.shuchaia.mapper.CategoryMapper;
import com.shuchaia.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author Wang
* @description 针对表【tb_category(分类表)】的数据库操作Service实现
* @createDate 2023-06-27 16:40:12
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        // 在文章表中获得状态为已发布的分类idList
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        // 状态为已发布的信息
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        // 获得分类id，并且去重
        List<Long> categoryIds = articleList.stream()
                // 仅获得分类id
                .map(Article::getCategoryId)
                .distinct()
                // 转化为set去重
                .collect(Collectors.toList());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        // 状态为正常状态
        categories = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        // 转化为vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}




