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
 * 群API
 *
 * @author qscbm187531
 */
@RequiredArgsConstructor
@Data
public class IslandApi {
    @NonNull
    private Bot bot;

    /**
     * 获取群信息
     *
     * @param islandSourceId 群号
     * @return result
     */
    public Result getIslandInfo(String islandSourceId) {
        String url = DodoOpenJava.BASEURL + "island/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取机器人群列表
     *
     * @return result
     */
    public Result getIslandList() {
        String url = DodoOpenJava.BASEURL + "island/list";
        JSONObject jsonObject = new JSONObject();
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取群等级排行榜
     *
     * @param islandSourceId 群号
     * @return result
     */
    public Result getIslandLevelRankList(String islandSourceId) {
        String url = DodoOpenJava.BASEURL + "/api/v2/island/level/rank/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取禁言列表
     *
     * @param islandSourceId 群号
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return result
     */
    public Result getIslandMuteList(String islandSourceId, int pageSize, long maxId) {
        String url = DodoOpenJava.BASEURL + "island/mute/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        jsonObject.put("pageSize", pageSize);
        jsonObject.put("maxId", maxId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取封禁列表
     *
     * @param islandSourceId 群号
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return result
     */
    public Result getIslandBanList(String islandSourceId, int pageSize, long maxId) {
        String url = DodoOpenJava.BASEURL + "island/ban/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId);
        jsonObject.put("pageSize", pageSize);
        jsonObject.put("maxId", maxId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
