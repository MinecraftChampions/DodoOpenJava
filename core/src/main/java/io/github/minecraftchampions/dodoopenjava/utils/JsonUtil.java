package io.github.minecraftchampions.dodoopenjava.utils;

import org.json.JSONObject;

import java.util.Map;

/**
 * 有关于 JSON 的相关实用性方法
 * @author qscbm187531
 */
public class JsonUtil {
    /**
     * 转化字符串为json
     *
     * @param string 字符串
     * @return JSONObject
     */
    public static JSONObject parseJson(String string) {
        return new JSONObject(string);
    }


    /**
     * json转化为字符串
     *
     * @param obj obj
     * @return json
     */
    public static String toString(JSONObject obj) {
        return obj.toString();
    }

    /**
     * 将json装换为map
     * @param jsonObject json
     * @return map
     */
    public static Map<String,Object> toMap(JSONObject jsonObject) {
        return jsonObject.toMap();
    }
}
