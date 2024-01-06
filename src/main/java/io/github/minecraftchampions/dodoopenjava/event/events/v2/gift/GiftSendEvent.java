package io.github.minecraftchampions.dodoopenjava.event.events.v2.gift;

import lombok.Getter;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * 赠礼成功事件
 */
@Getter
public class GiftSendEvent extends AbstractGiftEvent {
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
     * 获取来源群ID
     */
    public String islandSourceId;

    /**
     * -- GETTER --
     * 获取来源频道ID
     */
    public String channelId;

    /**
     * -- GETTER --
     * 获取订单号
     */
    public String orderNo;

    /**
     * -- GETTER --
     * 获取内容类型，1：消息，2：帖子
     */
    public Integer targetType;

    /**
     * -- GETTER --
     * 获取内容ID
     */
    public String targetId;

    /**
     * -- GETTER --
     * 获取礼物总价值（铃钱）
     */
    public BigDecimal totalAmount;

    /**
     * -- GETTER --
     * 获取礼物信息Object
     */
    public JSONObject gift;

    /**
     * -- GETTER --
     * 获取群分成（百分比）
     */
    public BigDecimal islandRatio;

    /**
     * -- GETTER --
     * 获取群收入（里程）
     */
    public BigDecimal islandIncome;

    /**
     * -- GETTER --
     * 获取赠礼人DoDoID
     */
    public String dodoSourceId;

    /**
     * -- GETTER --
     * 获取赠礼人群昵称
     */
    public String dodoIslandNickName;

    /**
     * -- GETTER --
     * 获取被赠礼人DoDoID
     */
    public String toDodoSourceId;

    /**
     * -- GETTER --
     * 获取被赠礼人群昵称
     */
    public String toDodoIslandNickName;

    /**
     * -- GETTER --
     * 获取被赠礼人分成（百分比）
     */
    public BigDecimal toDodoRatio;

    /**
     * -- GETTER --
     * 获取被赠礼人收入（里程）
     */
    public BigDecimal toDodoIncome;

    /**
     * -- GETTER --
     * 获取事件内容
     */
    public JSONObject jsonObject;

    /**
     * -- GETTER --
     * 获取事件内容
     */
    public String jsonString;

    public GiftSendEvent(JSONObject json) {
        super(true);
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
        eventType = GiftSendEvent.class;
    }
}
