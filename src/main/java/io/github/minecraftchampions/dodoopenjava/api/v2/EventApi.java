package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 事件API
 */
public class EventApi {
    /**
     * 获取WebSocket连接
     *
     * @param clientId 机器人唯一标识
     * @param token    机器人鉴权Token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getWebSocketConnection(String clientId, String token) throws IOException {
        return getWebSocketConnection(BaseUtil.generateAuthorization(clientId, token));
    }

    /**
     * 获取WebSocket连接
     *
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getWebSocketConnection(String authorization) throws IOException {
        String url = DodoOpenJava.BASEURL + "websocket/connection";
        JSONObject jsonObject = new JSONObject();
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }
}
