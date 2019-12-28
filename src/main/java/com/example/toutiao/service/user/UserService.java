/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.toutiao.service.user;

import com.example.toutiao.domain.user.User;
import com.example.toutiao.mapper.user.UserMapper;
import com.example.toutiao.service.BaseService;
import com.example.toutiao.utils.security.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2019/12/20 13:38
 */
@Service
public class UserService extends BaseService<UserMapper, User> {

    /**
     * 用户注册
     * @param username
     * @param password
     */
    public Map<String, Object> register(String username, String password) throws NoSuchAlgorithmException {
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            result.put("msgname", "用户名不能为空");
            return result;
        }

        if (StringUtils.isBlank(password)) {
            result.put("msgpwd", "密码不能为空");
            return result;
        }

        User user = this.mapper.selectUserByName(username);

        if (user != null) {
            result.put("msgname", "用户名已被注册");
            return result;
        }

        user = new User();
        user.setName(username);
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        String salt = UUID.randomUUID().toString().substring(0, 5);
        user.setSalt(salt);
        user.setPassword(Md5Util.get32BitMd5(password + salt));
        this.mapper.insert(user);

        return result;
    }

    /**
     * 用户登录
     * @return
     */
    public Map<String, Object> login(String username, String password) throws NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isBlank(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msgpwd", "密码不能为空");
            return map;
        }

        User user = this.mapper.selectUserByName(username);

        if (user == null) {
            map.put("msgname", "用户名不存在");
            return map;
        }

        if (!Md5Util.get32BitMd5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msgpwd", "密码不正确");
            return map;
        }

        // 这里肯定是获取用户登录的信息等内容

        return map;
    }

    /**
     * 用户登出
     * @param ticket
     */
    public void logout(String ticket) {
        // 将相应用户登录的信息设置即可
    }
}