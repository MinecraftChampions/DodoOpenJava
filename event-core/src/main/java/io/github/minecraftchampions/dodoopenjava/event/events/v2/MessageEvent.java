package io.github.minecraftchampions.dodoopenjava.event.events.v2;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * ��Ϣ�¼�
 */
public class MessageEvent extends Event {
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

    public String islandSourceId;

    public String channelId;

    public String dodoSourceId;

    public String messageId;

    public Integer messageIntType;

    public String messageType;

    public JSONObject personal;

    public String senderNickName;

    public String senderAvatarUrl;

    public Integer senderIntSex;

    public String senderSex;

    public JSONObject member;

    public String memberNickName;

    public String memberJoinTime;

    public JSONObject reference;

    public String referenceMessageId;

    public String referenceDodoSourceId;

    public String referenceNickName;

    public JSONObject messageBody;

    public JSONObject jsonObject;

    public String jsonString;

    public MessageEvent(JSONObject json) {
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.messageId = json.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.senderNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.senderAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.senderSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.senderIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.reference = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference");
        this.referenceMessageId = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("messageId");
        this.referenceDodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("dodoSourceId");
        this.referenceNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("nickName");
        this.messageType = IntMessageTypeToMessageType(json.getJSONObject("data").getJSONObject("eventBody").getInt("messageType"));
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
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * ��ȡƵ��ID
     * @return Ƶ��ID
     */
    public String getChannelId() {
        return this.channelId;
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
     * ��ȡ�ظ� Object��û�о�null
     * @return �ظ��� JSONObject
     */
    public JSONObject getReference() {
        return this.reference;
    }

    /**
     * ��ȡ�ظ�����ϢID��û�о�null
     * @return ��ϢID
     */
    public String getReferenceMessageId() {
        return this.referenceMessageId;
    }

    /**
     * ��ȡ�ظ���DodoSourceId��û�о�null
     * @return DodoSourceId
     */
    public String getReferenceDodoSourceId() {
        return this.referenceDodoSourceId;
    }

    /**
     * ��ȡ�ظ�����Ϣ���֣�û�о�null
     * @return ����
     */
    public String getReferenceNickName() {
        return this.referenceNickName;
    }


    /**
     * ��ȡ��Ϣ Object
     * @return ����
     */
    public JSONObject getMessageBody() {
        return this.messageBody;
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
