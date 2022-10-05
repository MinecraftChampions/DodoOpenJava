package io.github.mcchampions.DodoOpenJava.Api;

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
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelVoiceMemberStatus(String islandId,String dodoId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/status";
        param = "{" +
                "    \"islandId\": \""+ islandId +"\"," +
                "    \"dodoId\": \""+ dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.simulationBrowserRequest(param, url, authorization));
    }


    /**
     * 移动语音频道成员
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param channelId 移动到的频道号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject moveChannelVoiceMember(String islandId,String dodoId,String channelId,String clientId, String token) throws IOException {
        return moveChannelVoiceMember(islandId, dodoId, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 移动语音频道成员
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param channelId 移动到的频道号
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject moveChannelVoiceMember(String islandId,String dodoId,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/move";
        param = "{" +
                "    \"islandId\": \""+ islandId +"\"," +
                "    \"dodoId\": \""+ dodoId + "\"," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.simulationBrowserRequest(param, url, authorization));
    }

    /**
     * 管理语音中的成员
     * @param operateType 执行管理（详见文档）
     * @param dodoId Dodo号
     * @param channelId 移动到的频道号
     * @param clientId clientId
     * @param token token
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoId,String channelId,String clientId, String token) throws IOException {
        return editChannelVoiceMember(operateType, dodoId, channelId, BaseUtil.Authorization(clientId, token));
    }

    /**
     * 管理语音中的成员
     * @param operateType 执行管理（详见文档）
     * @param dodoId Dodo号
     * @param channelId 移动到的频道号
     * @param authorization authorization
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelVoiceMember(int operateType,String dodoId,String channelId,String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/voice/member/edit";
        param = "{" +
                "    \"operateType\": "+ operateType +"," +
                "    \"dodoId\": \""+ dodoId + "\"," +
                "    \"channelId\": \""+ channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.simulationBrowserRequest(param, url, authorization));
    }
}
