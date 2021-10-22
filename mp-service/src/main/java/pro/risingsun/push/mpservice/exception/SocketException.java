package pro.risingsun.push.mpservice.exception;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 16:55
 * @description
 */

public class SocketException extends RuntimeException {
    public static final Integer SOCKET_CLOSED = 42000;//通道关闭

    public Integer code;

    public String message;

    public SocketException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
