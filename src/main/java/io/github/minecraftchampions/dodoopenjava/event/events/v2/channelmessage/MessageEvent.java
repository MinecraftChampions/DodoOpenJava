package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage;

import lombok.Getter;
import org.json.JSONObject;

/**
 * 消息事件
 *
 * @author qscbm187531
 */
@Getter
public class MessageEvent extends AbstractChannelMessageEvent {

    /**
     * -- GETTER --
     * 获取群号
     */
    protected String islandSourceId;

    /**
     * -- GETTER --
     * 获取频道ID
     */
    protected String channelId;

    /**
     * -- GETTER --
     * 获取DodoSourceId
     */
    protected String dodoSourceId;

    /**
     * -- GETTER --
     * 获取消息ID
     */
    protected String messageId;

    /**
     * -- GETTER --
     * 获取消息类别（Int类型）
     */
    protected Integer messageIntType;

    /**
     * -- GETTER --
     * 获取消息类别（String类型）
     */
    protected String messageType;

    /**
     * -- GETTER --
     * 获取成员Object
     */
    protected JSONObject personal;

    /**
     * -- GETTER --
     * 获取发送者名字
     */
    protected String senderNickName;

    /**
     * -- GETTER --
     * 获取发送者头像URL
     */
    protected String senderAvatarUrl;

    /**
     * -- GETTER --
     * 获取性别（Int类型）
     */
    protected Integer senderIntSex;

    /**
     * -- GETTER --
     * 获取性别（String类型）
     */
    protected String senderSex;

    /**
     * -- GETTER --
     * 获取成员Object
     */
    protected JSONObject member;

    /**
     * -- GETTER --
     * 获取成员显示名
     */
    protected String memberNickName;

    /**
     * -- GETTER --
     * 获取成员加入时间
     */
    protected String memberJoinTime;

    /**
     * -- GETTER --
     * 获取回复 Object，没有就null
     */
    protected JSONObject reference;

    /**
     * -- GETTER --
     * 获取回复的消息ID，没有就null
     */
    protected String referenceMessageId;

    /**
     * -- GETTER --
     * 获取回复的DodoSourceId，没有就null
     */
    protected String referenceDodoSourceId;

    /**
     * -- GETTER --
     * 获取回复的消息名字，没有就null
     */
    protected String referenceNickName;

    /**
     * -- GETTER --
     * 获取消息 Object
     */
    protected JSONObject messageBody;



    public MessageEvent(JSONObject json) {
        super(true);
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getLong("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.messageId = json.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.senderNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.senderAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.senderSex = intSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.senderIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        JSONObject referenceJsonObject = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference");
        if (referenceJsonObject != null) {
            this.referenceMessageId = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("messageId");
            this.referenceDodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("dodoSourceId");
            this.reference = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference");
            this.referenceNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("nickName");
        }
        this.messageType = intMessageTypeToMessageType(json.getJSONObject("data").getJSONObject("eventBody").getInt("messageType"));
        this.messageIntType = json.getJSONObject("data").getJSONObject("eventBody").getInt("messageType");
        this.messageBody = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("messageBody");
        eventType = MessageEvent.class;
    }
}