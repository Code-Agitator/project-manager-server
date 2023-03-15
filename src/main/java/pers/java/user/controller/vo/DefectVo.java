package pers.java.user.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import pers.java.user.domain.Defect;
import pers.java.user.domain.User;

@Data
@Accessors(chain = true)
public class DefectVo extends Defect {
    User reportUser;
    User user;
}
