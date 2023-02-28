package pers.llz.user.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pers.llz.user.config.mybatis.handler.CustomSqlInjector;

/**
 * Mybatis plus 配置
 *
 * @author Agitator
 */
@Configuration
@MapperScan(basePackages = "pers.llz.user.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {
    /**
     * 分页插件
     *
     * @return interceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }


    @Bean
    public ISqlInjector sqlInjector() {
        return new CustomSqlInjector();
    }


}
