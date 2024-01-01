package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 赠礼系统Api
 */
public class GiftApi {
    /**
     * 获取群收入
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftAccount(String clientId, String token, String islandSourceId) throws IOException {
        return getGiftAccount(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * 获取群收入
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftAccount(String authorization, String islandSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "gift/account/info";
        String param = new JSONObject()
                .put("islandSourceId", islandSourceId)
                .toString();
        return NetUtil.sendRequest(param, url, authorization);
    }

    /**
     * 获取成员分成管理
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftShareRatioInfo(String clientId, String token, String islandSourceId) throws IOException {
        return getGiftShareRatioInfo(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * 获取成员分成管理
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftShareRatioInfo(String authorization, String islandSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "gift/share/ratio/info";
        String param = new JSONObject()
                .put("islandSourceId", islandSourceId)
                .toString();
        return NetUtil.sendRequest(param, url, authorization);
    }

    /**
     * 获取内容礼物列表
     *
     * @param clientId   机器人唯一标识
     * @param token      机器人鉴权Token
     * @param targetId   内容ID
     * @param targetType 内容类型，1：消息，2：帖子
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftList(String clientId, String token, String targetId, int targetType) throws IOException {
        return getGiftList(BaseUtil.Authorization(clientId, token), targetId, targetType);
    }

    /**
     * 获取内容礼物列表
     *
     * @param authorization authorization
     * @param targetId      内容ID
     * @param targetType    内容类型，1：消息，2：帖子
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftList(String authorization, String targetId, int targetType) throws IOException {
        String url = DodoOpenJava.BASEURL + "gift/list";
        String param = new JSONObject()
                .put("targetId", targetId)
                .put("targetType", targetType)
                .toString();
        return NetUtil.sendRequest(param, url, authorization);
    }

    /**
     * 获取内容礼物内成员列表
     *
     * @param clientId   机器人唯一标识
     * @param token      机器人鉴权Token
     * @param targetId   内容ID
     * @param targetType 内容类型，1：消息，2：帖子
     * @param giftId     礼物ID
     * @param pageSize   页大小，最大100
     * @param maxId      上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftMemberList(String clientId, String token, String targetId, int targetType, String giftId, int pageSize, long maxId) throws IOException {
        return getGiftMemberList(BaseUtil.Authorization(clientId, token), targetId, targetType, giftId, pageSize, maxId);
    }

    /**
     * 获取内容礼物内成员列表
     *
     * @param authorization authorization
     * @param targetId      内容ID
     * @param targetType    内容类型，1：消息，2：帖子
     * @param giftId        礼物ID
     * @param pageSize      页大小，最大100
     * @param maxId         上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftMemberList(String authorization, String targetId, int targetType, String giftId, int pageSize, long maxId) throws IOException {
        String url = DodoOpenJava.BASEURL + "gift/member/list";
        String param = new JSONObject()
                .put("targetId", targetId)
                .put("targetType", targetType)
                .put("giftId", giftId)
                .put("pageSize", pageSize)
                .put("maxId", maxId)
                .toString();
        return NetUtil.sendRequest(param, url, authorization);
    }

    /**
     * 用于获取指定内容的礼物总值列表
     *
     * @param clientId   机器人唯一标识
     * @param token      机器人鉴权Token
     * @param targetId   内容ID
     * @param targetType 内容类型，1：消息，2：帖子
     * @param pageSize   页大小，最大100
     * @param maxId      上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftGrossValueList(String clientId, String token, String targetId, int targetType, int pageSize, long maxId) throws IOException {
        return getGiftGrossValueList(BaseUtil.Authorization(clientId, token), targetId, targetType, pageSize, maxId);
    }

    /**
     * 用于获取指定内容的礼物总值列表
     *
     * @param authorization authorization
     * @param targetId      内容ID
     * @param targetType    内容类型，1：消息，2：帖子
     * @param pageSize      页大小，最大100
     * @param maxId         上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static Result getGiftGrossValueList(String authorization, String targetId, int targetType, int pageSize, long maxId) throws IOException {
        String url = DodoOpenJava.BASEURL + "gift/gross/value/list";
        String param = new JSONObject()
                .put("targetId", targetId)
                .put("targetType", targetType)
                .put("pageSize", pageSize)
                .put("maxId", maxId)
                .toString();
        return NetUtil.sendRequest(param, url, authorization);
    }
}
