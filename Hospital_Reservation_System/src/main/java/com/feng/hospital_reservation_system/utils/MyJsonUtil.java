package com.feng.hospital_reservation_system.utils;

import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;

public class MyJsonUtil {

    public static String getString(@NotNull Object data, @NotNull String key) {
        return jsonToString(data, key);
    }

    public static JSONObject getJSONObject(@NotNull Object data, @NotNull String key) {

        if (isNull(jsonToString(data, key)))
            return null;

        return JSONObject.parseObject(jsonToString(data, key));
    }

    public static <T> T getArrayList(@NotNull Object data, @NotNull String key, @NotNull Class<T> clazz) {

        if (isNull(jsonToString(data, key)))
            return null;

        return (T) JSONObject.parseArray(jsonToString(data, key), clazz);

    }

    public static <T> T getJavaType(@NotNull Object data, @NotNull String key, @NotNull Class<T> clazz) {

        if (isNull(jsonToString(data, key)))
            return null;

        return JSONObject.parseObject(jsonToString(data, key)).toJavaObject(clazz);
    }


    private static String jsonToString(@NotNull Object data, @NotNull String key) {
        if (data == null) {
            return null;
        }

        String[] keyArray = key.split("\\.");

        String jsonString = data.toString();

        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        String string = "";

        String current = keyArray[0];   //记录当前的key

        Object object = jsonObject.get(current);  //记录JSON对象当前key的value值

        if (object == null) {
            return null;
        }

        string = object.toString();  //将当前的value值转换成字符串类型

        for (int i = 1; i < keyArray.length; i++) {

            if (i == keyArray.length) { //解析到最后一个key停止循环
                break;
            }

            jsonObject = JSONObject.parseObject(string);  //将当前的value转换成JSONObject类型
            object = jsonObject.get(keyArray[i]);

            if (object != null) {
                string = object.toString();

            } else {
                return null;
            }
        }

        return string;
    }

    private static boolean isNull(Object object) {
        if (object == null)
            return true;
        return false;
    }

}
