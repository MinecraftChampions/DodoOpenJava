package io.github.mcchampions.DodoOpenJava.Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 一些常用的方法
 * @author qscbm187531
 */
public class BaseUtil {
    /**
     * 拼接 Authorization
     *
     * @param clientId 机器人唯一标示
     * @param token    机器人鉴权Token
     * @return 返回拼接后的文本
     */
    public static String Authorization(String clientId, String token) {
        return "Bot " + clientId + "." + token;
    }

    /**
     * 判断返回码的意义
     * @param stat 返回码
     * @return 意思
     */
    public static String getStatus(String stat) throws IOException {
        return new JSONObject(NetUtil.simulationBrowserRequest("https://mcchampions.github.io/status.json")).getString(stat);
    }

    /**
     * Object集合转String集合
     * @param list Object集合
     * @return String集合
     */
    public static List<String> toStringList(List<Object> list) {
        List<String> returnList = new ArrayList<>();
        for (Object o : list) {
            returnList.add(o.toString());
        }
        return returnList;
    }

    /**
     * 判断一个文本中是否含有敏感词（词库URL：<a href="https://mcchampions.github.io/database.json">词库地址</a>)
     * @param text 文本
     * @return true代表是
     */
    public static boolean hasSensitiveWord(String text) throws IOException {
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