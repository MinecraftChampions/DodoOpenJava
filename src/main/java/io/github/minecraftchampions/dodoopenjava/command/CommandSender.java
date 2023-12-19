package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.Bot;
import io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi;
import io.github.minecraftchampions.dodoopenjava.permissions.UserManager;
import lombok.Getter;
import lombok.NonNull;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 命令发送者
 */
@Getter
public class CommandSender {
    /**
     * -- GETTER --
     * 获取DodoID
     */
    private final String SenderDodoSourceId;

    /**
     * -- GETTER --
     * 获取群号
     */
    private final String IslandSourceId;

    /**
     * -- GETTER --
     * 获取触发命令的频道号
     */
    private final String ChannelId;

    /**
     * -- GETTER --
     * 获取头像URL
     */
    private final String AvatarUrl;

    /**
     * -- GETTER --
     * 获取发送者群昵称
     */
    private String SenderNickName;

    /**
     * -- GETTER --
     * 获取发送者原本名称
     */
    private final String SenderName;

    /**
     * -- GETTER --
     * 获取加入时间
     */
    private final String JoinTime;

    /**
     * -- GETTER --
     * 获取触发命令的消息ID
     */
    private final String MessageId;

    /**
     * -- GETTER --
     * 获取所属机器人
     */
    private final Bot bot;

    /**
     * 初始化发送者这个类型
     *
     * @param jsontext JSONText
     */
    public CommandSender(JSONObject jsontext, Bot bot) {
        this.bot = bot;
        this.SenderNickName = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.SenderName = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.JoinTime = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.AvatarUrl = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.SenderDodoSourceId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.IslandSourceId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.MessageId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        this.ChannelId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
    }

    /**
     * 编辑成员群昵称
     *
     * @param NickName 群昵称
     * @throws IOException 编辑失败后抛出
     */
    public void editSenderNickName(@NonNull String NickName) throws IOException {
        System.out.println(MemberApi.editMemberNickName(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId, NickName));
        SenderNickName = NickName;
    }

    /**
     * 禁言成员
     *
     * @param Time 时间（秒为单位）
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time) throws IOException {
        MemberApi.addMemberMute(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId, Time);
    }

    /**
     * 禁言成员
     *
     * @param Time   时间（秒为单位）
     * @param reason 禁言理由
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time, @NonNull String reason) throws IOException {
        MemberApi.addMemberReasonMute(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId, Time, reason);
    }

    /**
     * 取消禁言成员
     *
     * @throws IOException 失败后抛出
     */
    public void removeMuteSender() throws IOException {
        MemberApi.removeMemberMute(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId);
    }

    /**
     * 封禁成员
     *
     * @param reason 封禁理由
     * @throws IOException 失败后抛出
     */
    public void banSender(@NonNull String reason) throws IOException {
        MemberApi.addMemberReasonBan(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId, reason);
    }

    /**
     * 封禁成员
     *
     * @throws IOException 失败后抛出
     */
    public void banSender() throws IOException {
        MemberApi.addMemberBan(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId);
    }

    /**
     * 取消封禁成员
     *
     * @throws IOException 失败后抛出
     */
    public void removeBanSender() throws IOException {
        MemberApi.removeMemberBan(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId);
    }

    /**
     * 赋予成员身份组
     *
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void giveRole(@NonNull String RoleId) throws IOException {
        RoleApi.addRoleMember(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId, RoleId);
    }

    /**
     * 取消成员身份组
     *
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void removeRole(@NonNull String RoleId) throws IOException {
        RoleApi.removeRoleMember(bot.getAuthorization(), IslandSourceId, SenderDodoSourceId, RoleId);
    }

    /**
     * 是否拥有权限
     *
     * @param permission 权限
     * @return true有，false失败
     */
    public boolean hasPermission(String permission) {
        return UserManager.hasPerm(UserManager.getUser(getSenderDodoSourceId()), permission);
    }
}
