package io.github.minecraftchampions.dodoopenjava.event.events.v2;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * ˽���¼�
 */

public class PersonalMessageEvent extends Event {
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

    public String dodoSourceId;

    public String messageId;

    public Integer messageIntType;

    public String messageType;

    public JSONObject personal;

    public String senderNickName;

    public String senderAvatarUrl;

    public Integer senderIntSex;

    public String senderSex;

    public JSONObject messageBody;

    public JSONObject jsonObject;

    public String jsonString;

    public PersonalMessageEvent(JSONObject json) {
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.messageId = json.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.senderNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.senderAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.senderSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.senderIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");this.messageType = IntMessageTypeToMessageType(json.getJSONObject("data").getJSONObject("eventBody").getInt("messageType"));
        this.messageIntType = json.getJSONObject("data").getJSONObject("eventBody").getInt("messageType");
        this.messageBody = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("messageBody");
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
     * ��ȡDodoSourceId
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * ��ȡ��ϢID
     * @return ��ϢID
     */
    public String getMessageId() {
        return this.messageId;
    }

    /**
     * ��ȡ��Ϣ���Int���ͣ�
     * @return ��Ϣ���
     */
    public Integer getMessageIntType() {
        return this.messageIntType;
    }

    /**
     * ��ȡ��Ϣ���String���ͣ�
     * @return ��Ϣ���
     */
    public String getMessageType() {
        return this.messageType;
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
     * ��ȡ��Ϣ Object
     * @return ����
     */
    public JSONObject getMessageBody() {
        return this.messageBody;
    }

    public String getJsonString() {
        return jsonString;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
