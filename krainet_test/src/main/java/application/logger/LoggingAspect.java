package application.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static application.utils.Constant.*;
import static application.logger.LoggerPrinter.logPrint;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Around("execution(* application.service.impl..*(..))")
    public Object logServicesMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info(logPrint(String.format(LOG_PATTERN, START_METHOD, joinPoint.getSignature().getName(),
                FROM, joinPoint.getSignature().getDeclaringType())));
        Object result = joinPoint.proceed();
        log.info(logPrint(String.format(LOG_PATTERN, END_METHOD, joinPoint.getSignature().getName(),
                FROM, joinPoint.getSignature().getDeclaringType())));
        return result;
    }
}
