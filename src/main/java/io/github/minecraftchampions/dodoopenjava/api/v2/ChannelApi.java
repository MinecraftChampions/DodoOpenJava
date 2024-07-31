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
 * 频道API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class ChannelApi {
    @NonNull
    private Bot bot;

    /**
     * 获取频道列表
     *
     * @param islandSourceId 群号
     * @return result
     */
    public Result getChannelList(String islandSourceId) {
        String url = DodoOpenJava.BASEURL + "channel/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取频道信息
     *
     * @param channelId 频道号
     * @return result
     */
    public Result getChannelInfo(String channelId) {
        String url = DodoOpenJava.BASEURL + "channel/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 创建频道
     *
     * @param islandSourceId 群号
     * @param channelName    频道名
     * @param channelType    频道类型
     * @return result
     */
    public Result addChannel(String islandSourceId, String channelName, int channelType) {
        String url = DodoOpenJava.BASEURL + "channel/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("channelType", channelType);
        if (channelName != null) {
            jsonObject.put("channelName", channelName);
        }
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 编辑频道
     *
     * @param islandSourceId 群号
     * @param channelName    频道名
     * @param channelId      频道号
     * @return result
     */
    public Result editChannel(String islandSourceId, String channelName, String channelId) {
        String url = DodoOpenJava.BASEURL + "channel/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("channelName", channelName)
                .put("channelId", channelId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 删除频道
     *
     * @param islandSourceId 群号
     * @param channelId      频道号
     * @return result
     */

    public Result deleteChannel(String islandSourceId, String channelId) {
        String url = DodoOpenJava.BASEURL + "channel/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("channelId", channelId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
