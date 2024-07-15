package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 卡片消息列表回传事件
 *
 * @author qscbm187531
 */
@Getter
public class CardMessageListSubmitEvent extends AbstractChannelMessageEvent {

    /**
     * -- GETTER --
     * 获取群号
     */
    protected final String islandSourceId;

    /**
     * -- GETTER --
     * 获取频道ID
     */
    protected final String channelId;

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
     * 获取成员Object
     */
    protected final JSONObject member;

    /**
     * -- GETTER --
     * 获取成员显示名
     */
    protected final String memberNickName;

    /**
     * -- GETTER --
     * 获取成员加入时间
     */
    protected final String memberJoinTime;

    /**
     * -- GETTER --
     * 获取自定义ID
     */
    protected final String interactCustomId;

    /**
     * -- GETTER --
     * 获取返回的数据列表
     */
    protected final JSONArray list;

    public CardMessageListSubmitEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        eventType = CardMessageListSubmitEvent.class;
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
        this.list = json.getJSONObject("data").getJSONObject("eventBody").getJSONArray("listData");
        this.interactCustomId = json.getJSONObject("data").getJSONObject("eventBody").getString("interactCustomId");
    }
}