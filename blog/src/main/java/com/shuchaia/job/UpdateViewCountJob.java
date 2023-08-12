package com.shuchaia.job;

import com.shuchaia.constants.SystemConstants;
import com.shuchaia.domain.entity.Article;
import com.shuchaia.service.ArticleService;
import com.shuchaia.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName TestJob
 * @Description 定时(5 mins)把Redis中的浏览量更新到数据库中
 * @Author shuchaia
 * @Date 2023/7/7 11:16
 * @Version 1.0
 */
@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateViewCount(){
        // 从redis中取数据
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.VIEW_COUNT_KEY);

        // 获取更新数据
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        // 更新到数据库
        articleService.updateBatchById(articles);
    }
}
