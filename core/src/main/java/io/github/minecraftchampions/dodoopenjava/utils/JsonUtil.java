package io.github.minecraftchampions.dodoopenjava.utils;

import org.json.JSONObject;

import java.util.Map;

/**
 * �й��� JSON �����ʵ���Է���
 * @author qscbm187531
 */
public class JsonUtil {
    /**
     * ת���ַ���Ϊjson
     *
     * @param string �ַ���
     * @return JSONObject
     */
    public static JSONObject parseJson(String string) {
        return new JSONObject(string);
    }


    /**
     * jsonת��Ϊ�ַ���
     *
     * @param obj obj
     * @return json
     */
    public static String toString(JSONObject obj) {
        return obj.toString();
    }

    /**
     * ��jsonװ��Ϊmap
     * @param jsonObject json
     * @return map
     */
    public static Map<String,Object> toMap(JSONObject jsonObject) {
        return jsonObject.toMap();
    }
}
