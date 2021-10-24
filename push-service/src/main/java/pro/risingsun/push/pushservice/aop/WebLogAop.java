package pro.risingsun.push.pushservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 16:45
 * @description
 */
@Aspect
@Component
@Slf4j
public class WebLogAop {

    /**
     * 切点为controller包下的所有类所有方法
     */
    @Pointcut("execution(* pro.risingsun.push.pushservice.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //接收到请求,获取请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求内容
        log.info("Url : {}",request.getRequestURL());
        log.info("Http_Method : {}",request.getMethod());
        log.info("Ip : {}",request.getRemoteAddr());
        //记录请求类方法
        log.info("Class_Method : {}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("Args : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "resp",pointcut = "webLog()")
    public void doAfterReturning(Object resp){
        log.info("Response : {}",resp);
    }

}
