package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.NonNull;

/**
 * 用户接口
 */
public interface User {
    String getIslandSourceId();

    String getDodoSourceId();

    String getNickName();

    String getName();

    String getAvatarUrl();

    String getJoinTime();

    int getSex();

    int getLevel();

    int getIsBot();

    int getOnlineDevice();

    int getOnlineStatus();

    String getInviterDodoSourceId();

    void editNickName(@NonNull String nickName);

    void mute(int mills);

    void mute(int mills, @NonNull String reason);

    int getInvitationCount();

    /**
     * 封禁成员,传null视作不传参
     */
    void ban(String reason, String noticeChannelId);

    void unmute();

    void unban();

    void addRole(@NonNull String roleId);

    void removeRole(@NonNull String roleId);

    long getIntegralBalance();

    void editIntegral(int type, long integral);

    void addIntegral(long integral);

    void removeIntegral(long integral);

    String sendPersonalMessage(@NonNull Message message);
}
