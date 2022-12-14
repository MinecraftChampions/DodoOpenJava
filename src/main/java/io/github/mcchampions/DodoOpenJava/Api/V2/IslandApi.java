package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 群API
 * @author qscbm187531
 */
public class IslandApi {
    public static String url, param;

    /**
     * 获取群信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandInfo(String clientId, String token, String islandSourceId) throws IOException {
        return getIslandInfo(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * 获取群信息
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandInfo(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/island/info";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取机器人群列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandList(String clientId, String token) throws IOException {
        return getIslandList(BaseUtil.Authorization(clientId,token));
    }

    /**
     * 获取机器人群列表
     *
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandList(String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/island/list";
        param = "{}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取群等级排行榜
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandLevelRankList(String clientId, String token, String islandSourceId, int pageSize, long maxId) throws IOException {
        return getIslandLevelRankList(BaseUtil.Authorization(clientId, token), islandSourceId, pageSize, maxId);
    }

    /**
     * 获取群等级排行榜
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandLevelRankList(String authorization, String islandSourceId, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/island/info";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"pageSize\": \"" + pageSize + "\"," +
                "    \"maxId\": \"" + maxId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取禁言列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandMuteList(String clientId, String token, String islandSourceId) throws IOException {
        return getIslandMuteList(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * 获取禁言列表
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandMuteList(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/island/mute/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取封禁列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandBanList(String clientId, String token, String islandSourceId) throws IOException {
        return getIslandBanList(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * 获取封禁列表
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIslandBanList(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/island/ban/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
