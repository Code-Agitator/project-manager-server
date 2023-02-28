package pers.llz.user.advice.exception.children;

import pers.llz.user.advice.exception.WithResultCodeException;
import pers.llz.user.common.result.ResultCode;

/**
 * @author Agitator
 */
public class RefuseMessageException extends WithResultCodeException {
    public RefuseMessageException(ResultCode resultCode) {
        super(resultCode);
    }
}

