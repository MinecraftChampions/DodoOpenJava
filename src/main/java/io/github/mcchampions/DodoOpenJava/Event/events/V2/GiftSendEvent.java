package io.github.mcchampions.DodoOpenJava.Event.events.V2;

import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * 赠礼成功事件
 */
public class GiftSendEvent extends Event {
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

    public String channelId;

    public String orderNo;

    public Integer targetType;

    public String targetId;

    public BigDecimal totalAmount;

    public JSONObject gift;

    public BigDecimal islandRatio;

    public BigDecimal islandIncome;

    public String dodoSourceId;

    public String dodoIslandNickName;

    public String toDodoSourceId;

    public String toDodoIslandNickName;

    public BigDecimal toDodoRatio;

    public BigDecimal toDodoIncome;

    public JSONObject jsonObject;

    public String jsonString;

    public GiftSendEvent(JSONObject json) {
        this.jsonObject = json;
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.orderNo = json.getJSONObject("data").getJSONObject("eventBody").getString("orderNo");
        this.dodoIslandNickName = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoIslandNickName");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.totalAmount = json.getJSONObject("data").getJSONObject("eventBody").getBigDecimal("totalAmount");
        this.targetType = json.getJSONObject("data").getJSONObject("eventBody").getInt("targetType");
        this.gift = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("gift");
        this.islandIncome = json.getJSONObject("data").getJSONObject("eventBody").getBigDecimal("sex");
        this.islandRatio = json.getJSONObject("data").getJSONObject("eventBody").getBigDecimal("islandRatio");
        this.targetId = json.getJSONObject("data").getJSONObject("eventBody").getString("targetId");
        this.toDodoIncome = json.getJSONObject("data").getJSONObject("eventBody").getBigDecimal("toDodoIncome");
        this.toDodoRatio = json.getJSONObject("data").getJSONObject("eventBody").getBigDecimal("toDodoRatio");
        this.toDodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("toDodoSourceId");
        this.toDodoIslandNickName = json.getJSONObject("data").getJSONObject("eventBody").getString("toDodoIslandNickName");
    }

    /**
     * 获取订单号
     * @return 订单号
     */
    public String getOrderNo() {
        return this.orderNo;
    }

    /**
     * 获取来源频道ID
     * @return 来源频道ID
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * 获取礼物总价值（铃钱）
     * @return 礼物总价值（铃钱）
     */
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * 获取被赠礼人DoDoID
     * @return 被赠礼人DoDoID
     */
    public String getToDodoSourceId() {
        return this.toDodoSourceId;
    }

    /**
     * 获取被赠礼人分成（百分比）
     * @return 被赠礼人分成（百分比）
     */
    public BigDecimal getToDodoRatio() {
        return this.toDodoRatio;
    }

    /**
     * 获取被赠礼人群昵称
     * @return 被赠礼人群昵称
     */
    public String getToDodoIslandNickName() {
        return this.toDodoIslandNickName;
    }

    /**
     * 获取被赠礼人收入（里程）
     * @return 被赠礼人收入（里程）
     */
    public BigDecimal getToDodoIncome() {
        return this.toDodoIncome;
    }

    /**
     * 获取时间戳
     * @return 时间戳
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * 获取内容类型，1：消息，2：帖子
     * @return 内容类型，1：消息，2：帖子
     */
    public Integer getTargetType() {
        return this.targetType;
    }

    /**
     * 获取内容ID
     * @return 内容ID
     */
    public String getTargetId() {
        return this.targetId;
    }

    /**
     * 获取事件内容
     * @return 事件内容
     */
    public String getJsonString() {
        return this.jsonString;
    }

    /**
     * 获取事件内容
     * @return 事件内容
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * 获取来源群ID
     * @return 来源群ID
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * 获取群分成（百分比）
     * @return 群分成（百分比）
     */
    public BigDecimal getIslandRatio() {
        return this.islandRatio;
    }

    /**
     * 获取群收入（里程）
     * @return 群收入（里程）
     */
    public BigDecimal getIslandIncome() {
        return this.islandIncome;
    }

    /**
     * 获取礼物信息Object
     * @return 礼物信息Object
     */
    public JSONObject getGift() {
        return this.gift;
    }

    /**
     * 获取事件ID
     * @return 事件ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 获取赠礼人DoDoID
     * @return 赠礼人DoDoID
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * 获取赠礼人群昵称
     * @return 赠礼人群昵称
     */
    public String getDodoIslandNickName() {
        return this.dodoIslandNickName;
    }
}
