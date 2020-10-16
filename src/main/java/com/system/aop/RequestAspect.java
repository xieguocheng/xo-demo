package com.system.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jason
 * @description 打印请求参数
 * @date 2019-09-16
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Pointcut("execution(public * com.*.controller..*.*(..))")
    public void requestLog() {
    }

    @Before("requestLog()")
    public void logRequestParams(JoinPoint point) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        log.debug("-----------------------------------------------------------");
        log.debug("request url: {}", request.getRequestURL());
        log.debug("request uri: {}", request.getRequestURI());
        log.debug("request method: {}", request.getMethod());
        log.debug("request params: {}", point.getArgs());
        log.debug("request query string: {}", request.getQueryString());
    }

//    @AfterThrowing(value = "requestLog()", throwing = "e")
//    public void logRequestException(JoinPoint point, Exception e) {
//
//        if (e instanceof MessageException || e instanceof FrontMessageException) {
//            return;
//        }
//
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        assert requestAttributes != null;
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        log.error("-----------------------------------------------------------");
//        log.error("request url: {}", request.getRequestURL());
//        log.error("request uri: {}", request.getRequestURI());
//        log.error("request method: {}", request.getMethod());
//        log.error("request params: {}", point.getArgs());
//        log.error("request query string: {}", request.getQueryString());
//        log.error("request error: {}", e.toString());
//    }

}
