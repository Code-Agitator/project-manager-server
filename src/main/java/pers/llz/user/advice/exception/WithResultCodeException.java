package pers.llz.user.advice.exception;


import pers.llz.user.common.result.ResultCode;

/**
 * @author Agitator
 */
public class WithResultCodeException extends RuntimeException {
    private final ResultCode resultCode;


    public WithResultCodeException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public WithResultCodeException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
