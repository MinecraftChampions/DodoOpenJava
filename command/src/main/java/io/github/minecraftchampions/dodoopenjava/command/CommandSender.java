package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi;
import io.github.minecraftchampions.dodoopenjava.permissions.UserManager;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 命令发送者
 * @author qscbm187531
 */
public class CommandSender{
    public String SenderDodoSourceId;

    public String ChannelId;

    public String IslandSourceId;

    public String AvatarUrl;

    public String SenderNickName;

    public String SenderName;

    public String JoinTime;

    public String MessageId;

    /**
     * 获取DodoID
     * @return DodoID
     */
    public String getSenderDodoSourceId() {
        return this.SenderDodoSourceId;
    }
    /**
     * 获取频道ID
     * @return 频道ID
     */
    public String getChannelId() {
        return this.ChannelId;
    }

    /**
     * 获取群号
     * @return 群号
     */
    public String getIslandSourceId() {
        return this.IslandSourceId;
    }

    /**
     * 获取头像URL
     * @return 头像URL
     */
    public String getAvatarUrl() {
        return this.AvatarUrl;
    }

    /**
     * 获取发送者群昵称
     * @return 群昵称
     */
    public String getSenderNickName() {
        return this.SenderNickName;
    }

    /**
     * 获取发送者原本名称
     * @return 原名
     */
    public String getSenderName() {
        return this.SenderName;
    }

    /**
     * 获取加入时间
     * @return 加入时间
     */
    public String getJoinTime() {
        return this.JoinTime;
    }

    /**
     * 初始化发送者这个类型
     * @param jsontext JSONText
     */
    public void InitSender(JSONObject jsontext) {
        this.SenderNickName = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.SenderName = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.JoinTime = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.AvatarUrl = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.SenderDodoSourceId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.ChannelId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.IslandSourceId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.MessageId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
    }

    /**
     * 回复发送者发送的消息
     * @param Message 消息
     * @throws IOException 发送失败时抛出
     */
    public void referencedMessage(String Message) throws IOException {
        ChannelMessageApi.referencedMessage(Command.Authorization, ChannelId, Message, MessageId);
    }

    /**
     * 编辑成员群昵称
     * @param NickName 群昵称
     * @throws IOException 编辑失败后抛出
     */
    public void editSenderNickName(String NickName) throws IOException {
        MemberApi.editMemberNickName(Command.Authorization,IslandSourceId,SenderDodoSourceId,NickName);
    }

    /**
     * 禁言成员
     * @param Time 时间（秒为单位）
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time) throws IOException {
        MemberApi.addMemberMute(Command.Authorization, IslandSourceId, SenderDodoSourceId,Time);
    }

    /**
     * 禁言成员
     * @param Time 时间（秒为单位）
     * @param reason 禁言理由
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time, String reason) throws IOException {
        MemberApi.addMemberReasonrMute(Command.Authorization, IslandSourceId, SenderDodoSourceId,Time,reason);
    }

    /**
     * 取消禁言成员
     * @throws IOException 失败后抛出
     */
    public void removeMuteSender() throws IOException {
        MemberApi.removeMemberMute(Command.Authorization,IslandSourceId,SenderDodoSourceId);
    }

    /**
     * 封禁成员
     * @param reason 封禁理由
     * @throws IOException 失败后抛出
     */
    public void banSender(String reason) throws IOException {
        MemberApi.addMemberReasonBan(Command.Authorization, IslandSourceId, SenderDodoSourceId,reason);
    }

    /**
     * 封禁成员
     * @throws IOException 失败后抛出
     */
    public void banSender() throws IOException {
        MemberApi.addMemberBan(Command.Authorization, IslandSourceId, SenderDodoSourceId);
    }

    /**
     * 取消封禁成员
     * @throws IOException 失败后抛出
     */
    public void removeBanSender() throws IOException {
        MemberApi.removeMemberBan(Command.Authorization,IslandSourceId,SenderDodoSourceId);
    }

    /**
     * 发送私信
     * @param Message 私信
     * @throws IOException 失败后抛出
     */
    public void sendPrivateMessage(String Message) throws IOException {
        PersonalApi.sendPersonalMessage(Command.Authorization,SenderDodoSourceId,MessageId);
    }

    /**
     * 赋予成员身份组
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void giveRole(String RoleId) throws IOException {
        RoleApi.addRoleMember(Command.Authorization,IslandSourceId,SenderDodoSourceId,RoleId);
    }

    /**
     * 取消成员身份组
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void removeRole(String RoleId) throws IOException {
        RoleApi.removeRoleMember(Command.Authorization,IslandSourceId,SenderDodoSourceId,RoleId);
    }

    /**
     * 是否拥有权限
     * @param permission 权限
     * @return true有，false失败
     */
    public Boolean hasPermission(String permission) {
        return UserManager.hasPerm(UserManager.getUser(getSenderDodoSourceId()), permission);
    }
}
