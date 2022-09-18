package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Api.ChannelTextApi;
import io.github.mcchampions.DodoOpenJava.Api.MemberApi;
import io.github.mcchampions.DodoOpenJava.Api.PersonalApi;
import io.github.mcchampions.DodoOpenJava.Api.RoleApi;
import io.github.mcchampions.DodoOpenJava.Permissions.User;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 命令发送者
 */
public class CommandSender{
    public String SenderDodoId;

    public String ChannelId;

    public String IslandId;

    public String AvatarUrl;

    public String SenderNickName;

    public String SenderName;

    public String JoinTime;

    public String MessageId;

    /**
     * 获取DodoID
     * @return DodoID
     */
    public String getSenderDodoId() {
        return this.SenderDodoId;
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
    public String getIslandId() {
        return this.IslandId;
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
        this.SenderDodoId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("dodoId");
        this.ChannelId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.IslandId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("islandId");
        this.MessageId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
    }

    /**
     * 回复发送者发送的消息
     * @param Message 消息
     * @throws IOException 发送失败时抛出
     */
    public void referencedMessage(String Message) throws IOException {
        ChannelTextApi.referencedMessage(Command.SenderAuthorization, ChannelId, Message, MessageId);
    }

    /**
     * 编辑成员群昵称
     * @param NickName 群昵称
     * @throws IOException 编辑失败后抛出
     */
    public void editSenderNickName(String NickName) throws IOException {
        MemberApi.editMemberNickName(Command.SenderAuthorization,IslandId,SenderDodoId,NickName);
    }

    /**
     * 禁言成员
     * @param Time 时间（秒为单位）
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time) throws IOException {
        MemberApi.addMemberMute(Command.SenderAuthorization, IslandId, SenderDodoId,Time);
    }

    /**
     * 禁言成员
     * @param Time 时间（秒为单位）
     * @param reason 禁言理由
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time, String reason) throws IOException {
        MemberApi.addMemberReasonrMute(Command.SenderAuthorization, IslandId, SenderDodoId,Time,reason);
    }

    /**
     * 取消禁言成员
     * @throws IOException 失败后抛出
     */
    public void removeMuteSender() throws IOException {
        MemberApi.removeMemberMute(Command.SenderAuthorization,IslandId,SenderDodoId);
    }

    /**
     * 封禁成员
     * @param reason 封禁理由
     * @throws IOException 失败后抛出
     */
    public void banSender(String reason) throws IOException {
        MemberApi.addMemberReasonBan(Command.SenderAuthorization, IslandId, SenderDodoId,reason);
    }

    /**
     * 封禁成员
     * @throws IOException 失败后抛出
     */
    public void banSender() throws IOException {
        MemberApi.addMemberBan(Command.SenderAuthorization, IslandId, SenderDodoId);
    }

    /**
     * 取消封禁成员
     * @throws IOException 失败后抛出
     */
    public void removeBanSender() throws IOException {
        MemberApi.removeMemberBan(Command.SenderAuthorization,IslandId,SenderDodoId);
    }

    /**
     * 发送私信
     * @param Message 私信
     * @throws IOException 失败后抛出
     */
    public void sendPrivateMessage(String Message) throws IOException {
        PersonalApi.sendPersonalMessage(Command.SenderAuthorization,SenderDodoId,MessageId);
    }

    /**
     * 赋予成员身份组
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void giveRole(String RoleId) throws IOException {
        RoleApi.addRoleMember(Command.SenderAuthorization,IslandId,SenderDodoId,RoleId);
    }

    /**
     * 取消成员身份组
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void removeRole(String RoleId) throws IOException {
        RoleApi.removeRoleMember(Command.SenderAuthorization,IslandId,SenderDodoId,RoleId);
    }

    /**
     * 是否拥有权限
     * @param permission 权限
     * @return true有，false失败
     */
    public Boolean hasPermission(String permission) {
        return User.hasPerm(getSenderDodoId(), permission);
    }
}
