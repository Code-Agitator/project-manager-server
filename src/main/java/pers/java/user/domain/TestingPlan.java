package pers.java.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 测试计划管理
 * @TableName t_testing_plan
 */
@TableName(value ="t_testing_plan")
@Data
public class TestingPlan implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 测试计划名称
     */
    private String name;

    /**
     * 测试计划连接
     */
    private String link;

    /**
     * 负责人
     */
    private Integer userId;

    /**
     * 
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}