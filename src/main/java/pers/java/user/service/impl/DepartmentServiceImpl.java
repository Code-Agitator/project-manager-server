package pers.java.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.java.user.domain.Department;
import pers.java.user.service.DepartmentService;
import pers.java.user.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author 14820
* @description 针对表【t_department(部门表)】的数据库操作Service实现
* @createDate 2023-03-15 15:42:06
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




