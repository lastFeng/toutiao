package com.example.toutiao.config.interceptor;

import com.example.toutiao.domain.user.User;
import org.springframework.stereotype.Component;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/28 17:43
 * @description:
 * 将cookie存储起来的类---存储到本地线程的共享区中
 */
@Component
public class HostHolder {
    private  ThreadLocal<User> users = new ThreadLocal<>();

    public ThreadLocal<User> getUsers() {
        return users;
    }

    public void setUsers(ThreadLocal<User> users) {
        this.users = users;
    }

    public void clearUsers() {
        this.users.remove();
    }
}
