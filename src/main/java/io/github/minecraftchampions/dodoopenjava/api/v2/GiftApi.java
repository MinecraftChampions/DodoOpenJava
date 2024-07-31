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
 * 赠礼系统Api
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class GiftApi {
    @NonNull
    private Bot bot;

    /**
     * 获取群收入
     *
     * @param islandSourceId 群号
     * @return result
     */
    public Result getGiftAccount(String islandSourceId) {
        String url = DodoOpenJava.BASEURL + "gift/account/info";
        String param = new JSONObject()
                .put("islandSourceId", islandSourceId)
                .toString();
        return NetUtils.sendRequest(param, url, bot.getAuthorization());
    }

    /**
     * 获取成员分成管理
     *
     * @param islandSourceId 群号
     * @return result
     */
    public Result getGiftShareRatioInfo(String islandSourceId) {
        String url = DodoOpenJava.BASEURL + "gift/share/ratio/info";
        String param = new JSONObject()
                .put("islandSourceId", islandSourceId)
                .toString();
        return NetUtils.sendRequest(param, url, bot.getAuthorization());
    }

    /**
     * 获取内容礼物列表
     *
     * @param targetId   内容ID
     * @param targetType 内容类型，1：消息，2：帖子
     * @return result
     */
    public Result getGiftList(String targetId, int targetType) {
        String url = DodoOpenJava.BASEURL + "gift/list";
        String param = new JSONObject()
                .put("targetId", targetId)
                .put("targetType", targetType)
                .toString();
        return NetUtils.sendRequest(param, url, bot.getAuthorization());
    }

    /**
     * 获取内容礼物内成员列表
     *
     * @param targetId   内容ID
     * @param targetType 内容类型，1：消息，2：帖子
     * @param giftId     礼物ID
     * @param pageSize   页大小，最大100
     * @param maxId      上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return result
     */
    public Result getGiftMemberList(String targetId, int targetType, String giftId,
                                    int pageSize, long maxId) {
        String url = DodoOpenJava.BASEURL + "gift/member/list";
        String param = new JSONObject()
                .put("targetId", targetId)
                .put("targetType", targetType)
                .put("giftId", giftId)
                .put("pageSize", pageSize)
                .put("maxId", maxId)
                .toString();
        return NetUtils.sendRequest(param, url, bot.getAuthorization());
    }

    /**
     * 用于获取指定内容的礼物总值列表
     *
     * @param targetId   内容ID
     * @param targetType 内容类型，1：消息，2：帖子
     * @param pageSize   页大小，最大100
     * @param maxId      上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return result
     */
    public Result getGiftGrossValueList(String targetId, int targetType, int pageSize,
                                        long maxId) {
        String url = DodoOpenJava.BASEURL + "gift/gross/value/list";
        String param = new JSONObject()
                .put("targetId", targetId)
                .put("targetType", targetType)
                .put("pageSize", pageSize)
                .put("maxId", maxId)
                .toString();
        return NetUtils.sendRequest(param, url, bot.getAuthorization());
    }
}
