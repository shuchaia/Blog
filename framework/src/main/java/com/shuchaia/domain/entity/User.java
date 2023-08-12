package com.shuchaia.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.shuchaia.validate.group.InsertGroup;
import com.shuchaia.validate.group.LoginGroup;
import com.shuchaia.validate.group.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

/**
 * 用户表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = UpdateGroup.class, message = "用户ID不能为空")
    @Null(groups = InsertGroup.class, message = "参数非法")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    @NotBlank(message = "用户名不能为空", groups = {InsertGroup.class, LoginGroup.class})
    private String userName;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    @NotBlank(message = "昵称不能为空", groups = InsertGroup.class)
    private String nickName;

    /**
     * 密码
     */
    @TableField(value = "password")
    //    @Size(min = 6, max = 12)
    @NotBlank(message = "密码不能为空", groups = {InsertGroup.class, LoginGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9\u4E00-\u9FA5]+$")
    private String password;

    // todo: 自定义注释
    /**
     * 用户类型：0代表普通用户，1代表管理员
     */
    @TableField(value = "type")
    @Range(min = 0, max = 1, message = "参数非法")
    private String type;

    /**
     * 账号状态（0正常 1停用）
     */
    @TableField(value = "status")
    @Range(min = 0, max = 1, message = "参数非法")
    private String status;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @NotBlank(message = "邮箱不能为空", groups = InsertGroup.class)
    @Email(message = "请输入正确邮箱")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "phonenumber")
    @Pattern(regexp = "^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$",
            message = "请输入正确手机号")
    private String phonenumber;

    /**
     * 用户性别（0男，1女，2未知）
     */
    @TableField(value = "sex")
    @Range(min = 0, max = 2, message = "参数非法")
    private String sex;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @URL(message = "头像格式错误")
    private String avatar;

    /**
     * 创建人的用户id
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}