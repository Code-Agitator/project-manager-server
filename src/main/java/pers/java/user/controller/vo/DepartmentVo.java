package pers.java.user.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import pers.java.user.domain.Department;
import pers.java.user.domain.User;

@Data
@Accessors(chain = true)
public class DepartmentVo extends Department {

    User user;
}
