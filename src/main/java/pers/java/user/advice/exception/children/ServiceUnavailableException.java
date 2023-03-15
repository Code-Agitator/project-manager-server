package pers.java.user.advice.exception.children;


import pers.java.user.advice.exception.WithResultCodeException;
import pers.java.user.common.result.ResultCode;

/**
 * @author Agitator
 */
public class ServiceUnavailableException extends WithResultCodeException {
    public ServiceUnavailableException(ResultCode resultCode) {
        super(resultCode);
    }
}
