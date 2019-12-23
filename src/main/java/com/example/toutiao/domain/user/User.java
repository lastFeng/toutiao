package com.example.toutiao.domain.user;

import com.example.toutiao.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/18 21:16
 * @description:
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户密码加密所使用的的盐
     */
    private String salt;
    /**
     * 用户头像的地址
     */
    private String headUrl;
}
