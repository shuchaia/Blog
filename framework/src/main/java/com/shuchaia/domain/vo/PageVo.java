package com.shuchaia.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PageVo
 * @Description 封装分页查询数据
 * @Author shuchaia
 * @Date 2023/6/27 19:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    private List row;
    private Long total;
}
