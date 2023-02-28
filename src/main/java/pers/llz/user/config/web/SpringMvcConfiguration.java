package pers.llz.user.config.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import pers.llz.user.config.web.handler.ResponseReturnValueHandler;
import pers.llz.user.interceptor.BaseHandlerInterceptor;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@ServletComponentScan
@Slf4j
public class SpringMvcConfiguration implements InitializingBean, WebMvcConfigurer {
    @Resource
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    /**
     * 自定义的拦截器
     */
//    @Resource
//    private List<BaseHandlerInterceptor> baseHandlerInterceptors;
//
//
//    @Override
//    public void addInterceptors(@NotNull InterceptorRegistry registry) {
//        baseHandlerInterceptors.forEach(interceptor -> interceptor.register(registry));
//    }


    @Override
    public void afterPropertiesSet() {
        log.info("初始化并配置自定义的ResponseReturnValue处理器 - start");
        List<HttpMessageConverter<?>> messageConverters = requestMappingHandlerAdapter.getMessageConverters();
        List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>(Objects.requireNonNull(requestMappingHandlerAdapter.getReturnValueHandlers()));
        ResponseReturnValueHandler responseReturnValueHandler = new ResponseReturnValueHandler(messageConverters);
        int index = 0;
        for (int i = 0; i < returnValueHandlers.size(); i++) {
            if (RequestResponseBodyMethodProcessor.class.isAssignableFrom(returnValueHandlers.get(i).getClass())) {
                index = i;
                break;
            }
        }
        returnValueHandlers.add(index, responseReturnValueHandler);
        requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);
        log.info("初始化并配置自定义的ResponseReturnValue处理器 - success");
    }
}
