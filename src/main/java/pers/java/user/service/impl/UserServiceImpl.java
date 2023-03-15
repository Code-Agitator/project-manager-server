package pers.java.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.java.user.domain.User;
import pers.java.user.service.UserService;
import pers.java.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 14820
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2023-03-15 15:42:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




