package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Bot;
import io.github.minecraftchampions.dodoopenjava.api.User;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Dodo用户实现
 *
 * @author qscbm187531
 */
@Getter
@RequiredArgsConstructor
public class DodoUserImpl implements User {
    /**
     * -- GETTER --
     * 获取DodoID
     */
    @NonNull
    private final String dodoSourceId;

    /**
     * -- GETTER --
     * 获取群号
     */
    @NonNull
    private final String islandSourceId;

    /**
     * -- GETTER --
     * 获取所属机器人
     */
    @NonNull
    private final Bot bot;

    /**
     * 获取加入时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    @Override
    public String getJoinTime() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getString("joinTime");
        });
    }

    /**
     * 获取群昵称
     *
     * @return name
     */
    @Override
    public String getNickName() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getString("nickName");
        });
    }

    /**
     * 获取原名
     *
     * @return name
     */
    @Override
    public String getName() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getString("personalNickName");
        });
    }

    /**
     * 获取头像链接
     *
     * @return url
     */
    @Override
    public String getAvatarUrl() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getString("avatarUrl");
        });
    }

    /**
     * 获取等级
     *
     * @return url
     */
    @Override
    public int getLevel() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getInt("level");
        });
    }

    @Override
    public int getSex() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getInt("sex");
        });
    }

    @Override
    public int isBot() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getInt("isBot");
        });
    }

    @Override
    public int getOnlineDevice() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getInt("onlineDevice");
        });
    }

    @Override
    public int getOnlineStatus() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getInt("onlineStatus");
        });
    }

    @Override
    public String getInviterDodoSourceId() {
        return getBot().getApi().V2.memberApi.getMemberInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getString("inviterDodoSourceId");
        });
    }

    @Override
    public void editNickName(String nickName) {
        getBot().getApi().V2.memberApi.editMemberNickName(getIslandSourceId(), getDodoSourceId(), nickName);
    }

    @Override
    public void mute(int mills) {
        getBot().getApi().V2.memberApi.addMemberMute(getIslandSourceId(), getDodoSourceId(), mills);
    }

    @Override
    public void mute(int mills, String reason) {
        getBot().getApi().V2.memberApi.addMemberReasonMute(getIslandSourceId(), getDodoSourceId(), mills, reason);
    }

    @Override
    public int getInvitationCount() {
        return getBot().getApi().V2.memberApi.getMemberInvitationInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getInt("invitationCount");
        });
    }

    @Override
    public void ban(String reason, String noticeChannelId) {
        if (noticeChannelId == null && reason == null) {
            getBot().getApi().V2.memberApi.addMemberBan(getIslandSourceId(), getDodoSourceId());
        }
        if (noticeChannelId == null && reason != null) {
            getBot().getApi().V2.memberApi.addMemberReasonBan(getIslandSourceId(), getDodoSourceId(), reason);
        }
        if (reason == null && noticeChannelId != null) {
            getBot().getApi().V2.memberApi.addMemberChannelBan(getIslandSourceId(), getDodoSourceId(), noticeChannelId);
        }
        getBot().getApi().V2.memberApi.addMemberReasonChannelBan(getIslandSourceId(), getDodoSourceId(), reason, noticeChannelId);
    }

    @Override
    public void unmute() {
        getBot().getApi().V2.memberApi.removeMemberMute(getIslandSourceId(), getDodoSourceId());
    }

    @Override
    public void unban() {
        getBot().getApi().V2.memberApi.removeMemberBan(getIslandSourceId(), getDodoSourceId());
    }

    @Override
    public void addRole(String roleId) {
        getBot().getApi().V2.roleApi.addRoleMember(getIslandSourceId(), getDodoSourceId(), roleId);
    }

    @Override
    public void removeRole(String roleId) {
        getBot().getApi().V2.roleApi.removeRoleMember(getIslandSourceId(), getDodoSourceId(), roleId);
    }

    @Override
    public long getIntegralBalance() {
        return getBot().getApi().V2.integralApi.getIntegralInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getLong("integralBalance");
        });
    }

    @Override
    public void editIntegral(int type, long integral) {
        getBot().getApi().V2.integralApi.setIntegralEdit(getIslandSourceId(), getDodoSourceId(), type, integral);
    }

    @Override
    public void addIntegral(long integral) {
        editIntegral(1, integral);
    }

    @Override
    public void removeIntegral(long integral) {
        editIntegral(2, integral);
    }

    @Override
    public String sendPersonalMessage(Message message) {
        return getBot().getApi().V2.personalApi.sendMessage(getIslandSourceId(), getDodoSourceId(), message).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getString("messageId");
        });
    }
}