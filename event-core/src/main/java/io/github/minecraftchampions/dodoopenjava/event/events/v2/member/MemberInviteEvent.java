package io.github.minecraftchampions.dodoopenjava.event.events.v2.member;

import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import lombok.Getter;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * 成员邀请事件
 */
@Getter
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
     *  获取DodoSourceId
     *
     */
    public String dodoSourceId;

    /**
     * -- GETTER --
     *  获取变动时间
     *
     */
    public String modifyTime;

    /**
     * -- GETTER --
     *  获取JSONObject
     *
     */
    public JSONObject jsonObject;

    /**
     * -- GETTER --
     *  获取JsonString
     *
     */
    public String jsonString;

    /**
     * -- GETTER --
     *  获取邀请人群昵称
     *
     */
    public String dodoIslandNickName;

    /**
     * -- GETTER --
     *  获取被邀请人DoDoID
     *
     */
    public String toDodoSourceId;

    /**
     * -- GETTER --
     *  获取被邀请人群昵称
     *
     */
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

}
