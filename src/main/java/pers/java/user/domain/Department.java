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
 * 部门表
 * @TableName t_department
 */
@TableName(value ="t_department")
@Data
public class Department implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String name;

    /**
     * 部门主管id
     */
    @ApiModelProperty("部门主管id")
    private Integer userId;

    /**
     * 
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}