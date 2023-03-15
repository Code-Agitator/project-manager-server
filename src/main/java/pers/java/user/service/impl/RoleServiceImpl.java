package pers.java.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.java.user.domain.Role;
import pers.java.user.service.RoleService;
import pers.java.user.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 14820
* @description 针对表【t_role(角色表)】的数据库操作Service实现
* @createDate 2023-03-15 15:42:06
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




