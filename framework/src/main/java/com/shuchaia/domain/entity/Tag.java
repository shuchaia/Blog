package com.shuchaia.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签
 * @TableName tb_tag
 */
@TableName(value ="tb_tag")
@Data
public class Tag implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "create_by")
    private Long create_by;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 
     */
    @TableField(value = "update_by")
    private Long update_by;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableField(value = "del_flag")
    private Integer del_flag;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}