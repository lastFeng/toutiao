package com.example.toutiao.config.interceptor;

import com.example.toutiao.domain.user.User;
import com.example.toutiao.mapper.user.UserMapper;
import com.example.toutiao.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/28 17:37
 * @description:
 */
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 什么情况下，激活该拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = null;

        if (request.getCookies() != null) {
            for (Cookie cookie: request.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket != null) {
            // 查询数据库，看看有没有对应数据，并没有过期
            // if xxx
            // 无效
            // return true;

            // 有效
            // 将cookie保存起来，使用线程
            ThreadLocal<User> user = new ThreadLocal<>();
            user.set(new User());
            hostHolder.setUsers(user);
        }
        return true;
    }

    /**
     * 拦截之后，将内容与前端进行渲染
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && hostHolder.getUsers() != null) {
            modelAndView.addObject("user", hostHolder.getUsers());
        }
    }

    /**
     * 结束之后路的操作，将数据清除
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clearUsers();
    }
}
