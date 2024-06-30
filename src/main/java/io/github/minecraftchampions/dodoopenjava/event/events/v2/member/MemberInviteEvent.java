package io.github.minecraftchampions.dodoopenjava.event.events.v2.member;

import lombok.Getter;
import org.json.JSONObject;

/**
 * 成员邀请事件
 *
 * @author qscbm187531
 */
@Getter
public class MemberInviteEvent extends AbstractMemberEvent {

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
     * 获取变动时间
     */
    protected String modifyTime;

    /**
     * -- GETTER --
     * 获取JSONObject
     */
    protected JSONObject jsonObject;

    /**
     * -- GETTER --
     * 获取JsonString
     */
    protected String jsonString;

    /**
     * -- GETTER --
     * 获取邀请人群昵称
     */
    protected String dodoIslandNickName;

    /**
     * -- GETTER --
     * 获取被邀请人DoDoID
     */
    protected String toDodoSourceId;

    /**
     * -- GETTER --
     * 获取被邀请人群昵称
     */
    protected String toDodoIslandNickName;

    public MemberInviteEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getLong("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.dodoIslandNickName = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoIslandNickName");
        this.toDodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("toDodoSourceId");
        this.toDodoIslandNickName = json.getJSONObject("data").getJSONObject("eventBody").getString("toDodoIslandNickName");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        eventType = MemberInviteEvent.class;
    }
}