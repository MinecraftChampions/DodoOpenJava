package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Island;
import io.github.minecraftchampions.dodoopenjava.api.User;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.*;

/**
 * Dodo用户实现
 *
 * @author qscbm187531
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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

    @Override
    public Island getIsland() {
        return new IslandImpl(getIslandSourceId(), bot);
    }

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
    public Result editNickName(String nickName) {
        return getBot().getApi().V2.memberApi.editMemberNickName(getIslandSourceId(), getDodoSourceId(), nickName);
    }

    @Override
    public Result mute(int mills) {
        return getBot().getApi().V2.memberApi.addMemberMute(getIslandSourceId(), getDodoSourceId(), mills);
    }

    @Override
    public Result mute(int mills, String reason) {
        return getBot().getApi().V2.memberApi.addMemberReasonMute(getIslandSourceId(), getDodoSourceId(), mills, reason);
    }

    @Override
    public int getInvitationCount() {
        return getBot().getApi().V2.memberApi.getMemberInvitationInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getInt("invitationCount");
        });
    }

    @Override
    public Result ban(String reason, String noticeChannelId) {
        MemberApi.BanSetting setting = MemberApi.BanSetting.builder()
                .reason(reason).noticeChannelId(noticeChannelId).build();
        return getBot().getApi().V2.memberApi.addMemberBan(getIslandSourceId(), getDodoSourceId(), setting);
    }

    @Override
    public Result unmute() {
        return getBot().getApi().V2.memberApi.removeMemberMute(getIslandSourceId(), getDodoSourceId());
    }

    @Override
    public Result unban() {
        return getBot().getApi().V2.memberApi.removeMemberBan(getIslandSourceId(), getDodoSourceId());
    }

    @Override
    public Result addRole(String roleId) {
        return getBot().getApi().V2.roleApi.addRoleMember(getIslandSourceId(), getDodoSourceId(), roleId);
    }

    @Override
    public Result removeRole(String roleId) {
        return getBot().getApi().V2.roleApi.removeRoleMember(getIslandSourceId(), getDodoSourceId(), roleId);
    }

    @Override
    public long getIntegralBalance() {
        return getBot().getApi().V2.integralApi.getIntegralInfo(getIslandSourceId(), getDodoSourceId()).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getLong("integralBalance");
        });
    }

    @Override
    public Result editIntegral(int type, long integral) {
        return getBot().getApi().V2.integralApi.setIntegralEdit(getIslandSourceId(), getDodoSourceId(), type, integral);
    }

    @Override
    public Result addIntegral(long integral) {
        return editIntegral(1, integral);
    }

    @Override
    public Result removeIntegral(long integral) {
        return editIntegral(2, integral);
    }

    @Override
    public String sendPersonalMessage(Message message) {
        return getBot().getApi().V2.personalApi.sendMessage(getIslandSourceId(), getDodoSourceId(), message).ifSuccess((result) -> {
            return result.getJSONObjectData().getJSONObject("data").getString("messageId");
        });
    }
}