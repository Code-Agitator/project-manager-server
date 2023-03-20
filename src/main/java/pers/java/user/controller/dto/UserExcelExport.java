package pers.java.user.controller.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @TableName t_user
 */
@Data
public class UserExcelExport implements Serializable {

    /**
     * 工号
     */
    @ExcelProperty("工号")
    private String no;


    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;
    /**
     * 手机号码
     */
    @ExcelProperty("手机号码")
    private String phone;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String email;

    /**
     * 座位
     */
    @ExcelProperty("座位")
    private String seat;

    /**
     * 0-离职 1-在职 2-实习生
     */
    @ExcelIgnore
    private Integer status;

    @ExcelProperty("状态")
    private String statusStr;

    /**
     * 角色id
     */
    @ExcelIgnore
    private Integer roleId;

    /**
     * 角色
     */
    @ExcelProperty("角色")
    private String roleName;


    /**
     * 部门id
     */
    @ExcelProperty("部门编号")
    private Integer departmentId;

    @ExcelProperty("部门")
    private String departmentName;


    /**
     *
     */
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}