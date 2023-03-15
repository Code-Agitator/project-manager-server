package pers.java.user.controller.dto;

import lombok.Data;
import pers.java.user.common.utils.entity.PageParam;

import java.util.Date;

@Data
public class UserSearchDto implements PageParam {
    private String name;
    private String phone;
    private Integer status;
    private Date startDate;
    private Date endDate;
    private Integer pageSize;
    private Integer pageNumber;
    private Integer roleId;
    private Integer departmentId;
}
