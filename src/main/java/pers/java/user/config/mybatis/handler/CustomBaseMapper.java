package pers.java.user.config.mybatis.handler;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

public interface CustomBaseMapper<T> extends BaseMapper<T> {
    void recoverLogicDeleted(Serializable id);
}
