package io.github.mcchampions.DodoOpenJava.Utils;

import com.alibaba.fastjson2.JSONObject;
import org.json.XML;

/**
 * 关于 XML 文件的一些相关实用方法
 */
public class XmlUtil {
    /**
     * 转换为JSON字符串
     * @param xml xml字符串
     * @return 字符串
     */
    public static String toJSONString(String xml) {
        return XML.toJSONObject(xml).toString();
    }

    /**
     * 转换为JSON对象
     * @param xml xml字符串
     * @return JSONObject
     */
    public static JSONObject toJSONObject(String xml) {
        return JSONObject.parseObject(XML.toJSONObject(xml).toString());
    }
}
