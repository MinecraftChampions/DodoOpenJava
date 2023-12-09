package io.github.minecraftchampions.dodoopenjava.event.events.v2.integral;

import lombok.Getter;
import org.json.JSONObject;

/**
 * 积分变更事件
 */
@Getter
public class IntegralChangeEvent extends IntegralEvent {
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
     *  获取DodoId
     *
     */
    public String dodoSourceId;

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
     *  获取场景类型
     *  1：签到，2：邀请，3：转账，4：购买商品，5：管理积分，6：退群
     *
     */
    public int operateType;

    /**
     * -- GETTER --
     *  获取积分数量
     *  正数时表示积分增加，负数时表示积分减少
     *
     */
    public long integral;

    public IntegralChangeEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.integral = json.getJSONObject("data").getJSONObject("eventBody").getLong("integral");
        this.operateType = json.getJSONObject("data").getJSONObject("eventBody").getInt("operateType");
        eventType = IntegralChangeEvent.class;
    }
}
