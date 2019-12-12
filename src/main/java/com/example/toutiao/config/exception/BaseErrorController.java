package com.example.toutiao.config.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @date created in 2019/12/12 22:34
 * 查看错误页面，都是默认映射到/error,那么修改此映射即可，但是要继承ErrorController类，重写相应方法
 * 或：在templates/error/下定义：如添加5xx页面，4xx页面
 */
@RestController
@RequestMapping("/error")
public class BaseErrorController implements ErrorController{
    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping
    public String error() {
        return "Custom Error Page!!!";
    }
}
