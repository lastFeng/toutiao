package com.example.toutiao.utils.json;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/23 22:18
 * @description:
 */
public class JsonMapper {
    private JSONObject jsonObject;

    private JsonMapper(){
    }

    private static JsonMapper newInstance() {
        return new JsonMapper();
    }

    public static JsonMapper INSTANCE = JsonMapper.newInstance();

    public String getJsonString(int code) {
        jsonObject = new JSONObject();
        jsonObject.put("code", code);
        return jsonObject.toJSONString();
    }

    public String getJsonString(int code, Map<String, Object> map) {
        jsonObject = new JSONObject();
        jsonObject.put("code", code);

        for (Map.Entry<String, Object> entry: map.entrySet()) {
            jsonObject.put(entry.getKey(), entry.getValue());
        }
        return jsonObject.toJSONString();
    }

    public String getJsonString(int code, String msg) {
        jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);

        return jsonObject.toJSONString();
    }
}
