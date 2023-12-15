package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelarticle;

import lombok.Getter;
import org.json.JSONObject;

/**
 * 帖子评论回复事件
 */
@Getter
public class ChannelArticleCommentEvent extends ChannelArticleEvent {
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
     * 获取DodoSourceId
     */
    public String dodoSourceId;

    /**
     * -- GETTER --
     * 获取频道ID
     */
    public String channelId;

    /**
     * -- GETTER --
     * 获取卡片消息JSON对象
     */
    public JSONObject jsonObject;

    /**
     * -- GETTER --
     * 获取卡片消息JSON字符串
     */
    public String jsonString;

    /**
     * -- GETTER --
     * 获取成员Object
     */
    public JSONObject personal;

    /**
     * -- GETTER --
     * 获取发送者名字
     */
    public String userNickName;

    /**
     * -- GETTER --
     * 获取发送者头像URL
     */
    public String userAvatarUrl;

    /**
     * -- GETTER --
     * 获取性别（Int类型）
     */
    public Integer userIntSex;

    /**
     * -- GETTER --
     * 获取性别（String类型）
     */
    public String userSex;

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
     * 获取帖子ID
     */
    public String articleId;

    /**
     * -- GETTER --
     * 获取内容
     */
    public String content;

    /**
     * -- GETTER --
     * 获取评论iD
     */
    public String commentId;

    public ChannelArticleCommentEvent(JSONObject json) {
        super(true);
        this.commentId = json.getJSONObject("data").getJSONObject("eventBody").getString("commentId");
        this.articleId = json.getJSONObject("data").getJSONObject("eventBody").getString("articleId");
        this.content = json.getJSONObject("data").getJSONObject("eventBody").getString("content");
        this.jsonObject = json;
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.userNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.userAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.userSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.userIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        eventType = ChannelArticleCommentEvent.class;
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


}
