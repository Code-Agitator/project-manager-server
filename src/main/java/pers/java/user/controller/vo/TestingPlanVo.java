package pers.java.user.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import pers.java.user.domain.TestingPlan;
import pers.java.user.domain.User;


@Data
@Accessors(chain = true)
public class TestingPlanVo extends TestingPlan {
    User user;
}
