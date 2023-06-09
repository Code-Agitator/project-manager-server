package pers.java.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.*;
import lombok.Data;

/**
 * 用户表
 *
 * @TableName t_user
 */
@TableName(value = "t_user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工号
     */
    @ApiModelProperty("工号")
    private String no;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private Integer roleId;


    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 座位
     */
    @ApiModelProperty("座位")
    private String seat;

    /**
     * 0-离职 1-在职 2-实习生
     */
    @ApiModelProperty("0-离职 1-在职 2-实习生")
    private Integer status;

    /**
     * 部门id
     */
    @ApiModelProperty("部门id")
    private Integer departmentId;

    /**
     *
     */
    private Date createTime;

    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}