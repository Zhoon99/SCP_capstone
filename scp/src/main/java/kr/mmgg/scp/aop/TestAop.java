package kr.mmgg.scp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class TestAop {
    @Around("execution(* *..controller.*.*(..))")
    public Object printLog(ProceedingJoinPoint pjp) throws Throwable {
        for (Object i : pjp.getArgs()) {
            log.info("getArgs : " + i.toString());
        }
        Object result = pjp.proceed();
        log.info("================================After=============================");
        return result;
    }
}
