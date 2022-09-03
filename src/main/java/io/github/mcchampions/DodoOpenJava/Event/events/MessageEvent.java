package io.github.mcchampions.DodoOpenJava.Event.events;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;

import javax.annotation.Nonnull;

/**
 * 消息事件
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

    public String islandId;

    public String channelId;

    public String dodoId;

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

    public String referenceDodoId;

    public String referenceNickName;

    public JSONObject messageBody;

    public JSONObject jsonObject;

    public String jsonString;

    public MessageEvent(JSONObject json) {
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInteger("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandId");
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.dodoId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoId");
        this.messageId = json.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.senderNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.senderAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.senderSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInteger("sex"));
        this.senderIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInteger("sex");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.reference = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference");
        this.referenceMessageId = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("messageId");
        this.referenceDodoId = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("dodoId");
        this.referenceNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("reference").getString("nickName");
        this.messageType = IntMessageTypeToMessageType(json.getJSONObject("data").getJSONObject("eventBody").getInteger("messageType"));
        this.messageIntType = json.getJSONObject("data").getJSONObject("eventBody").getInteger("messageType");
        this.messageBody = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("messageBody");
    }

    /**
     * 转换 为Int数据类型的 性别关键字 为 String 类型
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
     * @param type 消息类型
     * @return 消息类型
     */
    public String IntMessageTypeToMessageType(Integer type) {
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

    /**
     * 获取时间戳
     * @return 时间戳
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * 获取事件ID
     * @return 事件ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 获取群号
     * @return 群号
     */
    public String getIslandId() {
        return this.islandId;
    }

    /**
     * 获取频道ID
     * @return 频道ID
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * 获取DodoId
     * @return DodoId
     */
    public String getDodoId() {
        return this.dodoId;
    }

    /**
     * 获取消息ID
     * @return 消息ID
     */
    public String getMessageId() {
        return this.messageId;
    }

    /**
     * 获取消息类别（Int类型）
     * @return 消息类别
     */
    public Integer getMessageIntType() {
        return this.messageIntType;
    }

    /**
     * 获取消息类别（String类型）
     * @return 消息类别
     */
    public String getMessageType() {
        return this.messageType;
    }


    /**
     * 获取成员Object
     * @return 获取成员的 JsonObject
     */
    public JSONObject getPersonal() {
        return this.personal;
    }


    /**
     * 获取发送者名字
     * @return 名字
     */
    public String getSenderNickName() {
        return this.senderNickName;
    }

    /**
     * 获取发送者头像URL
     * @return 头像url
     */
    public String getSenderAvatarUrl() {
        return this.senderAvatarUrl;
    }

    /**
     * 获取性别（Int类型）
     * @return 性别
     */
    public Integer getSenderIntSex() {
        return this.senderIntSex;
    }

    /**
     * 获取性别（String类型）
     * @return 性别
     */
    public String getSenderSex() {
        return this.senderSex;
    }


    /**
     * 获取成员Object
     * @return 成员 JsonObject
     */
    public JSONObject getMember() {
        return this.member;
    }

    /**
     * 获取成员显示名
     * @return 名字
     */
    public String getMemberNickName() {
        return this.memberNickName;
    }

    /**
     * 获取成员加入时间
     * @return 加入时间
     */
    public String getMemberJoinTime() {
        return this.memberJoinTime;
    }

    /**
     * 获取回复 Object，没有就null
     * @return 回复的 JSONObject
     */
    public JSONObject getReference() {
        return this.reference;
    }

    /**
     * 获取回复的消息ID，没有就null
     * @return 消息ID
     */
    public String getReferenceMessageId() {
        return this.referenceMessageId;
    }

    /**
     * 获取回复的DodoId，没有就null
     * @return DodoId
     */
    public String getReferenceDodoId() {
        return this.referenceDodoId;
    }

    /**
     * 获取回复的消息名字，没有就null
     * @return 名字
     */
    public String getReferenceNickName() {
        return this.referenceNickName;
    }


    /**
     * 获取消息 Object
     * @return 对象
     */
    public JSONObject getMessageBody() {
        return this.messageBody;
    }
}
