package io.github.minecraftchampions.dodoopenjava.event.events.v2.member;

import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * 成员邀请事件
 */
public class MemberInviteEvent extends MemberEvent {
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

    public String islandSourceId;

    public String dodoSourceId;

    public String modifyTime;

    public JSONObject jsonObject;

    public String jsonString;

    public String dodoIslandNickName;

    public String toDodoSourceId;

    public String toDodoIslandNickName;

    public MemberInviteEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.dodoIslandNickName = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoIslandNickName");
        this.toDodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("toDodoSourceId");
        this.toDodoIslandNickName = json.getJSONObject("data").getJSONObject("eventBody").getString("toDodoIslandNickName");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
    }

    /**
     * 获取时间戳
     *
     * @return 返回时间戳
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * 获取事件ID
     *
     * @return 事件ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 获取群号
     *
     * @return 群号
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * 获取DodoSourceId
     *
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * 获取变动时间
     *
     * @return 变动时间
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 获取JSONObject
     *
     * @return JSONObject
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * 获取JsonString
     *
     * @return String
     */
    public String getJsonString() {
        return this.jsonString;
    }

    /**
     * 获取邀请人群昵称
     *
     * @return String
     */
    public String getDodoIslandNickName() {
        return dodoIslandNickName;
    }

    /**
     * 获取被邀请人DoDoID
     *
     * @return String
     */
    public String getToDodoSourceId() {
        return toDodoSourceId;
    }

    /**
     * 获取被邀请人群昵称
     *
     * @return String
     */
    public String getToDodoIslandNickName() {
        return toDodoIslandNickName;
    }
}
