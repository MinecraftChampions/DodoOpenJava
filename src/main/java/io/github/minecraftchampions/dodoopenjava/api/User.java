package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.NonNull;

/**
 * 用户接口
 *
 * @author qscbm187531
 */
public interface User {
    /**
     * 获取群ID
     *
     * @return id
     */
    String getIslandSourceId();

    /**
     * 获取ID
     *
     * @return id
     */

    String getDodoSourceId();

    /**
     * 获取群名
     *
     * @return 群名
     */
    String getNickName();


    /**
     * 获取用户名
     *
     * @return 用户名
     */
    String getName();


    /**
     * 获取头像url
     *
     * @return url
     */
    String getAvatarUrl();


    /**
     * 获取加入时间
     *
     * @return time
     */
    String getJoinTime();

    /**
     * 获取性别
     *
     * @return 性别
     */
    int getSex();

    /**
     * 获取等级
     *
     * @return 等级
     */
    int getLevel();

    /**
     * 是否为机器人
     *
     * @return 是否为机器人
     */
    int isBot();

    /**
     * 获取在线设备
     *
     * @return 设备
     */
    int getOnlineDevice();

    /**
     * 获取在线状态
     *
     * @return 状态
     */
    int getOnlineStatus();


    /**
     * 获取邀请该用户的用户的ID
     *
     * @return id
     */
    String getInviterDodoSourceId();

    /**
     * 编辑群名
     *
     * @param nickName 群名
     */
    void editNickName(@NonNull String nickName);


    /**
     * 禁言
     *
     * @param mills 时间
     */
    void mute(int mills);

    /**
     * 禁言
     *
     * @param mills  时间
     * @param reason 原因
     */
    void mute(int mills, @NonNull String reason);

    /**
     * 获取邀请数量
     *
     * @return 数量
     */
    int getInvitationCount();

    /**
     * 封禁成员,传null视作不传参
     *
     * @param reason          原因
     * @param noticeChannelId 通知频道ID
     */
    void ban(String reason, String noticeChannelId);

    /**
     * 解除禁言
     */
    void unmute();

    /**
     * 解封
     */
    void unban();

    /**
     * 添加身份组
     *
     * @param roleId id
     */
    void addRole(@NonNull String roleId);

    /**
     * 移除身份组
     *
     * @param roleId id
     */
    void removeRole(@NonNull String roleId);

    /**
     * 获取余额
     *
     * @return 余额
     */
    long getIntegralBalance();

    /**
     * 操作余额
     *
     * @param integral 数量
     * @param type     操作方式
     */
    void editIntegral(int type, long integral);

    /**
     * 添加余额
     *
     * @param integral 数量
     */
    void addIntegral(long integral);

    /**
     * 移除余额
     *
     * @param integral 数量
     */
    void removeIntegral(long integral);

    /**
     * 发送私聊
     *
     * @param message 消息
     * @return id
     */
    String sendPersonalMessage(@NonNull Message message);
}
