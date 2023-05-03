package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.PersonalApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi;
import io.github.minecraftchampions.dodoopenjava.permissions.UserManager;
import org.json.JSONObject;

import java.io.IOException;

/**
 * �������
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
     * ��ȡDodoID
     * @return DodoID
     */
    public String getSenderDodoSourceId() {
        return this.SenderDodoSourceId;
    }
    /**
     * ��ȡƵ��ID
     * @return Ƶ��ID
     */
    public String getChannelId() {
        return this.ChannelId;
    }

    /**
     * ��ȡȺ��
     * @return Ⱥ��
     */
    public String getIslandSourceId() {
        return this.IslandSourceId;
    }

    /**
     * ��ȡͷ��URL
     * @return ͷ��URL
     */
    public String getAvatarUrl() {
        return this.AvatarUrl;
    }

    /**
     * ��ȡ������Ⱥ�ǳ�
     * @return Ⱥ�ǳ�
     */
    public String getSenderNickName() {
        return this.SenderNickName;
    }

    /**
     * ��ȡ������ԭ������
     * @return ԭ��
     */
    public String getSenderName() {
        return this.SenderName;
    }

    /**
     * ��ȡ����ʱ��
     * @return ����ʱ��
     */
    public String getJoinTime() {
        return this.JoinTime;
    }

    /**
     * ��ʼ���������������
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
     * �ظ������߷��͵���Ϣ
     * @param Message ��Ϣ
     * @throws IOException ����ʧ��ʱ�׳�
     */
    public void referencedMessage(String Message) throws IOException {
        ChannelMessageApi.referencedMessage(Command.Authorization, ChannelId, Message, MessageId);
    }

    /**
     * �༭��ԱȺ�ǳ�
     * @param NickName Ⱥ�ǳ�
     * @throws IOException �༭ʧ�ܺ��׳�
     */
    public void editSenderNickName(String NickName) throws IOException {
        MemberApi.editMemberNickName(Command.Authorization,IslandSourceId,SenderDodoSourceId,NickName);
    }

    /**
     * ���Գ�Ա
     * @param Time ʱ�䣨��Ϊ��λ��
     * @throws IOException ʧ�ܺ��׳�
     */
    public void muteSender(int Time) throws IOException {
        MemberApi.addMemberMute(Command.Authorization, IslandSourceId, SenderDodoSourceId,Time);
    }

    /**
     * ���Գ�Ա
     * @param Time ʱ�䣨��Ϊ��λ��
     * @param reason ��������
     * @throws IOException ʧ�ܺ��׳�
     */
    public void muteSender(int Time, String reason) throws IOException {
        MemberApi.addMemberReasonrMute(Command.Authorization, IslandSourceId, SenderDodoSourceId,Time,reason);
    }

    /**
     * ȡ�����Գ�Ա
     * @throws IOException ʧ�ܺ��׳�
     */
    public void removeMuteSender() throws IOException {
        MemberApi.removeMemberMute(Command.Authorization,IslandSourceId,SenderDodoSourceId);
    }

    /**
     * �����Ա
     * @param reason �������
     * @throws IOException ʧ�ܺ��׳�
     */
    public void banSender(String reason) throws IOException {
        MemberApi.addMemberReasonBan(Command.Authorization, IslandSourceId, SenderDodoSourceId,reason);
    }

    /**
     * �����Ա
     * @throws IOException ʧ�ܺ��׳�
     */
    public void banSender() throws IOException {
        MemberApi.addMemberBan(Command.Authorization, IslandSourceId, SenderDodoSourceId);
    }

    /**
     * ȡ�������Ա
     * @throws IOException ʧ�ܺ��׳�
     */
    public void removeBanSender() throws IOException {
        MemberApi.removeMemberBan(Command.Authorization,IslandSourceId,SenderDodoSourceId);
    }

    /**
     * ����˽��
     * @param Message ˽��
     * @throws IOException ʧ�ܺ��׳�
     */
    public void sendPrivateMessage(String Message) throws IOException {
        PersonalApi.sendPersonalMessage(Command.Authorization,SenderDodoSourceId,MessageId);
    }

    /**
     * �����Ա�����
     * @param RoleId Ȩ����ID
     * @throws IOException ʧ�ܺ��׳�
     */
    public void giveRole(String RoleId) throws IOException {
        RoleApi.addRoleMember(Command.Authorization,IslandSourceId,SenderDodoSourceId,RoleId);
    }

    /**
     * ȡ����Ա�����
     * @param RoleId Ȩ����ID
     * @throws IOException ʧ�ܺ��׳�
     */
    public void removeRole(String RoleId) throws IOException {
        RoleApi.removeRoleMember(Command.Authorization,IslandSourceId,SenderDodoSourceId,RoleId);
    }

    /**
     * �Ƿ�ӵ��Ȩ��
     * @param permission Ȩ��
     * @return true�У�falseʧ��
     */
    public Boolean hasPermission(String permission) {
        return UserManager.hasPerm(UserManager.getUser(getSenderDodoSourceId()), permission);
    }
}
