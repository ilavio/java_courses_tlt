package com.potemkin.i;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LoggingAOP {
    
    @Pointcut("@annotation(MyLogging)")
    public void beforeEntityMethods() {
    }
    
    @Before("beforeEntityMethods()")
    public void logMethodCall(JoinPoint jp) {
        var strBuf = new StringBuffer();
        strBuf.append("Method: ");
        strBuf.append(jp.getSignature().getName()).append(" arg's :");
        for(Object arg : jp.getArgs()) {
            strBuf.append(arg);
            strBuf.append("; ");
        }
        log.info("{}", strBuf);
    }
}
