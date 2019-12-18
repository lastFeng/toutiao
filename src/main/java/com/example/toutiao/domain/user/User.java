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
    private String name;
    private String password;
    private String salt;
    private String headUrl;
}
