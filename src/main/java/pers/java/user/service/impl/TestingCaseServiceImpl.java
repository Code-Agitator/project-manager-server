package pers.java.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.java.user.mapper.TestingCaseMapper;
import pers.java.user.service.TestingCaseService;
import pers.java.user.domain.TestingCase;
import org.springframework.stereotype.Service;

/**
* @author 14820
* @description 针对表【t_testing_case(测试用例)】的数据库操作Service实现
* @createDate 2023-03-15 17:42:39
*/
@Service
public class TestingCaseServiceImpl extends ServiceImpl<TestingCaseMapper, TestingCase>
    implements TestingCaseService {

}




