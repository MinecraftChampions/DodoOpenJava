package io.github.minecraftchampions.dodoopenjava.api;

import org.json.JSONObject;

import java.util.List;

public interface Island {
    /**
     * 获取群ID
     *
     * @return id
     */
    String getIslandId();

    /**
     * 获取群名
     *
     * @return 名
     */
    String getIslandName();

    /**
     * 获取群头像地址
     *
     * @return url
     */
    String getCoverUrl();

    /**
     * 获取成员数量
     *
     * @return 数量
     */
    int getMemberCount();

    /**
     * 获取在线成员数量
     *
     * @return 在线成员数量
     */
    int getOnlineMemberCount();

    /**
     * 获取群描述
     *
     * @return 描述
     */
    String getDescription();

    /**
     * 获取默认频道id
     *
     * @return id
     */
    String getDefaultChannelId();

    /**
     * 获取系统消息频道id
     *
     * @return id
     */
    String getSystemChannelId();

    /**
     * 获取群主id
     *
     * @return id
     */
    String getOwnerDodoId();

    /**
     * 获取等级排行榜
     *
     * @return rank
     */
    List<JSONObject> getIslandLevelRankList();

    /**
     * 获取禁言列表
     *
     * @return list
     */
    List<JSONObject> getIslandMuteList();

    /**
     * 获取封禁列表
     *
     * @return list
     */
    List<JSONObject> getIslandBanList();

    /**
     * 获取成员列表
     *
     * @return list
     */
    List<User> getMemberList();

    /**
     * 获取用户
     *
     * @param dodoId id
     * @return user
     */
    User getUser(String dodoId);
}
