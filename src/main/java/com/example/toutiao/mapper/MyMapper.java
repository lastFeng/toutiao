package com.example.toutiao.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description: mapper的基础实现类，其中T代表需要的domain类
 * @data created in 2019/12/11 21:51
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
