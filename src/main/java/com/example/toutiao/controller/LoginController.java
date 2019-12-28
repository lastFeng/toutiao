package com.example.toutiao.controller;

import com.example.toutiao.service.user.UserService;
import com.example.toutiao.utils.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/23 21:34
 * @description:
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    JsonMapper jsonMapper = JsonMapper.INSTANCE;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/reg/", method = RequestMethod.POST)
    public String index(ModelAndView modelAndView, @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(name = "remember", defaultValue = "false") Boolean remember) {
        try {
            Map<String, Object> map = userService.register(username, password);
            if (map.isEmpty()) {
                return jsonMapper.getJsonString(0, "注册成功");
            }
            return jsonMapper.getJsonString(1, map);
        } catch (NoSuchAlgorithmException e) {
            logger.error("注册出错", e.getMessage());
            return jsonMapper.getJsonString(1, "注册异常");
        }
    }

    @RequestMapping(path = {"/logout"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/";
    }
}
