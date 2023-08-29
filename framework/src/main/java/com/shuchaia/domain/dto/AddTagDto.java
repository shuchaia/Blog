package com.shuchaia.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AddTagDto
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/8/29 11:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTagDto {
    private String name;
    private String remark;
}
