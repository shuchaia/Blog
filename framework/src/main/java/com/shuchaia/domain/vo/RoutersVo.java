package com.shuchaia.domain.vo;

import com.shuchaia.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName RoutersVo
 * @Description 用于封装getRouters接口的返回数据
 * @Author shuchaia
 * @Date 2023/8/28 20:41
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {
    private List<Menu> menus;
}
