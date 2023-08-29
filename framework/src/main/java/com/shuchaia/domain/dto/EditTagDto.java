package com.shuchaia.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName EditTagDto
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/8/29 11:04
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTagDto {
    private Long id;
    private String name;
    private String remark;
}
