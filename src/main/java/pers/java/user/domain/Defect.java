package pers.java.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.*;
import lombok.Data;

/**
 * 缺陷表
 * @TableName t_defect
 */
@TableName(value ="t_defect")
@Data
public class Defect implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * defect-缺陷 improve-改进 experience-体验
     */
    @ApiModelProperty("defect-缺陷 improve-改进 experience-体验")
    private String type;

    /**
     * 优先级 high-紧急 mid-中 low-低 delay-延迟
     */
    @ApiModelProperty("优先级 high-紧急 mid-中 low-低 delay-延迟")
    private String priority;

    /**
     * 严重程度 none-无 death-致命 important-严重 normal-普通 low-轻微
     */
    @ApiModelProperty("严重程度 none-无 death-致命 important-严重 normal-普通 low-轻微")
    private String level;

    /**
     * 重复概率 none-无 must-必然 high-大概率 mid-偶然 low-难以复现
     */
    @ApiModelProperty("重复概率 none-无 must-必然 high-大概率 mid-偶然 low-难以复现")
    private String repeatedProbability;

    /**
     * 报告人id
     */
    @ApiModelProperty("报告人id")
    private Integer reportUserId;

    /**
     * 经办人id
     */
    @ApiModelProperty("经办人id")
    private Integer userId;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String comment;

    /**
     * 1-待解决 2-正在解决 3-已解决 4-关闭
     */
    @ApiModelProperty("1-待解决 2-正在解决 3-已解决 4-关闭")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}