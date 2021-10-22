package pro.risingsun.push.exception;



public class TokenException extends RuntimeException{

    public static final Integer NO_TOKEN = 41000;//token不存在
    public static final Integer TOKEN_EXPIRED = 41100;//token过期
    public static final Integer TOKEN_VERIFY_ERROR = 51200;//token解析出错
    public static final Integer TOKEN_PARSER_ERROR = 51300;//token获取数据异常

    public Integer code;

    public String message;

    public TokenException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
