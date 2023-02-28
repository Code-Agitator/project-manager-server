package pers.llz.user.mapper;

import pers.llz.user.config.mybatis.handler.CustomBaseMapper;
import pers.llz.user.domain.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 14820
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2023-02-28 11:03:55
* @Entity pers.llz.user.domain.User
*/
public interface UserMapper extends CustomBaseMapper<UserInfo> {

}




