package io.github.minecraftchampions.dodoopenjava.event.events.v2.personal;

import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.MessageEvent;
import lombok.Getter;
import org.json.JSONObject;

/**
 * 私信事件
 *
 * @author qscbm187531
 */

@Getter
public class PersonalMessageEvent extends AbstractPersonalEvent {
    /**
     * -- GETTER --
     * 获取时间戳
     */
    public Integer timestamp;

    /**
     * -- GETTER --
     * 获取事件ID
     */
    public String eventId;

    /**
     * -- GETTER --
     * 获取DodoSourceId
     */
    public String dodoSourceId;

    /**
     * -- GETTER --
     * 获取消息ID
     */
    public String messageId;

    /**
     * -- GETTER --
     * 获取消息类别（Int类型）
     */
    public Integer messageIntType;

    /**
     * -- GETTER --
     * 获取消息类别（String类型）
     */
    public String messageType;

    /**
     * -- GETTER --
     * 获取成员Object
     */
    public JSONObject personal;

    /**
     * -- GETTER --
     * 获取发送者名字
     */
    public String senderNickName;

    /**
     * -- GETTER --
     * 获取发送者头像URL
     */
    public String senderAvatarUrl;

    /**
     * -- GETTER --
     * 获取性别（Int类型）
     */
    public Integer senderIntSex;

    /**
     * -- GETTER --
     * 获取性别（String类型）
     */
    public String senderSex;

    /**
     * -- GETTER --
     * 获取消息 Object
     */
    public JSONObject messageBody;

    public JSONObject jsonObject;

    public String jsonString;

    public PersonalMessageEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.messageId = json.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.senderNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.senderAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.senderSex = MessageEvent.intSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.senderIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.messageType = MessageEvent.intMessageTypeToMessageType(json.getJSONObject("data").getJSONObject("eventBody").getInt("messageType"));
        this.messageIntType = json.getJSONObject("data").getJSONObject("eventBody").getInt("messageType");
        this.messageBody = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("messageBody");
        eventType = PersonalMessageEvent.class;
    }
}