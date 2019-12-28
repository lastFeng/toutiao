package com.example.toutiao.config.web.webconfig;

import com.example.toutiao.config.interceptor.PassportInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/28 17:54
 * @description:
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PassportInterceptor());
    }
}
