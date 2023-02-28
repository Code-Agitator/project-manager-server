package pers.llz.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.llz.user.domain.UserInfo;
import pers.llz.user.service.UserInfoService;
import pers.llz.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 14820
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2023-02-28 11:03:55
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserMapper, UserInfo>
    implements UserInfoService {

}




