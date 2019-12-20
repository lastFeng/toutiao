package com.example.toutiao.mapper.user;

import com.example.toutiao.common.MyMapper;
import com.example.toutiao.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/18 21:20
 * @description:
 */
@Mapper
public interface UserMapper extends MyMapper<User> {
}
