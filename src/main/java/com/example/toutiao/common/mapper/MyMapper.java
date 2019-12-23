package com.example.toutiao.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description: mapper的基础实现类，其中T代表需要的domain类
 * @data created in 2019/12/11 21:51
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

    // FIXME: 该接口不能被扫描，否则会报错

    /**
     * 条件查询，支持模糊查询
     * @param entity
     * @return
     */
    List<T> findList(T entity);
}
