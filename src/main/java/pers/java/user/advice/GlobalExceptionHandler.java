package pers.java.user.advice;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.java.user.advice.exception.WithResultCodeException;
import pers.java.user.advice.exception.children.ServerException;
import pers.java.user.advice.exception.children.UnauthorizedException;
import pers.java.user.common.result.JsonResult;
import pers.java.user.common.result.ResultCode;
import pers.java.user.common.result.ResultTool;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * 控制器增强
 *
 * @author Agitator
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截表单验证
     *
     * @param e 表单验证失败
     * @return json
     */
    @ExceptionHandler({BindException.class})
    public JsonResult<?> bindException(BindException e) {
        BindingResult bindResult = e.getBindingResult();
        String message = Objects.requireNonNull(bindResult.getFieldError()).getDefaultMessage();
        return ResultTool.fail(ResultCode.PARAM_NOT_VALID, message);
    }

    /**
     * 拦截json验证
     *
     * @param e json验证失败
     * @return json
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<?> bindException(MethodArgumentNotValidException e) {
        BindingResult bindResult = e.getBindingResult();
        String message = Objects.requireNonNull(bindResult.getFieldError()).getDefaultMessage();
        return ResultTool.fail(ResultCode.PARAM_NOT_VALID, message);
    }

    /**
     * 处理数据校验错误
     *
     * @param exception 错误
     * @return 错误响应
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public JsonResult<?> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        Map<String, String> errors = new HashMap<>(violations.size());
        for (ConstraintViolation<?> item : violations) {
            try {
                PathImpl node = (PathImpl) item.getPropertyPath();
                String[] nodeStr = node.asString().split("\\.");
                String key = nodeStr[nodeStr.length - 1];
                errors.put(key, item.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                errors.put("others", item.getMessage());
            }
        }
        return ResultTool.fail(ResultCode.PARAM_NOT_VALID, JSONUtil.parse(errors).toString());
    }


    @ExceptionHandler(RuntimeException.class)
    public JsonResult<Void> runtimeExceptionHandler(RuntimeException exception) {
        log.error("未知错误：");
        exception.printStackTrace();
        return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    @ExceptionHandler(ServerException.class)
    public JsonResult<Void> serverExceptionHandler(ServerException exception) {
        return ResultTool.fail(exception.getResultCode(), exception.getMessage());
    }


    @ExceptionHandler(WithResultCodeException.class)
    public JsonResult<Void> withResultCodeExceptionHandler(WithResultCodeException e) {
        return ResultTool.fail(e.getResultCode());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public JsonResult<Void> withUnauthorizedExceptionHandler(UnauthorizedException e) {
        return ResultTool.fail(e.getResultCode());
    }


}
