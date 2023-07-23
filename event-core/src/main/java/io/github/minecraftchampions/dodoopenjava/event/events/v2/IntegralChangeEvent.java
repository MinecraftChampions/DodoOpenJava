package io.github.minecraftchampions.dodoopenjava.event.events.v2;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * 积分变更事件
 */
public class IntegralChangeEvent extends Event {
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

    public JSONObject jsonObject;

    public String jsonString;

    public int operateType;

    public long integral;

    public IntegralChangeEvent(JSONObject json) {
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.integral = json.getJSONObject("data").getJSONObject("eventBody").getLong("integral");
        this.operateType = json.getJSONObject("data").getJSONObject("eventBody").getInt("operateType");
    }

    /**
     * 获取场景类型
     * 1：签到，2：邀请，3：转账，4：购买商品，5：管理积分，6：退群
     * @return 场景类型
     */
    public int getOperateType() {
        return operateType;
    }

    /**
     * 获取积分数量
     * 正数时表示积分增加，负数时表示积分减少
     * @return 积分
     */
    public long getIntegral() {
        return integral;
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
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * 获取DodoId
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
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
