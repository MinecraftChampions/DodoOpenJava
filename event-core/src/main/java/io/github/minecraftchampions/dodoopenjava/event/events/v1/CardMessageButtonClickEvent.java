package io.github.minecraftchampions.dodoopenjava.event.events.v1;

import org.json.JSONObject;
import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;

import javax.annotation.Nonnull;

/**
 * ��Ƭ��Ϣ��ť�¼�
 * @author qscbm187531
 */
public class CardMessageButtonClickEvent extends Event {
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

    public String channelId;

    public String dodoId;

    public String messageId;

    public JSONObject personal;

    public String senderNickName;

    public String senderAvatarUrl;

    public Integer senderIntSex;

    public String senderSex;

    public JSONObject member;

    public String memberNickName;

    public String memberJoinTime;

    public JSONObject jsonObject;

    public String jsonString;

    public String interactCustomId;

    public String value;

    public CardMessageButtonClickEvent(JSONObject json) {
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandId");
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.dodoId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoId");
        this.messageId = json.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.senderNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.senderAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.senderSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.senderIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.interactCustomId = json.getJSONObject("data").getJSONObject("eventBody").getString("interactCustomId");
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
     * ת�� ΪInt�������͵� ��Ϣ���͹ؼ��� Ϊ String ����
     * @param type ��Ϣ����
     * @return ��Ϣ����
     */
    public String IntMessageTypeToMessageType(Integer type) {
        return switch (type) {
            case 1 -> "������Ϣ";
            case 2 -> "ͼƬ��Ϣ";
            case 3 -> "��Ƶ��Ϣ";
            case 4 -> "������Ϣ";
            case 5 -> "�ļ���Ϣ";
            case 6 -> "��Ƭ��Ϣ";
            default -> "δ֪��Ϣ";
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
     * ��ȡ��ϢID
     * @return ��ϢID
     */
    public String getMessageId() {
        return this.messageId;
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
    public String getSenderNickName() {
        return this.senderNickName;
    }

    /**
     * ��ȡ������ͷ��URL
     * @return ͷ��url
     */
    public String getSenderAvatarUrl() {
        return this.senderAvatarUrl;
    }

    /**
     * ��ȡ�Ա�Int���ͣ�
     * @return �Ա�
     */
    public Integer getSenderIntSex() {
        return this.senderIntSex;
    }

    /**
     * ��ȡ�Ա�String���ͣ�
     * @return �Ա�
     */
    public String getSenderSex() {
        return this.senderSex;
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
     * ��ȡ���ص�ֵ
     * @return ֵ
     */
    public String getValue() {
        return this.value;
    }

    /**
     * ��ȡ�Զ���ID
     * @return ID
     */
    public String getInteractCustomId() {
        return this.interactCustomId;
    }

    public String getJsonString() {
        return jsonString;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
