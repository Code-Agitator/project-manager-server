package pers.java.user.advice.exception.children;

import pers.java.user.advice.exception.WithResultCodeException;
import pers.java.user.common.result.ResultCode;

/**
 * @author Agitator
 */
public class BadRequestException extends WithResultCodeException {
    public BadRequestException(ResultCode resultCode) {
        super(resultCode);
    }
}
