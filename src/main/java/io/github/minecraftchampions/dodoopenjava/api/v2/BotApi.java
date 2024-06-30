package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.util.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.util.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 机器人API
 *
 * @author qscbm187531
 */
public class BotApi {

    /**
     * 获取机器人信息
     *
     * @param clientId 机器人唯一标识
     * @param token    机器人鉴权Token
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getBotInfo(String clientId, String token) throws IOException {
        return getBotInfo(BaseUtil.generateAuthorization(clientId, token));
    }

    /**
     * 获取机器人信息
     *
     * @param authorization authorization
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */

    public static Result getBotInfo(String authorization) throws IOException {
        String url = DodoOpenJava.BASEURL + "bot/info";
        JSONObject jsonObject = new JSONObject();
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result setBotIslandLeave(String clientId, String token, String islandSourceId) throws IOException {
        return setBotIslandLeave(BaseUtil.generateAuthorization(clientId, token), islandSourceId);
    }

    /**
     * 机器人退群
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */

    public static Result setBotIslandLeave(String authorization, String islandSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "bot/island/leave";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取机器人邀请列表
     *
     * @param clientId 机器人唯一标识
     * @param token    机器人鉴权Token
     * @param pageSize 页大小，最大100
     * @param maxId    上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */

    public static Result getBotInviteList(String clientId, String token, int pageSize, long maxId) throws IOException {
        return getBotInviteList(BaseUtil.generateAuthorization(clientId, token), pageSize, maxId);
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

    public static Result getBotInviteList(String authorization, int pageSize, long maxId) throws IOException {
        String url = DodoOpenJava.BASEURL + "bot/invite/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageSize", pageSize)
                .put("maxId", maxId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 添加成员到机器人邀请列表
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param dodoSourceId dodoSourceId
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */

    public static Result addBotInvite(String clientId, String token, String dodoSourceId) throws IOException {
        return addBotInvite(BaseUtil.generateAuthorization(clientId, token), dodoSourceId);
    }

    /**
     * 添加成员到机器人邀请列表
     *
     * @param authorization authorization
     * @param dodoSourceId  dodoSourceId
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */

    public static Result addBotInvite(String authorization, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "bot/invite/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 移除成员出机器人邀请列表
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param dodoSourceId dodoSourceId
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */

    public static Result removeBotInvite(String clientId, String token, String dodoSourceId) throws IOException {
        return removeBotInvite(BaseUtil.generateAuthorization(clientId, token), dodoSourceId);
    }

    /**
     * 移除成员出机器人邀请列表
     *
     * @param authorization authorization
     * @param dodoSourceId  dodoSourceId
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */

    public static Result removeBotInvite(String authorization, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "bot/invite/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }
}
