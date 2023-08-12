package com.shuchaia.controller;

import com.shuchaia.annotation.SystemLog;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/27 16:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获得分类列表
     * @return
     */
    @GetMapping("/getCategoryList")
    @SystemLog(businessName = "获得分类列表")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
