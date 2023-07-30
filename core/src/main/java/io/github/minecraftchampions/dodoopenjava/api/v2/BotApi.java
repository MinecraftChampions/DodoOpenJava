package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 机器人API
 */
public class BotApi {
    public static String url, param;

    /**
     * 获取机器人信息
     *
     * @param clientId 机器人唯一标识
     * @param token    机器人鉴权Token
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject getBotInfo(String clientId, String token) throws IOException {
        return getBotInfo(BaseUtil.Authorization(clientId, token));
    }

    /**
     * 获取机器人信息
     *
     * @param authorization authorization
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject getBotInfo(String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/info";
        param = "{}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 机器人退群
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject setBotIslandLeave(String clientId, String token, String islandSourceId) throws IOException {
        return setBotIslandLeave(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * 机器人退群
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject setBotIslandLeave(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/island/leave";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取机器人邀请列表
     *
     * @param authorization authorization
     * @param pageSize      页大小，最大100
     * @param maxId         上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject getBotInviteList(String authorization, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/invite/list";
        param = "{" +
                "    \"pageSize\":" + pageSize + "," +
                "    \"maxId\":" + maxId + "" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 添加成员到机器人邀请列表
     *
     * @param authorization authorization
     * @param dodoSourceId  dodoSourceId
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject addBotInvite(String authorization, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/invite/add";
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 移除成员出机器人邀请列表
     *
     * @param authorization authorization
     * @param dodoSourceId  dodoSourceId
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject removeBotInvite(String authorization, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/invite/remove";
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
