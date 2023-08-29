package com.shuchaia.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ExcelCategoryVo
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/8/29 14:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelCategoryVo {
    @ExcelProperty("分类名")
    private String name;
    @ExcelProperty("描述")
    private String description;
    @ExcelProperty("状态： 0:正常, 1:禁用")
    private String status;
}
