package pers.llz.user.advice.exception.children;

import pers.llz.user.advice.exception.WithResultCodeException;
import pers.llz.user.common.result.ResultCode;

/**
 * @author Agitator
 */
public class BadRequestException extends WithResultCodeException {
    public BadRequestException(ResultCode resultCode) {
        super(resultCode);
    }
}
