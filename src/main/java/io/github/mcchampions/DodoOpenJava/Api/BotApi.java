package io.github.mcchampions.DodoOpenJava.Api;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
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
     * @param token 机器人鉴权Token
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject getBotInfo(String clientId, String token) throws IOException {
        return getBotInfo(BaseUtil.Authorization(clientId,token));
    }

    /**
     * 获取机器人信息
     *
     * @param Authorization Authorization
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject getBotInfo(String Authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/bot/info";
        param = "{}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 机器人退群
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject setBotIslandLeave(String clientId, String token, String islandId) throws IOException {
        return setBotIslandLeave(BaseUtil.Authorization(clientId, token), islandId);
    }

    /**
     * 机器人退群
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject setBotIslandLeave(String Authorization, String islandId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/bot/island/leave";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 获取机器人邀请列表
     *
     * @param Authorization Authorization
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject getBotInviteList(String Authorization,int pageSize,long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/bot/invite/list";
        param = "{" +
                "    \"pageSize\":" + pageSize + "," +
                "    \"maxId\":" + maxId + "" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 添加成员到机器人邀请列表
     *
     * @param Authorization Authorization
     * @param dodoId dodoId
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject addBotInvite(String Authorization,String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/bot/invite/add";
        param = "{" +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 移除成员出机器人邀请列表
     *
     * @param Authorization Authorization
     * @param dodoId dodoId
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject removeBotInvite(String Authorization,String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/bot/invite/remove";
        param = "{" +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }
}
