package com.it.academy.library.http.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.it.academy.library.http.controller")
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception exception) {
        log.error("Exception ", exception);

        return "error/error500";
    }
}
