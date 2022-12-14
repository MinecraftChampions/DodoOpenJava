package io.github.mcchampions.DodoOpenJava.Api.V1;

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
     * @param islandId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelList(String clientId, String token, String islandId) throws IOException {
        return getChannelList(BaseUtil.Authorization(clientId,token), islandId);
    }

    /**
     * 获取频道列表
     *
     * @param authorization authorization
     * @param islandId 群号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelList(String authorization, String islandId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"" +
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
        url = "https://botopen.imdodo.com/api/v1/channel/info";
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
     * @param islandId 群号
     * @param channelName 频道名
     * @param channelType 频道类型
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannel(String clientId, String token, String islandId, String channelName, int channelType) throws IOException {
        return addChannel(BaseUtil.Authorization(clientId, token), islandId, channelName, channelType);
    }

    /**
     * 创建频道
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param channelName 频道名
     * @param channelType 频道类型
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannel(String authorization, String islandId, String channelName, int channelType) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
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
     * @param islandId 群号
     * @param channelName 频道名
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannel(String clientId, String token, String islandId, String channelName, String channelId) throws IOException {
        return editChannel(BaseUtil.Authorization(clientId, token), islandId, channelName, channelId);
    }

    /**
     * 编辑频道
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param channelName 频道名
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannel(String authorization, String islandId, String channelName, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/edit";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
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
     * @param islandId 群号
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject deleteChannel(String clientId, String token, String islandId, String channelId) throws IOException {
        return deleteChannel(BaseUtil.Authorization(clientId, token), islandId, channelId);
    }

    /**
     * 删除频道
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject deleteChannel(String authorization, String islandId, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"channelId\": \"" + channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
