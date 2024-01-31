package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 群API
 */
public class IslandApi {
    /**
     * 获取群信息
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandInfo(String clientId, String token, String islandSourceId) throws IOException {
        return getIslandInfo(BaseUtil.generateAuthorization(clientId, token), islandSourceId);
    }

    /**
     * 获取群信息
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandInfo(String authorization, String islandSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "island/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取机器人群列表
     *
     * @param clientId 机器人唯一标识
     * @param token    机器人鉴权Token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandList(String clientId, String token) throws IOException {
        return getIslandList(BaseUtil.generateAuthorization(clientId, token));
    }

    /**
     * 获取机器人群列表
     *
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandList(String authorization) throws IOException {
        String url = DodoOpenJava.BASEURL + "island/list";
        JSONObject jsonObject = new JSONObject();
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取群等级排行榜
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandLevelRankList(String clientId, String token, String islandSourceId, int pageSize, long maxId) throws IOException {
        return getIslandLevelRankList(BaseUtil.generateAuthorization(clientId, token), islandSourceId, pageSize, maxId);
    }

    /**
     * 获取群等级排行榜
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandLevelRankList(String authorization, String islandSourceId, int pageSize, long maxId) throws IOException {
        String url = DodoOpenJava.BASEURL + "island/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("pageSize", pageSize)
                .put("maxId", maxId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取禁言列表
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandMuteList(String clientId, String token, String islandSourceId) throws IOException {
        return getIslandMuteList(BaseUtil.generateAuthorization(clientId, token), islandSourceId);
    }

    /**
     * 获取禁言列表
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandMuteList(String authorization, String islandSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "island/mute/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取封禁列表
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandBanList(String clientId, String token, String islandSourceId) throws IOException {
        return getIslandBanList(BaseUtil.generateAuthorization(clientId, token), islandSourceId);
    }

    /**
     * 获取封禁列表
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIslandBanList(String authorization, String islandSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "island/ban/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }
}
