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
     * 获取DodoSourceId
     */
    protected final String dodoSourceId;

    /**
     * -- GETTER --
     * 获取消息ID
     */
    protected final String messageId;

    /**
     * -- GETTER --
     * 获取消息类别（Int类型）
     */
    protected final Integer messageIntType;

    /**
     * -- GETTER --
     * 获取消息类别（String类型）
     */
    protected final String messageType;

    /**
     * -- GETTER --
     * 获取成员Object
     */
    protected final JSONObject personal;

    /**
     * -- GETTER --
     * 获取发送者名字
     */
    protected final String senderNickName;

    /**
     * -- GETTER --
     * 获取发送者头像URL
     */
    protected final String senderAvatarUrl;

    /**
     * -- GETTER --
     * 获取性别（Int类型）
     */
    protected final Integer senderIntSex;

    /**
     * -- GETTER --
     * 获取性别（String类型）
     */
    protected final String senderSex;

    /**
     * -- GETTER --
     * 获取消息 Object
     */
    protected final JSONObject messageBody;

    public PersonalMessageEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getLong("timestamp");
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