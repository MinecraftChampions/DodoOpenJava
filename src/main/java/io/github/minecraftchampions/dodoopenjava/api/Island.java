package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.permission.Permission;
import lombok.NonNull;
import org.json.JSONObject;

import java.util.List;

/**
 * 超级群
 */
public interface Island {
    /**
     * 获取群ID
     *
     * @return id
     */
    String getIslandSourceId();

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
     * 获取指定身份组的成员数量
     *
     * @param roleId id
     * @return count
     */
    List<User> getRoleMemberList(@NonNull String roleId);

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
    List<User> getIslandMuteList();

    /**
     * 获取封禁列表
     *
     * @return list
     */
    List<User> getIslandBanList();

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
    User getUser(@NonNull String dodoId);

    /**
     * 获取频道列表
     *
     * @return list
     */
    List<Channel> getChannelList();

    /**
     * 获取频道
     *
     * @param channelId 频道id
     * @return 频道
     */
    Channel getChannel(@NonNull String channelId);

    /**
     * 获取身份组列表
     *
     * @return list
     */
    List<Role> getRoleList();

    /**
     * 获取身份组
     *
     * @param roleId id
     * @return role
     */
    Role getRole(@NonNull String roleId);

    /**
     * 创建频道
     *
     * @param channelName 名
     * @param channelType 类型
     * @return Channel
     */
    Channel createChannel(@NonNull String channelName, int channelType);

    /**
     * 创建频道
     *
     * @param channelType 类型
     * @return Channel
     */
    Channel createChannel(int channelType);

    Bot getBot();

    /**
     * 创建身分组
     * 可传 null
     *
     * @param roleName   name
     * @param roleColor  color
     * @param position   权重
     * @param permission 权限
     * @return role
     */
    Role createRole(String roleName, String roleColor, int position, Permission permission);
}
