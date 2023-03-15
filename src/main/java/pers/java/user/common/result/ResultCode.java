package pers.java.user.common.result;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Agitator
 * @Description 封装响应码和响应信息
 */
public enum ResultCode {
    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000~1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码或验证码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    USER_ADDRESS_NOT_FOUND(2010, "用户地址为找到"),

    USER_WX_LOGIN_FAIL(2021, "用户微信登录失败"),
    USER_PHONE_ALREADY_EXIST(2022, "手机号码已绑定其他账号"),


    /* 权限相关 */
    NO_PERMISSION(3001, "没有权限"),
    USER_TOKEN_EXPIRES(3002, "token权限认证过期，请重新获取"),
    USER_TOKEN_ILLEGAL(3003, "token不合法"),
    USER_REFRESH_TOKEN_EXPIRES(3004, "refresh_token权限认证过期，请重新获取"),
    USER_REFRESH_TOKEN_ILLEGAL(3005, "refresh_token不合法"),

    ;


    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Map<Integer, String> toMap() {
        return Arrays.stream(ResultCode.values()).collect(Collectors.toMap(ResultCode::getCode, ResultCode::getMessage));
    }


    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    /**
     * 通过code获取message
     *
     * @param code 需要查询的code
     * @return code对应的message
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode resultCode : values()) {
            if (resultCode.getCode().equals(code)) {
                return resultCode.getMessage();
            }
        }
        return null;
    }


}
