package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage;

import lombok.Getter;
import org.json.JSONObject;

/**
 * 表情反应事件
 *
 * @author qscbm187531
 */
@Getter
public class MessageReactionEvent extends AbstractChannelMessageEvent {
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
     * 获取群号
     */
    public String islandSourceId;

    /**
     * -- GETTER --
     * 获取频道ID
     */
    public String channelId;

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

    public String reactionType;

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
     * 获取成员Object
     */
    public JSONObject member;

    /**
     * -- GETTER --
     * 获取成员显示名
     */
    public String memberNickName;

    /**
     * -- GETTER --
     * 获取成员加入时间
     */
    public String memberJoinTime;

    /**
     * -- GETTER --
     * 获取表情 Object
     */
    public JSONObject reactionEmoji;

    /**
     * -- GETTER --
     * 获取表情的EmojiId
     */
    public String reactionEmojiId;

    public JSONObject jsonObject;

    public String jsonString;

    public MessageReactionEvent(JSONObject json) {
        super(true);
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
        this.senderSex = intSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.senderIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.reactionEmoji = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reactionEmoji");
        this.reactionEmojiId = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reactionEmoji").getString("id");
        this.reactionType = intReactionTypeToReactionType(json.getJSONObject("data").getJSONObject("eventBody").getInt("reactionType"));
        eventType = MessageReactionEvent.class;
    }

    public static String intReactionTypeToReactionType(Integer type) {
        return switch (type) {
            case 1 -> "新增";
            case 0 -> "删除";
            default -> "位置";
        };
    }
}