package pro.risingsun.push.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 23:45
 * @description
 */
@Slf4j
@Data
@AllArgsConstructor
public class UserException extends RuntimeException{

    public static final Integer USER_NO_EXIST = 43000;//用户不存在

    public Integer code;

    public String message;
}
