package com.example.toutiao.mysqltest;

import com.example.toutiao.domain.user.User;
import com.example.toutiao.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/18 21:22
 * @description:
 */
@SpringBootTest
@Rollback
public class MybatisTest {

    @Autowired
    private UserService userService;

    @Test
    public void userTableTest() {
        for (int i = 0; i < 11; i++) {
            User user = new User();
            user.setHeadUrl("http://www.baidu.com");
            user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setName("Test" + i);
            user.setPassword("Test" + i);
            user.setSalt("Test" + i);

            userService.saveOrUpdate(user);
        }
    }
}
