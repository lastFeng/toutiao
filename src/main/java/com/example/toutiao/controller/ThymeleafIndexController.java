package com.example.toutiao.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Enumeration;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019/12/11 21:33
 */
//@Controller
public class ThymeleafIndexController {

    /**
     * thymeleaf示例
     * @param
     * @return
     */
    @RequestMapping(path = {"/", "/index"})
    public ModelAndView index() {
        User user = new User();
        user.setAge(21);
        user.setName("郭炜锋");
        user.setFriend(new User("李小龙", 30));


        return new ModelAndView("thymeleafindex", "user", user);
    }

    @RequestMapping(value = "/profile/{groupId}/{userId}")
    @ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "wf") String key) {
        return String.format("GID:{%s}, UID:{%d}, TYPE:{%d}, KEY:{%s}", groupId, userId, type, key);
    }

    /**
     * 请求内容的获取
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/request")
    @ResponseBody
    public String request(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) {
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        stringBuilder.append("Headers: <br>");
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            stringBuilder.append(name + ":" + request.getHeader(name) + "<br>");
        }

        stringBuilder.append("Cookie: <br>");
        for (Cookie cookie: request.getCookies()) {
            stringBuilder.append("Cookie:" + cookie.getName() + ":" + cookie.getValue() + "<br>");
        }

        return stringBuilder.toString();
    }

    /**
     * 设置响应的Cookie与Header
     * @param cookie
     * @param key
     * @param value
     * @param response
     * @return
     */
    @RequestMapping("/response")
    @ResponseBody
    public String response(@CookieValue(value = "Cookie", defaultValue = "123") String cookie,
                           @RequestParam(value = "key", defaultValue = "Cookie") String key,
                           @RequestParam(value = "value", defaultValue = "value") String value,
                           HttpServletResponse response) {
        response.addCookie(new Cookie(key, value));
        response.addHeader(key, value);
        return "guowf From Cookie: " + cookie;
    }

    /**
     * 跳转：
     *   301： 永久--获取本地缓存
     *   302： 临时--从服务器中获取
     * @param code
     * @return
     */
    @RequestMapping("/redirect/{code}")
    public RedirectView redirectView(@PathVariable("code") int code) {
        RedirectView view = new RedirectView("/", true);
        if (code == 301) {
            view.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }

        return view;
    }

    /**
     * 302跳转
     */
    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:/";
    }

    @Data
    class User implements Serializable {
        private static final long serialVersionUID = 7313283055058322532L;
        String name;
        int age;
        User friend;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}