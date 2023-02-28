package pers.llz.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息更新历史
 *
 * @TableName t_user_info_history
 */
@TableName(value = "t_user_info_history")
@Accessors(chain = true)
@Data
public class UserInfoHistory implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer userId;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 手机
     */
    @ApiModelProperty("手机")
    private String phone;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createdTime;
    /**
     * 操作类型
     */
    @ApiModelProperty("操作类型")
    private String operateType;

    /**
     * 地址编码
     */
    @ApiModelProperty("地址编码")
    private String areaCode;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}