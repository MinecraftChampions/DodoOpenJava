package io.github.minecraftchampions.dodoopenjava.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * һЩ���õķ���
 * @author qscbm187531
 */
public class BaseUtil {
    /**
     * ƴ�� Authorization
     *
     * @param clientId ������Ψһ��ʾ
     * @param token �����˼�ȨToken
     * @return ����ƴ�Ӻ���ı�
     */
    public static String Authorization(String clientId, String token) {
        return "Bot " + clientId + "." + token;
    }

    /**
     * �жϷ����������
     * @param status ������
     * @return ��˼
     */
    public static String getStatus(int status) throws IOException {
        return new JSONObject(NetUtil.simulationBrowserRequest("https://mcchampions.github.io/status.json")).getString(String.valueOf(status));
    }

    /**
     * Object����תString����
     * @param list Object����
     * @return String����
     */
    public static List<String> toStringList(List<Object> list) {
        List<String> returnList = new ArrayList<>();
        for (Object o : list) {
            returnList.add(o.toString());
        }
        return returnList;
    }

    /**
     * �ж�һ���ı����Ƿ������дʣ��ʿ�URL��<a href="https://mcchampions.github.io/database.json">�ʿ��ַ</a>)
     * @param text �ı�
     * @return true������
     */
    public static boolean hasSensitiveWord(String text) throws IOException {
        //��ȡ�ʿ�����
        List<String> list = toStringList((new JSONObject(NetUtil.simulationBrowserRequest("https://mcchampions.github.io/database.json"))).getJSONArray("words").toList());
        boolean isSensitiveWord = false;
        for (String word : list) {
            if (text.contains(word)) {
                isSensitiveWord = true;
                break;
            }
        }
        return isSensitiveWord;
    }
}