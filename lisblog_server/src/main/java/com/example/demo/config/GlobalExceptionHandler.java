package com.example.demo.config;

import com.example.demo.Model.HttpResponse;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @Autowired
    Logger logger;

    /**
     * 捕获shiro异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public HttpResponse handle401(ShiroException e) {
        return HttpResponse.error(401, e.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public HttpResponse handle(IllegalArgumentException e) {
        return HttpResponse.error(e.getMessage());
    }
    /**
     * @Validated 校验错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public HttpResponse handler(MethodArgumentNotValidException e) throws IOException {
        logger.error("运行时异常:-------------->",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return HttpResponse.error(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public HttpResponse handler(RuntimeException e) throws IOException {
        logger.error("运行时异常:-------------->",e);
        return HttpResponse.error(e.getMessage());
    }


}
