package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice;

import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.AbstractChannelMessageEvent;
import lombok.Getter;
import org.json.JSONObject;

/**
 * 成员退出语音频道事件
 *
 * @author qscbm187531
 */
@Getter
public class ChannelVoiceMemberLeaveEvent extends AbstractChannelVoiceEvent {

    /**
     * -- GETTER --
     * 获取群号
     */
    protected final String islandSourceId;

    /**
     * -- GETTER --
     * 获取DodoSourceId
     */
    protected final String dodoSourceId;

    /**
     * -- GETTER --
     * 获取频道ID
     */
    protected final String channelId;

    /**
     * -- GETTER --
     * 获取成员加入时间
     */
    protected final String modifyTime;



    /**
     * -- GETTER --
     * 获取成员Object
     */
    protected final JSONObject personal;

    /**
     * -- GETTER --
     * 获取发送者名字
     */
    protected final String userNickName;

    /**
     * -- GETTER --
     * 获取发送者头像URL
     */
    protected final String userAvatarUrl;

    /**
     * -- GETTER --
     * 获取性别（Int类型）
     */
    protected final Integer userIntSex;

    /**
     * -- GETTER --
     * 获取性别（String类型）
     */
    protected final String userSex;

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

    public ChannelVoiceMemberLeaveEvent(JSONObject json) {
        super(true);
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
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        eventType = ChannelVoiceMemberLeaveEvent.class;
    }
}