package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelarticle;

import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.AbstractChannelMessageEvent;
import lombok.Getter;
import org.json.JSONObject;

/**
 * 帖子发布事件
 *
 * @author qscbm187531
 */
@Getter
public class ChannelArticlePublishEvent extends AbstractChannelArticleEvent {
    /**
     * -- GETTER --
     * 获取群号
     */
    protected String islandSourceId;

    /**
     * -- GETTER --
     * 获取DodoSourceId
     */
    protected String dodoSourceId;

    /**
     * -- GETTER --
     * 获取频道ID
     */
    protected String channelId;



    /**
     * -- GETTER --
     * 获取成员Object
     */
    protected JSONObject personal;

    /**
     * -- GETTER --
     * 获取发送者名字
     */
    protected String userNickName;

    /**
     * -- GETTER --
     * 获取发送者头像URL
     */
    protected String userAvatarUrl;

    /**
     * -- GETTER --
     * 获取性别（Int类型）
     */
    protected Integer userIntSex;

    /**
     * -- GETTER --
     * 获取性别（String类型）
     */
    protected String userSex;

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
     * 获取帖子ID
     */
    protected String articleId;

    /**
     * -- GETTER --
     * 获取标题
     */
    protected String title;

    /**
     * -- GETTER --
     * 获取内容
     */
    protected String content;

    public ChannelArticlePublishEvent(JSONObject json) {
        super(true);
        this.articleId = json.getJSONObject("data").getJSONObject("eventBody").getString("articleId");
        this.title = json.getJSONObject("data").getJSONObject("eventBody").getString("title");
        this.content = json.getJSONObject("data").getJSONObject("eventBody").getString("content");
        this.jsonObject = json;
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getLong("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.userNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.userAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.userSex = AbstractChannelMessageEvent.intSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.userIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        eventType = ChannelArticlePublishEvent.class;
    }
}