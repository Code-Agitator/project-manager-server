package pers.java.user.config.swagger;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.web.method.HandlerMethod;
import pers.java.user.common.result.JsonResult;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;

public class CustomHandlerMethodResolver extends HandlerMethodResolver {
    private final TypeResolver typeResolver;

    public CustomHandlerMethodResolver(TypeResolver typeResolver) {
        super(typeResolver);
        this.typeResolver = typeResolver;
    }

    @Override
    public ResolvedType methodReturnType(HandlerMethod handlerMethod) {
        ResolvedType resolvedType = super.methodReturnType(handlerMethod);
        return typeResolver.resolve(JsonResult.class, resolvedType);
    }
}
