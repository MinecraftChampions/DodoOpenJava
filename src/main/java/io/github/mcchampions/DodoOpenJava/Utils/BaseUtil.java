package io.github.mcchampions.DodoOpenJava.Utils;

/**
 * 一些常用的方法
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
}