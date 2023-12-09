package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import lombok.Getter;
import org.json.JSONObject;

/**
 * 表情反应事件
 */
@Getter
public class MessageReactionEvent extends Event {
    /**
     * -- GETTER --
     *  获取时间戳
     *
     */
    public Integer timestamp;

    /**
     * -- GETTER --
     *  获取事件ID
     *
     */
    public String eventId;

    /**
     * -- GETTER --
     *  获取群号
     *
     */
    public String islandSourceId;

    /**
     * -- GETTER --
     *  获取频道ID
     *
     */
    public String channelId;

    /**
     * -- GETTER --
     *  获取DodoSourceId
     *
     */
    public String dodoSourceId;

    /**
     * -- GETTER --
     *  获取消息ID
     *
     */
    public String messageId;

    public String reactionType;

    /**
     * -- GETTER --
     *  获取成员Object
     *
     */
    public JSONObject personal;

    /**
     * -- GETTER --
     *  获取发送者名字
     *
     */
    public String senderNickName;

    /**
     * -- GETTER --
     *  获取发送者头像URL
     *
     */
    public String senderAvatarUrl;

    /**
     * -- GETTER --
     *  获取性别（Int类型）
     *
     */
    public Integer senderIntSex;

    /**
     * -- GETTER --
     *  获取性别（String类型）
     *
     */
    public String senderSex;

    /**
     * -- GETTER --
     *  获取成员Object
     *
     */
    public JSONObject member;

    /**
     * -- GETTER --
     *  获取成员显示名
     *
     */
    public String memberNickName;

    /**
     * -- GETTER --
     *  获取成员加入时间
     *
     */
    public String memberJoinTime;

    /**
     * -- GETTER --
     *  获取表情 Object
     *
     */
    public JSONObject reactionEmoji;

    /**
     * -- GETTER --
     *  获取表情的EmojiId
     *
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
        this.senderSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.senderIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.reactionEmoji = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reactionEmoji");
        this.reactionEmojiId = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reactionEmoji").getString("id");
        this.reactionType = IntReactionTypeToReactionType(json.getJSONObject("data").getJSONObject("eventBody").getInt("reactionType"));
        eventType = MessageReactionEvent.class;
    }

    /**
     * 转换 为Int数据类型的 性别关键字 为 String 类型
     *
     * @param IntSex 性别
     * @return 性别
     */
    public String IntSexToSex(Integer IntSex) {
        return switch (IntSex) {
            case 0 -> "女";
            case 1 -> "男";
            default -> "保密";
        };
    }

    /**
     * 转换 为Int数据类型的 消息类型关键字 为 String 类型
     *
     * @param type 消息类型
     * @return 消息类型
     */
    public String IntReactionTypeToReactionType(Integer type) {
        return switch (type) {
            case 1 -> "文字消息";
            case 2 -> "图片消息";
            case 3 -> "视频消息";
            case 4 -> "分享消息";
            case 5 -> "文件消息";
            case 6 -> "卡片消息";
            default -> "未知消息";
        };
    }


}
