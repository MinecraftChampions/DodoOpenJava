package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 语言频道API
 * @author qscbm187531
 */
public class ChannelVoiceApi {
    public static String url,param;

    /**
     * 获取成员语音频道状态
     * @param islandSourceId 群号
     * @param dodoSourceId Dodo号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandSourceId,String dodoSourceId,String clientId, String token) throws IOException {
        return getChannelVoiceMemberStatus(islandSourceId, dodoSourceId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 获取成员语音频道状态
     * @param islandSourceId 群号
     * @param dodoSourceId Dodo号
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandSourceId,String dodoSourceId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/voice/member/status";
        param = "{" +
                "    \"islandSourceId\": \""+ islandSourceId +"\"," +
                "    \"dodoSourceId\": \""+ dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }


    /**
     * 移动语音频道成员
     * @param islandSourceId 群号
     * @param dodoSourceId Dodo号
     * @param channelId 移动到的频道号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject moveChannelVoiceMember(String islandSourceId,String dodoSourceId,String channelId,String clientId, String token) throws IOException {
        return moveChannelVoiceMember(islandSourceId, dodoSourceId, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 移动语音频道成员
     * @param islandSourceId 群号
     * @param dodoSourceId Dodo号
     * @param channelId 移动到的频道号
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject moveChannelVoiceMember(String islandSourceId,String dodoSourceId,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/voice/member/move";
        param = "{" +
                "    \"islandSourceId\": \""+ islandSourceId +"\"," +
                "    \"dodoSourceId\": \""+ dodoSourceId + "\"," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 管理语音中的成员
     * @param operateType 执行管理（详见文档）
     * @param dodoSourceId Dodo号
     * @param channelId 移动到的频道号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoSourceId,String channelId,String clientId, String token) throws IOException {
        return editChannelVoiceMember(operateType, dodoSourceId, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 管理语音中的成员
     * @param operateType 执行管理（详见文档）
     * @param dodoSourceId Dodo号
     * @param channelId 移动到的频道号
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoSourceId,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/voice/member/edit";
        param = "{" +
                "    \"operateType\": "+ operateType +"," +
                "    \"dodoSourceId\": \""+ dodoSourceId + "\"," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
