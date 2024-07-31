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
 * 语言频道API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class ChannelVoiceApi {
    @NonNull
    private Bot bot;

    /**
     * 获取成员语音频道状态
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return result
     */
    public Result getChannelVoiceMemberStatus(String islandSourceId, String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "channel/voice/member/status";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 移动语音频道成员
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param channelId      移动到的频道号
     * @return result
     */
    public Result moveChannelVoiceMember(String islandSourceId, String dodoSourceId,
                                         String channelId) {
        String url = DodoOpenJava.BASEURL + "channel/voice/member/move";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("channelId", channelId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 管理语音中的成员
     *
     * @param operateType  执行管理（详见文档）
     * @param dodoSourceId Dodo号
     * @param channelId    移动到的频道号
     * @return JSON对象
     */
    public Result editChannelVoiceMember(int operateType, String dodoSourceId,
                                         String channelId) {
        String url = DodoOpenJava.BASEURL + "channel/voice/member/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operateType", operateType)
                .put("dodoSourceId", dodoSourceId)
                .put("channelId", channelId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
