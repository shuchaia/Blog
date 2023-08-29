package com.shuchaia.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TagListDto
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/8/29 10:40
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagListDto {
    private String name;
    private String remark;
}
