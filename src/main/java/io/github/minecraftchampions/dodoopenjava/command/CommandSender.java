package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.Bot;
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
    private String ChannelId;

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
    private String JoinTime;

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
     * -- GETTER --
     * 是否私聊
     */
    private final boolean isPersonalChat;

    /**
     * 初始化发送者这个类型
     *
     * @param jsontext JSONText
     */
    public CommandSender(JSONObject jsontext, Bot bot, boolean personalMessage) {
        this.bot = bot;
        this.isPersonalChat = personalMessage;
        this.SenderNickName = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.SenderName = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.AvatarUrl = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.SenderDodoSourceId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.IslandSourceId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.MessageId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        if (!personalMessage) {
            this.ChannelId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
            this.JoinTime = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        }
    }

    /**
     * 编辑成员群昵称
     *
     * @param NickName 群昵称
     * @throws IOException 编辑失败后抛出
     */
    public synchronized void editSenderNickName(@NonNull String NickName) throws IOException {
        SenderNickName = NickName;
        bot.getApi().V2.memberApi.editMemberNickName(IslandSourceId, SenderDodoSourceId, NickName);
    }

    /**
     * 禁言成员
     *
     * @param Time 时间（秒为单位）
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time) throws IOException {
        bot.getApi().V2.memberApi.addMemberMute(IslandSourceId, SenderDodoSourceId, Time);
    }

    /**
     * 禁言成员
     *
     * @param Time   时间（秒为单位）
     * @param reason 禁言理由
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time, @NonNull String reason) throws IOException {
        bot.getApi().V2.memberApi.addMemberReasonMute(IslandSourceId, SenderDodoSourceId, Time, reason);
    }

    /**
     * 取消禁言成员
     *
     * @throws IOException 失败后抛出
     */
    public void removeMuteSender() throws IOException {
        bot.getApi().V2.memberApi.removeMemberMute(IslandSourceId, SenderDodoSourceId);
    }

    /**
     * 封禁成员
     *
     * @param reason 封禁理由
     * @throws IOException 失败后抛出
     */
    public void banSender(@NonNull String reason) throws IOException {
        bot.getApi().V2.memberApi.addMemberReasonBan(IslandSourceId, SenderDodoSourceId, reason);
    }

    /**
     * 封禁成员
     *
     * @throws IOException 失败后抛出
     */
    public void banSender() throws IOException {
        bot.getApi().V2.memberApi.addMemberBan(IslandSourceId, SenderDodoSourceId);
    }

    /**
     * 取消封禁成员
     *
     * @throws IOException 失败后抛出
     */
    public void removeBanSender() throws IOException {
        bot.getApi().V2.memberApi.removeMemberBan(IslandSourceId, SenderDodoSourceId);
    }

    /**
     * 赋予成员身份组
     *
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void giveRole(@NonNull String RoleId) throws IOException {
        bot.getApi().V2.roleApi.addRoleMember(IslandSourceId, SenderDodoSourceId, RoleId);
    }

    /**
     * 取消成员身份组
     *
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void removeRole(@NonNull String RoleId) throws IOException {
        bot.getApi().V2.roleApi.removeRoleMember(IslandSourceId, SenderDodoSourceId, RoleId);
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
