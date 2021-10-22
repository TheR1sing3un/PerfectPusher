package pro.risingsun.push.gatewayservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.risingsun.push.exception.TokenException;
import pro.risingsun.push.model.CommonResult;

/**
 * @author TheR1sing3un
 * @date 2021/10/21 22:05
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
     * @param tokenException
     * @return
     */
    @ExceptionHandler(TokenException.class)
    public CommonResult error(TokenException tokenException){
        log.info(tokenException.message);
        return CommonResult.error().code(tokenException.code).msg(tokenException.message);
    }
}