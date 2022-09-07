package io.github.mcchampions.DodoOpenJava.api;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 语言频道API
 */
public class ChannelVoiceApi {
    public static String url,param;

    /**
     * 获取成员语音频道状态
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandId,String dodoId,String clientId, String token) throws IOException {
        return getChannelVoiceMemberStatus(islandId, dodoId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 获取成员语音频道状态
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param Authorization Authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandId,String dodoId,String Authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/status";
        param = "{" +
                "    \"islandId\": \""+ islandId +"\"," +
                "    \"dodoId\": \""+ dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }


    /**
     * 移动语音频道成员
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param ChannelId 移动到的频道号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject moveChannelVoiceMember(String islandId,String dodoId,String ChannelId,String clientId, String token) throws IOException {
        return moveChannelVoiceMember(islandId, dodoId, ChannelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 移动语音频道成员
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param ChannelId 移动到的频道号
     * @param Authorization Authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject moveChannelVoiceMember(String islandId,String dodoId,String ChannelId,String Authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/move";
        param = "{" +
                "    \"islandId\": \""+ islandId +"\"," +
                "    \"dodoId\": \""+ dodoId + "\"," +
                "    \"channelId\": \""+ ChannelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 管理语音中的成员
     * @param operateType 执行管理（详见文档）
     * @param dodoId Dodo号
     * @param ChannelId 移动到的频道号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoId,String ChannelId,String clientId, String token) throws IOException {
        return editChannelVoiceMember(operateType, dodoId, ChannelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 管理语音中的成员
     * @param operateType 执行管理（详见文档）
     * @param dodoId Dodo号
     * @param ChannelId 移动到的频道号
     * @param Authorization Authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoId,String ChannelId,String Authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/edit";
        param = "{" +
                "    \"operateType\": "+ operateType +"," +
                "    \"dodoId\": \""+ dodoId + "\"," +
                "    \"channelId\": \""+ ChannelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }
}
