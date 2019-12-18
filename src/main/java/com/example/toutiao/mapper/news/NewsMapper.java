package com.example.toutiao.mapper.news;

import com.example.toutiao.domain.news.News;
import com.example.toutiao.common.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/18 21:20
 * @description:
 */
@Mapper
public interface NewsMapper extends MyMapper<News> {
}
