package pers.java.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.java.user.mapper.TestingPlanMapper;
import pers.java.user.service.TestingPlanService;
import pers.java.user.domain.TestingPlan;
import org.springframework.stereotype.Service;

/**
* @author 14820
* @description 针对表【t_testing_plan(测试计划管理)】的数据库操作Service实现
* @createDate 2023-03-15 17:42:39
*/
@Service
public class TestingPlanServiceImpl extends ServiceImpl<TestingPlanMapper, TestingPlan>
    implements TestingPlanService {

}




