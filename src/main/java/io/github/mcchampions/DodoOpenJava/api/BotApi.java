package io.github.mcchampions.DodoOpenJava.api;

import com.alibaba.fastjson.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;

import java.io.IOException;

/**
 * 机器人API
 */
public class BotApi {
    public static String url,parm;

    /**
     * 获取机器人信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数
     */
    public static String getBotInfo(String clientId, String token, Boolean returnJSONText) throws IOException {
        return getBotInfo(BaseUtil.Authorization(clientId,token),returnJSONText);
    }

    /**
     * 获取机器人信息
     *
     * @param Authorization Authorization
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数
     */
    public static String getBotInfo(String Authorization, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/bot/info";
        parm = "{}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String clientId = JSONObject.parseObject(Parm).getJSONObject("data").getString("clientId");
            String dodoId = JSONObject.parseObject(Parm).getJSONObject("data").getString("dodoId");
            String nickName = JSONObject.parseObject(Parm).getJSONObject("data").getString("nickName");
            String avatarUrl = JSONObject.parseObject(Parm).getJSONObject("data").getString("avatarUrl");
            Parm = "机器人唯一标识: \"" + clientId + "\"\n" +
                    "机器人DoDo号: \"" + dodoId + "\"\n" +
                    "机器人昵称: \"" + nickName	 + "\"\n" +
                    "机器人头像URL地址: \"" + avatarUrl + "\"";
        }
        return Parm;
    }

    /**
     * 机器人退群
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数，由于没什么好解析的，所以false就是返回null
     */
    public static String setBotIslandLeave(String clientId, String token, String islandId, Boolean returnJSONText) throws IOException {
        return setBotIslandLeave(BaseUtil.Authorization(clientId, token), islandId, returnJSONText);
    }

    /**
     * 机器人退群
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数，由于没什么好解析的，所以false就是返回null
     */
    public static String setBotIslandLeave(String Authorization, String islandId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/bot/island/leave";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }
}
