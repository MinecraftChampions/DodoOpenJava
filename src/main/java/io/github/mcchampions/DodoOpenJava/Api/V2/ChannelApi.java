package io.github.mcchampions.DodoOpenJava.Api.V2;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;

import java.io.IOException;

/**
 * 频道API
 * @author qscbm187531
 */
public class ChannelApi {
    public static String url, param;
    /**
     * 获取频道列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelList(String clientId, String token, String islandSourceId) throws IOException {
        return getChannelList(BaseUtil.Authorization(clientId,token), islandSourceId);
    }

    /**
     * 获取频道列表
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelList(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取频道信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelInfo(String clientId, String token, String channelId) throws IOException {
        return getChannelInfo(BaseUtil.Authorization(clientId, token), channelId);
    }

    /**
     * 获取频道信息
     *
     * @param authorization authorization
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelInfo(String authorization, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/info";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 创建频道
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param channelName 频道名
     * @param channelType 频道类型
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannel(String clientId, String token, String islandSourceId, String channelName, int channelType) throws IOException {
        return addChannel(BaseUtil.Authorization(clientId, token), islandSourceId, channelName, channelType);
    }

    /**
     * 创建频道
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param channelName 频道名
     * @param channelType 频道类型
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannel(String authorization, String islandSourceId, String channelName, int channelType) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/add";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"channelName\": \"" + channelName + "\"," +
                "    \"channelType\": " + channelType + "" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
    
    /**
     * 编辑频道
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param channelName 频道名
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannel(String clientId, String token, String islandSourceId, String channelName, String channelId) throws IOException {
        return editChannel(BaseUtil.Authorization(clientId, token), islandSourceId, channelName, channelId);
    }

    /**
     * 编辑频道
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param channelName 频道名
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannel(String authorization, String islandSourceId, String channelName, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/edit";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"channelName\": \"" + channelName + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
    
    /**
     * 删除频道
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandSourceId 群号
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject deleteChannel(String clientId, String token, String islandSourceId, String channelId) throws IOException {
        return deleteChannel(BaseUtil.Authorization(clientId, token), islandSourceId, channelId);
    }

    /**
     * 删除频道
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject deleteChannel(String authorization, String islandSourceId, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/remove";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"channelId\": \"" + channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
