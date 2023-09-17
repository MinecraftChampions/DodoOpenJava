package io.github.minecraftchampions.dodoopenjava.utils;

import org.json.JSONObject;
import org.json.XML;

/**
 * 关于 XML 文件的一些相关实用方法
 */
public class XmlUtil {
    /**
     * 转换为JSON字符串
     *
     * @param xml xml字符串
     * @return 字符串
     */
    public static String toJSONString(String xml) {
        return XML.toJSONObject(xml).toString();
    }

    /**
     * 转换为JSON对象
     *
     * @param xml xml字符串
     * @return JSONObject
     */
    public static JSONObject toJSONObject(String xml) {
        return XML.toJSONObject(xml);
    }

    /**
     * json转换为Toml
     *
     * @param jsonObject json
     * @return toml
     */
    public static String jsonToXml(JSONObject jsonObject) {
        return XML.toString(jsonObject);
    }
}