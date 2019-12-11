package com.example.toutiao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019/12/11 21:33
 */
@Controller
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


        return new ModelAndView("index", "user", user);
    }

    @RequestMapping(value = "/profile/{groupId}/{userId}")
    @ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "wf") String key) {
        return String.format("GID:{%s}, UID:{%d}, TYPE:{%d}, KEY:{%s}", groupId, userId, type, key);
    }

    class User implements Serializable {
        String name;
        int age;
        User friend;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public User getFriend() {
            return friend;
        }

        public void setFriend(User friend) {
            this.friend = friend;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}