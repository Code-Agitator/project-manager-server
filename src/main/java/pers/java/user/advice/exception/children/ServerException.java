package pers.java.user.advice.exception.children;


import pers.java.user.advice.exception.WithResultCodeException;
import pers.java.user.common.result.ResultCode;

/**
 * 通用异常处理
 */
public class ServerException extends WithResultCodeException {

    public ServerException(String message) {
        super(ResultCode.COMMON_FAIL, message);
    }

    public ServerException() {
        super(ResultCode.COMMON_FAIL);
    }

    public ServerException(ResultCode resultCode) {
        super(resultCode);
    }

    public ServerException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }
}
