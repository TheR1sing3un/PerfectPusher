package pro.risingsun.push.cpservice.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.risingsun.push.model.CommonResult;

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

    @ExceptionHandler({WxErrorException.class})
    public void error(WxErrorException e){
        e.printStackTrace();
        log.error(e.getMessage());
    }

}
