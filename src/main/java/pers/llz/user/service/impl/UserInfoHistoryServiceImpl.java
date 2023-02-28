package pers.llz.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.llz.user.domain.UserInfoHistory;
import pers.llz.user.service.UserInfoHistoryService;
import pers.llz.user.mapper.UserInfoHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 14820
* @description 针对表【t_user_info_history(用户信息更新历史)】的数据库操作Service实现
* @createDate 2023-02-28 12:02:10
*/
@Service
public class UserInfoHistoryServiceImpl extends ServiceImpl<UserInfoHistoryMapper, UserInfoHistory>
    implements UserInfoHistoryService{

}




