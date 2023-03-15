package pers.java.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 测试用例
 * @TableName t_testing_case
 */
@TableName(value ="t_testing_case")
@Data
public class TestingCase implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用例计划id
     */
    private Integer plantId;

    /**
     * 用例标题
     */
    private String name;

    /**
     * 输出者
     */
    private Integer userId;

    /**
     * 用例链接
     */
    private String link;

    /**
     * 测试结果
     */
    private String result;

    /**
     * 备注
     */
    private String comment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}