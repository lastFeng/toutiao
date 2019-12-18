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
package com.example.toutiao.service;

import com.example.toutiao.domain.BaseEntity;
import com.example.toutiao.common.MyMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2019/12/12 14:55
 * Service 基类
 */
@Transactional(readOnly = true, rollbackFor = Exception.class)
public abstract class BaseService<M extends MyMapper<T>, T extends BaseEntity> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 持久层对象
     */
    @Autowired
    protected M mapper;

    /**
     * 单表条件查询，不支持模糊查询
     * @param id
     * @return
     */
    public T findById(String id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * 单表条件查询，不支持模糊查询
     * @param param
     * @return
     */
    public List<T> findByWhere(T param) {
        return this.mapper.select(param);
    }

    /**
     * 多表条件查询，支持模糊查询（在mapper.xml中自定义），如果需要分页，参考PageHelper
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return this.mapper.findList(entity);
    }

    /**
     * 根据参数类查询数据列表，如果需要分页，参考PageHelper
     * @param entity
     * @return
     */
    public PageInfo<T> findAllPageInfo(T entity) {
        // 设置开视页
        if (entity != null && entity.getPage() != null && entity.getRows() != null) {
            PageHelper.startPage(entity.getPage(), entity.getRows());
        }
        return new PageInfo<T>(this.mapper.findList(entity));
    }

    /**
     * 单表查询记录数
     * @param param
     * @return
     */
    public Integer queryCount(T param) {
        return this.mapper.selectCount(param);
    }

    /**
     * 单表查询一条记录
     * @param param
     * @return
     */
    public T findOne(T param) {
        return this.mapper.selectOne(param);
    }

    /**
     * 保存或更新数据
     * @param entity
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveOrUpdate(T entity) {
        // 新增
        if (entity.getId() != null && !entity.getId().isEmpty()) {
            this.mapper.updateByPrimaryKey(entity);
        } else {
            this.mapper.insert(entity);
        }
    }

    /**
     * 保存或更新数据
     * @param entity
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void  saveOrUpdateSelective(T entity) {
        // 防止出入空字符
        entity.setId(StringUtils.isEmpty(entity.getId()) ? null : entity.getId());
        if (entity.getId() != null) {
            this.mapper.updateByPrimaryKey(entity);
        } else {
            this.mapper.insert(entity);
        }
    }

    /**
     * 删除单个数据
     * @param id
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteById(String id) {
        this.mapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除单个数据
     * @param entity
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(T entity) {
        this.mapper.delete(entity);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteByIds(Class<T> clazz, List<String> values) {
        if (CollectionUtils.isEmpty(values)) {
            return;
        }

        Example example = new Example(clazz);
        example.createCriteria().andIn("id", values);

        this.mapper.deleteByExample(example);
    }
}