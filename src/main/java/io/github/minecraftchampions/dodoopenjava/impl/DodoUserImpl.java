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
@RequiredArgsConstructor
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
        return new IslandImpl(islandSourceId, bot);
    }

    /**
     * 获取加入时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    @Override
    public String getJoinTime() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getString("joinTime");
        });
    }

    /**
     * 获取群昵称
     *
     * @return name
     */
    @Override
    public String getNickName() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getString("nickName");
        });
    }

    /**
     * 获取原名
     *
     * @return name
     */
    @Override
    public String getName() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getString("personalNickName");
        });
    }

    /**
     * 获取头像链接
     *
     * @return url
     */
    @Override
    public String getAvatarUrl() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getString("avatarUrl");
        });
    }

    /**
     * 获取等级
     *
     * @return url
     */
    @Override
    public int getLevel() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getInt("level");
        });
    }

    @Override
    public int getSex() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getInt("sex");
        });
    }

    @Override
    public int isBot() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getInt("isBot");
        });
    }

    @Override
    public int getOnlineDevice() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getInt("onlineDevice");
        });
    }

    @Override
    public int getOnlineStatus() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getInt("onlineStatus");
        });
    }

    @Override
    public String getInviterDodoSourceId() {
        return bot.getApi().getMemberApi().getMemberInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getString("inviterDodoSourceId");
        });
    }

    @Override
    public Result editNickName(String nickName) {
        return bot.getApi().getMemberApi().editMemberNickName(islandSourceId, dodoSourceId, nickName);
    }

    @Override
    public Result mute(int mills) {
        return bot.getApi().getMemberApi().addMemberMute(islandSourceId, dodoSourceId, mills);
    }

    @Override
    public Result mute(int mills, String reason) {
        return bot.getApi().getMemberApi().addMemberReasonMute(islandSourceId, dodoSourceId, mills, reason);
    }

    @Override
    public int getInvitationCount() {
        return bot.getApi().getMemberApi().getMemberInvitationInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getInt("invitationCount");
        });
    }

    @Override
    public Result ban(String reason, String noticeChannelId) {
        MemberApi.BanSetting setting = MemberApi.BanSetting.builder()
                .reason(reason).noticeChannelId(noticeChannelId).build();
        return bot.getApi().getMemberApi().addMemberBan(islandSourceId, dodoSourceId, setting);
    }

    @Override
    public Result unmute() {
        return bot.getApi().getMemberApi().removeMemberMute(islandSourceId, dodoSourceId);
    }

    @Override
    public Result unban() {
        return bot.getApi().getMemberApi().removeMemberBan(islandSourceId, dodoSourceId);
    }

    @Override
    public Result addRole(String roleId) {
        return bot.getApi().getRoleApi().addRoleMember(islandSourceId, dodoSourceId, roleId);
    }

    @Override
    public Result removeRole(String roleId) {
        return bot.getApi().getRoleApi().removeRoleMember(islandSourceId, dodoSourceId, roleId);
    }

    @Override
    public long getIntegralBalance() {
        return bot.getApi().getIntegralApi().getIntegralInfo(islandSourceId, dodoSourceId).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getLong("integralBalance");
        });
    }

    @Override
    public Result editIntegral(int type, long integral) {
        return bot.getApi().getIntegralApi().setIntegralEdit(islandSourceId, dodoSourceId, type, integral);
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
        return bot.getApi().getPersonalApi().sendMessage(islandSourceId, dodoSourceId, message).ifSuccess((result) -> {
            return result.getData().getJSONObject("data").getString("messageId");
        });
    }
}