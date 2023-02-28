package pers.llz.user.advice.exception.children;

import pers.llz.user.advice.exception.WithResultCodeException;
import pers.llz.user.common.result.ResultCode;

/**
 * @author Agitator
 */
public class UnauthorizedException extends WithResultCodeException {
    public UnauthorizedException(ResultCode resultCode) {
        super(resultCode);
    }

    public UnauthorizedException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }
}
