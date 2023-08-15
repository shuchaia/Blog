package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/26 20:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获得热门文章列表
     * @return
     */
    @GetMapping("/hotArticleList")
    @SystemLog(businessName = "查看热门文章列表")
    public ResponseResult hotArticleList(){

        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

    /**
     * 获得首页/分类页面的文章列表
     * @param pageNum 当前页数
     * @param pageSize 每页数据量
     * @param categoryId 当前的分类ID（if null就是首页）
     * @return
     */

    @GetMapping("/articleList")
    @SystemLog(businessName = "获得首页/分类页面的文章列表")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    /**
     * 根据文章id获得文章详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @SystemLog(businessName = "根据文章id获得文章详情")
    public ResponseResult getArticleDetail(@PathVariable Long id) {
        return articleService.getArticleDetail(id);
    }

    /**
     * 更新浏览量
     * @param id
     * @return
     */
    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "更新浏览量")
    public ResponseResult updateViewCount(@PathVariable Long id) {
        return articleService.updateViewCount(id);
    }
}
