package com.example.toutiao.domain.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/19 21:48
 * @description:
 */
public class ViewObject {
    private Map<String, Object> views = new HashMap<>();

    /**
     * 设置展示对象
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        views.put(key, value);
    }

    /**
     * 获取key对象，如果key不存在，返回null
     * @param key
     * @return
     */
    public Object get(String key) {
        if (views.containsKey(key)) {
            return views.get(key);
        }
        return null;
    }
}
