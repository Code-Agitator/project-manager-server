package pers.java.user.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.java.user.domain.TestingPlan;
import pers.java.user.service.TestingPlanService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/testing/plan")
@Api(tags = "测试计划")
public class TestingPlanController {
@Resource
    TestingPlanService testingPlanService;

    @PostMapping("/save")
    public TestingPlan save(@RequestBody TestingPlan testingPlan){
        testingPlanService.save(testingPlan);
        return testingPlan;
    }
}
