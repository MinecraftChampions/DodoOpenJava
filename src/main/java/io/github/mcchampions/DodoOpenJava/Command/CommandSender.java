package io.github.mcchampions.DodoOpenJava.Command;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.api.ChannelTextApi;
import io.github.mcchampions.DodoOpenJava.api.MemberApi;
import io.github.mcchampions.DodoOpenJava.api.PersonalApi;
import io.github.mcchampions.DodoOpenJava.api.RoleApi;

import java.io.IOException;

/**
 * 命令发送者
 */
public class CommandSender {
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
        return SenderDodoId;
    }
    /**
     * 获取频道ID
     * @return 频道ID
     */
    public String getChannelId() {
        return ChannelId;
    }

    /**
     * 获取群号
     * @return 群号
     */
    public String getIslandId() {
        return IslandId;
    }

    /**
     * 获取头像URL
     * @return 头像URL
     */
    public String getAvatarUrl() {
        return AvatarUrl;
    }

    /**
     * 获取发送者群昵称
     * @return 群昵称
     */
    public String getSenderNickName() {
        return SenderNickName;
    }

    /**
     * 获取发送者原本名称
     * @return 原名
     */
    public String getSenderName() {
        return SenderName;
    }

    /**
     * 获取加入时间
     * @return 加入时间
     */
    public String getJoinTime() {
        return JoinTime;
    }

    /**
     * 初始化发送者这个类型
     * @param jsontext JSONText
     */
    public void InitSender(JSONObject jsontext) {
        SenderNickName = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        SenderName = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        JoinTime = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        AvatarUrl = jsontext.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        SenderDodoId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("dodoId");
        ChannelId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        IslandId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("islandId");
        MessageId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
    }

    /**
     * 回复发送者发送的消息
     * @param Message 消息
     * @throws IOException 发送失败时抛出
     */
    public void referencedMessage(String Message) throws IOException {
        ChannelTextApi.referencedMessage(Command.SenderAuthorization, ChannelId, Message, MessageId, false);
    }

    /**
     * 编辑成员群昵称
     * @param NickName 群昵称
     * @throws IOException 编辑失败后抛出
     */
    public void editSenderNickName(String NickName) throws IOException {
        MemberApi.setMemberNickNameEdit(Command.SenderAuthorization,IslandId,SenderDodoId,NickName,false);
    }

    /**
     * 禁言成员
     * @param Time 时间（秒为单位）
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time) throws IOException {
        MemberApi.setMemberMuteAdd(Command.SenderAuthorization, IslandId, SenderDodoId,Time,false);
    }

    /**
     * 禁言成员
     * @param Time 时间（秒为单位）
     * @param reason 禁言理由
     * @throws IOException 失败后抛出
     */
    public void muteSender(int Time, String reason) throws IOException {
        MemberApi.setMemberReasonrMuteAdd(Command.SenderAuthorization, IslandId, SenderDodoId,Time,reason,false);
    }

    /**
     * 取消禁言成员
     * @throws IOException 失败后抛出
     */
    public void removeMuteSender() throws IOException {
        MemberApi.setMemberMuteRemove(Command.SenderAuthorization,IslandId,SenderDodoId,false);
    }

    /**
     * 封禁成员
     * @param reason 封禁理由
     * @throws IOException 失败后抛出
     */
    public void banSender(String reason) throws IOException {
        MemberApi.setMemberReasonBanAdd(Command.SenderAuthorization, IslandId, SenderDodoId,reason,false);
    }

    /**
     * 封禁成员
     * @throws IOException 失败后抛出
     */
    public void banSender() throws IOException {
        MemberApi.setMemberBanAdd(Command.SenderAuthorization, IslandId, SenderDodoId,false);
    }

    /**
     * 取消封禁成员
     * @throws IOException 失败后抛出
     */
    public void removeBanSender() throws IOException {
        MemberApi.setMemberBanRemove(Command.SenderAuthorization,IslandId,SenderDodoId,false);
    }

    /**
     * 发送私信
     * @param Message 私信
     * @throws IOException 失败后抛出
     */
    public void sendPrivateMessage(String Message) throws IOException {
        PersonalApi.setPersonalMessageSend(Command.SenderAuthorization,SenderDodoId,MessageId,false);
    }

    /**
     * 赋予成员身份组
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void giveRole(String RoleId) throws IOException {
        RoleApi.setRoleMemberAdd(Command.SenderAuthorization,IslandId,SenderDodoId,RoleId,false);
    }

    /**
     * 取消成员身份组
     * @param RoleId 权限组ID
     * @throws IOException 失败后抛出
     */
    public void removeRole(String RoleId) throws IOException {
        RoleApi.setRoleMemberRemove(Command.SenderAuthorization,IslandId,SenderDodoId,RoleId,false);
    }
}
