package com.it.academy.library.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Slf4j
public class FirstAspect {
    /**
     * execution(modifiers-pattern? ret-type-pattern
     * declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
     */
    @Pointcut("execution(public * com.it.academy.library.service..*ServiceImpl.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before(
            value = "anyFindByIdServiceMethod()" +
                    " && args(id)" +
                    " && target(service)" +
                    " && this(serviceProxy)" +
                    " && @within(transactional)",
            argNames = "joinPoint,id,service,serviceProxy,transactional"
    )
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("Before - invoke findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(
            value = "anyFindByIdServiceMethod()" +
                    " && target(service)",
            returning = "result",
            argNames = "result,service"
    )
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("After returning - invoke findById method in class {}, result{}", service, result);
    }

    @AfterThrowing(
            value = "anyFindByIdServiceMethod()" +
                    " && target(service)",
            throwing = "exception"
    )
    public void addLoggingAfterThrowing(@NotNull Throwable exception, Object service) {
        log.info(
                "After throwing - invoke findById method in class {}, exception{}: {}", service, exception.getCause(),
                exception.getMessage()
        );
    }

    @After("anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("After (finally) - invoke findById method in class {}", service);
    }
}
