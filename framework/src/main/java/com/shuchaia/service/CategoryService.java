package com.shuchaia.service;

import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Wang
* @description 针对表【tb_category(分类表)】的数据库操作Service
* @createDate 2023-06-27 16:40:12
*/
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    ResponseResult listAllCategory();
}
