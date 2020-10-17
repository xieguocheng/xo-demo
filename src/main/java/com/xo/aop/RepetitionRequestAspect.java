package com.xo.aop;

import com.xo.annotation.CannotRepetitionRequest;
import com.xo.service.RedisService;
import com.xo.utils.IpUtils;
import com.xo.utils.api.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Jason
 * @description 防重复提交切面
 * @date 2019-09-18 11:49
 */
@Aspect
@Component
public class RepetitionRequestAspect {

    @Resource
    private RedisService redisService;


    //参考：https://www.cnblogs.com/manliu/articles/5983888.html
    @Around("@annotation(com.xo.annotation.CannotRepetitionRequest)")
    public Object handler(ProceedingJoinPoint point) throws Throwable {
        String key = getKey();

        if (!StringUtils.isEmpty(redisService.get(key, RedisService.REPETITION_DB))) {
            return CommonResult.requestError("操作过于频繁");
        }

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        int timeout = method.getAnnotation(CannotRepetitionRequest.class).timeout();

        if (!StringUtils.isEmpty(redisService.get(key, RedisService.REPETITION_DB))) {
            return CommonResult.requestError("操作过于频繁");
        }

        redisService.set(key, "0", timeout, RedisService.REPETITION_DB);

        return point.proceed();
    }

    private String getKey() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        try {
            Long currentUserId = 1L;//TODO
            return String.format("%d_%d", currentUserId, request.getRequestURI().hashCode());
        } catch (Exception e) {
            return String.format("%s_%d", IpUtils.getIp(request), request.getRequestURI().hashCode());
        }

    }

}
