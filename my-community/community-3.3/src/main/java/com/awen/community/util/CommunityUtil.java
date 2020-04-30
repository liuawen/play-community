package com.awen.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-26 10:49
 */
public class CommunityUtil {
    // 生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    // 加密
    // hello  ->
    // hello + salt ->
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
    //可能没有  code msg  map  重载写
    // code  msg   map  封装JSON对象
    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        //com.alibaba.json

        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        //key value
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "法外狂徒张三");
        map.put("age", 15);
        System.out.println(getJSONString(0, "ok", map));
        //{"msg":"ok","code":0,"name":"法外狂徒张三","age":15}
    }
}
