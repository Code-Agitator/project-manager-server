package pers.java.user.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import pers.java.user.domain.TestingCase;
import pers.java.user.domain.TestingPlan;
import pers.java.user.domain.User;

@Data
@Accessors(chain = true)
public class TestingCaseVo extends TestingCase {
    TestingPlan testingPlan;
    User user;
}
