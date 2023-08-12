package com.shuchaia.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.entity.Article;
import com.shuchaia.mapper.ArticleMapper;
import com.shuchaia.service.ArticleService;
import com.shuchaia.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName TestRunner
 * @Description 应用启动时的初始化操作
 * @Author shuchaia
 * @Date 2023/7/7 11:11
 * @Version 1.0
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleMapper.selectList(null);
        // 创建id->viewCound的map映射
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        // 存储到redis中
        redisCache.setCacheMap(SystemConstants.VIEW_COUNT_KEY, viewCountMap);
    }
}
