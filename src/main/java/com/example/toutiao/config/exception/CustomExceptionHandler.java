package com.example.toutiao.config.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019/12/12 22:25
 * 自定义异常句柄，但是好像没有用
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * 统一运行时异常处理
     * @param e
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String customRuntimeException(RuntimeException e) {
        return "RuntimeException: " + e.getMessage();
    }

    /**
     * 统一检查异常处理
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String customExceptionHandler(Exception e) {
        return "Error: " + e.getMessage();
    }
}
