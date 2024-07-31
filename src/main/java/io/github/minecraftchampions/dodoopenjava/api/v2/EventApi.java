package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

/**
 * 事件API
 *
 * @author qscbm187531
 */
@RequiredArgsConstructor
@Data
public class EventApi {
    @NonNull
    private Bot bot;

    /**
     * 获取WebSocket连接
     *
     * @return result
     */
    public Result getWebSocketConnection() {
        String url = DodoOpenJava.BASEURL + "websocket/connection";
        JSONObject jsonObject = new JSONObject();
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
