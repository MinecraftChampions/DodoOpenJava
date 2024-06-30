package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage;

import lombok.Getter;
import org.json.JSONObject;

/**
 * 卡片消息按钮事件
 *
 * @author qscbm187531
 */
@Getter
public class CardMessageButtonClickEvent extends AbstractChannelMessageEvent {
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
     * 获取自定义ID
     */
    protected String interactCustomId;

    /**
     * -- GETTER --
     * 获取返回的值
     */
    protected String value;

    public CardMessageButtonClickEvent(JSONObject json) {
        super(true);
        eventType = CardMessageButtonClickEvent.class;
        this.jsonObject = json;
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
        this.interactCustomId = json.getJSONObject("data").getJSONObject("eventBody").getString("interactCustomId");
    }
}