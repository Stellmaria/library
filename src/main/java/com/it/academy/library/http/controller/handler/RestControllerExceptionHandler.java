package com.it.academy.library.http.controller.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.it.academy.library.http.controller.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
