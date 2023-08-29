package com.shuchaia.controller;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.dto.AddArticleDto;
import com.shuchaia.domain.dto.EditArticleDto;
import com.shuchaia.domain.entity.Article;
import com.shuchaia.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/8/29 11:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto articleDto) {
        return articleService.add(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize,
                               Article article) {
        return articleService.listPage(pageNum, pageSize, article);
    }

    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable Long id) {
        return articleService.getInfo(id);
    }

    @PutMapping
    public ResponseResult edit(@RequestBody EditArticleDto articleDto) {
        return articleService.edit(articleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}
