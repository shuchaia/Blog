package com.shuchaia.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.shuchaia.domain.ResponseResult;
import com.shuchaia.domain.entity.Category;
import com.shuchaia.domain.vo.ExcelCategoryVo;
import com.shuchaia.enums.AppHttpCodeEnum;
import com.shuchaia.service.CategoryService;
import com.shuchaia.utils.BeanCopyUtils;
import com.shuchaia.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/8/29 11:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        return ResponseResult.okResult(categoryService.listAllCategory());
    }

    /**
    * 导出分类表
    * 需要导出分类的权限
    * @param response:
    * @return void
    */
    @GetMapping("/export")
    // 需要拥有权限
    @PreAuthorize("hasAuthority('content:category:export')")
    public void export(HttpServletResponse response){
        try {
            // 设置下载文件的请求头
            WebUtils.setDownLoadHeader("category.xlsx", response);
            // 从数据库获得需要导出的数据
            List<Category> categoryList = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryList, ExcelCategoryVo.class);
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class)
                    // 不自动关闭流
                    .autoCloseStream(Boolean.FALSE)
                    // 导出到对应的sheet中，sheet名叫分类导出
                    .sheet("分类导出")
                    .doWrite(excelCategoryVos);
        } catch (Exception e) {
            // 出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
}
