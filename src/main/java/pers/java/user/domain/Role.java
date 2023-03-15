package pers.java.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.*;
import lombok.Data;

/**
 * 角色表
 * @TableName t_role
 */
@TableName(value ="t_role")
@Data
public class Role implements Serializable {
    /**
     * 权限编号
     */
    @ApiModelProperty("权限编号")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 权限字符
     */
    @ApiModelProperty("权限字符")
    private String roleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}