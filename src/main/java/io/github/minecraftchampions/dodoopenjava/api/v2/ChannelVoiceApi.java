package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtils;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 语言频道API
 *
 * @author qscbm187531
 */
public class ChannelVoiceApi {
    /**
     * 获取成员语音频道状态
     *
     * @param clientId       clientId
     * @param token          token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getChannelVoiceMemberStatus(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getChannelVoiceMemberStatus(BaseUtils.generateAuthorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 获取成员语音频道状态
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getChannelVoiceMemberStatus(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/voice/member/status";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, authorization);
    }


    /**
     * 移动语音频道成员
     *
     * @param clientId       clientId
     * @param token          token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param channelId      移动到的频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result moveChannelVoiceMember(String clientId, String token, String islandSourceId, String dodoSourceId, String channelId) throws IOException {
        return moveChannelVoiceMember(BaseUtils.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, channelId);
    }

    /**
     * 移动语音频道成员
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param channelId      移动到的频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result moveChannelVoiceMember(String authorization, String islandSourceId, String dodoSourceId, String channelId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/voice/member/move";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("channelId", channelId);
        return NetUtils.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 管理语音中的成员
     *
     * @param clientId     clientId
     * @param token        token
     * @param operateType  执行管理（详见文档）
     * @param dodoSourceId Dodo号
     * @param channelId    移动到的频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editChannelVoiceMember(String clientId, String token, int operateType, String dodoSourceId, String channelId) throws IOException {
        return editChannelVoiceMember(BaseUtils.generateAuthorization(clientId, token), operateType, dodoSourceId, channelId);
    }

    /**
     * 管理语音中的成员
     *
     * @param authorization authorization
     * @param operateType   执行管理（详见文档）
     * @param dodoSourceId  Dodo号
     * @param channelId     移动到的频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editChannelVoiceMember(String authorization, int operateType, String dodoSourceId, String channelId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/voice/member/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operateType", operateType)
                .put("dodoSourceId", dodoSourceId)
                .put("channelId", channelId);
        return NetUtils.sendRequest(jsonObject.toString(), url, authorization);
    }
}
