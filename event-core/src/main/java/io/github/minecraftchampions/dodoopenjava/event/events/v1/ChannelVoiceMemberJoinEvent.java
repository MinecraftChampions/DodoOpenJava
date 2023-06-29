package io.github.minecraftchampions.dodoopenjava.event.events.v1;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * ��Ա��������Ƶ���¼�
 */
public class ChannelVoiceMemberJoinEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Integer timestamp;

    public String eventId;

    public String islandId;

    public String dodoId;

    public String channelId;

    public String modifyTime;

    public JSONObject jsonObject;

    public String jsonString;

    public JSONObject personal;

    public String userNickName;

    public String userAvatarUrl;

    public Integer userIntSex;

    public String userSex;

    public JSONObject member;

    public String memberNickName;

    public String memberJoinTime;

    public ChannelVoiceMemberJoinEvent(JSONObject json) {
        this.jsonObject = json;
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandId");
        this.dodoId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoId");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.userNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.userAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.userSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.userIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
    }

    /**
     * ת�� ΪInt�������͵� �Ա�ؼ��� Ϊ String ����
     * @param IntSex �Ա�
     * @return �Ա�
     */
    public String IntSexToSex(Integer IntSex) {
        return switch (IntSex) {
            case 0 -> "Ů";
            case 1 -> "��";
            default -> "����";
        };
    }

    /**
     * ��ȡʱ���
     * @return ʱ���
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * ��ȡ�¼�ID
     * @return �¼�ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * ��ȡȺ��
     * @return Ⱥ��
     */
    public String getIslandId() {
        return this.islandId;
    }

    /**
     * ��ȡƵ��ID
     * @return Ƶ��ID
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * ��ȡDodoId
     * @return DodoId
     */
    public String getDodoId() {
        return this.dodoId;
    }


    /**
     * ��ȡ��ԱObject
     * @return ��ȡ��Ա�� JsonObject
     */
    public JSONObject getPersonal() {
        return this.personal;
    }


    /**
     * ��ȡ����������
     * @return ����
     */
    public String getUserNickName() {
        return this.userNickName;
    }

    /**
     * ��ȡ������ͷ��URL
     * @return ͷ��url
     */
    public String getUserAvatarUrl() {
        return this.userAvatarUrl;
    }

    /**
     * ��ȡ�Ա�Int���ͣ�
     * @return �Ա�
     */
    public Integer getUserIntSex() {
        return this.userIntSex;
    }

    /**
     * ��ȡ�Ա�String���ͣ�
     * @return �Ա�
     */
    public String getUserSex() {
        return this.userSex;
    }


    /**
     * ��ȡ��ԱObject
     * @return ��Ա JsonObject
     */
    public JSONObject getMember() {
        return this.member;
    }

    /**
     * ��ȡ��Ա��ʾ��
     * @return ����
     */
    public String getMemberNickName() {
        return this.memberNickName;
    }

    /**
     * ��ȡ��Ա����ʱ��
     * @return ����ʱ��
     */
    public String getMemberJoinTime() {
        return this.memberJoinTime;
    }

    /**
     * ��ȡ��Ƭ��ϢJSON�ַ���
     */
    public String getJsonString() {
        return this.jsonString;
    }

    /**
     * ��ȡ��Ƭ��ϢJSON����
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }
}
