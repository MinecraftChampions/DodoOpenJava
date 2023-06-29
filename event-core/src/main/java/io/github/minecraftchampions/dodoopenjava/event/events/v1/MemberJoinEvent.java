package io.github.minecraftchampions.dodoopenjava.event.events.v1;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * 成员加入事件
 */
public class MemberJoinEvent extends Event {
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

    public String dodoId;

    public String modifyTime;

    public JSONObject jsonObject;

    public String jsonString;

    public MemberJoinEvent(JSONObject json) {
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandId");
        this.dodoId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoId");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
    }

    /**
     * 获取时间戳
     * @return 返回时间戳
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
     * 获取DodoId
     * @return DodoId
     */
    public String getDodoId() {
        return this.dodoId;
    }

    /**
     * 获取变动时间
     * @return 变动时间
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 获取JSONObject
     * @return JSONObject
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * 获取JsonString
     * @return String
     */
    public String getJsonString() {
        return this.jsonString;
    }
}
