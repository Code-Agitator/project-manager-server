package pers.llz.user.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.*;
import io.swagger.annotations.*;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户表
 *
 * @TableName t_user
 */
@TableName(value = "t_user_info")
@Data
@Accessors(chain = true)
public class UserInfo implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 性别 1-男 0-女
     */
    @ApiModelProperty("性别 1-男 0-女")
    private Boolean sex;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phone;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String address;
    /**
     * 逻辑删除
     */
    @ApiModelProperty("逻辑删除")
    private Integer deleted;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createdTime;
    /**
     * 地址编码
     */
    @ApiModelProperty("地址编码")
    private String areaCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}