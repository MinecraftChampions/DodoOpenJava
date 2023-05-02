package io.github.minecraftchampions.dodoopenjava.utils;

import org.json.JSONObject;
import org.json.XML;

/**
 * ���� XML �ļ���һЩ���ʵ�÷���
 * @author qscbm187531
 */
public class XmlUtil {
    /**
     * ת��ΪJSON�ַ���
     * @param xml xml�ַ���
     * @return �ַ���
     */
    public static String toJSONString(String xml) {
        return XML.toJSONObject(xml).toString();
    }

    /**
     * ת��ΪJSON����
     * @param xml xml�ַ���
     * @return JSONObject
     */
    public static JSONObject toJSONObject(String xml) {
        return new JSONObject(XML.toJSONObject(xml));
    }
}
