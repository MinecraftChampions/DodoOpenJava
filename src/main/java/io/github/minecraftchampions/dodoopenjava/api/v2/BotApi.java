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
 * 机器人API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class BotApi {
    @NonNull
    private Bot bot;

    /**
     * 获取机器人信息
     *
     * @return result
     */
    public Result getBotInfo() {
        String url = DodoOpenJava.BASEURL + "bot/info";
        JSONObject jsonObject = new JSONObject();
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 机器人退群
     *
     * @param islandSourceId 群号
     * @return result
     */
    public Result setBotIslandLeave(String islandSourceId) {
        String url = DodoOpenJava.BASEURL + "bot/island/leave";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取机器人邀请列表
     *
     * @param pageSize 页大小，最大100
     * @param maxId    上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return result
     */
    public Result getBotInviteList(int pageSize, long maxId) {
        String url = DodoOpenJava.BASEURL + "bot/invite/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageSize", pageSize)
                .put("maxId", maxId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 添加成员到机器人邀请列表
     *
     * @param dodoSourceId dodoSourceId
     * @return result
     */
    public Result addBotInvite(String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "bot/invite/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 移除成员出机器人邀请列表
     *
     * @param dodoSourceId dodoSourceId
     * @return result
     */
    public Result removeBotInvite(String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "bot/invite/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
