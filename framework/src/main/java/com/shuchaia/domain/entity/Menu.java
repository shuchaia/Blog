package com.shuchaia.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单权限表
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
// 允许链式编程 setter方法后返回当前Menu对象
@Accessors(chain = true)
public class Menu implements Serializable {
    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @TableField(value = "path")
    private String path;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 是否为外链（0是 1否）
     */
    @TableField(value = "is_frame")
    private Integer isFrame;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField(value = "menu_type")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField(value = "visible")
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 权限标识
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 
     */
    @TableField(value = "del_flag")
    private String delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<Menu> children;
}