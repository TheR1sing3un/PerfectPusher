package pro.risingsun.push.userservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.risingsun.push.exception.TokenException;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.userservice.exception.UserException;
import pro.risingsun.push.userservice.service.UserService;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 15:42
 * @description
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {
    /**
     * 拦截异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResult error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return CommonResult.error().msg("系统异常");
    }

    /**
     * 拦截token异常
     * @param userException
     * @return
     */
    @ExceptionHandler(UserException.class)
    public CommonResult error(UserException userException){
        log.info(userException.message);
        return CommonResult.error().code(userException.code).msg(userException.message);
    }
}
