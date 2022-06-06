package kr.mmgg.scp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAop {
    @Around("execution(* *..controller.*.*(..))")
    public Object printLog(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        return result;
    }
}
