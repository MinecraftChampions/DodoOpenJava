package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice;

import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.AbstractChannelMessageEvent;
import lombok.Getter;
import org.json.JSONObject;

/**
 * 成员加入语音频道事件
 *
 * @author qscbm187531
 */
@Getter
public class ChannelVoiceMemberJoinEvent extends AbstractChannelVoiceEvent {
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

    public String modifyTime;

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

    public ChannelVoiceMemberJoinEvent(JSONObject json) {
        super(true);
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
        this.userSex = AbstractChannelMessageEvent.intSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.userIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        eventType = ChannelVoiceMemberJoinEvent.class;
    }
}
