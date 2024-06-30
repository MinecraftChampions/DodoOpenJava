package io.github.minecraftchampions.dodoopenjava.event.events.v2.gift;

import lombok.Getter;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * 赠礼成功事件
 *
 * @author qscbm187531
 */
@Getter
public class GiftSendEvent extends AbstractGiftEvent {

    /**
     * -- GETTER --
     * 获取来源群ID
     */
    protected String islandSourceId;

    /**
     * -- GETTER --
     * 获取来源频道ID
     */
    protected String channelId;

    /**
     * -- GETTER --
     * 获取订单号
     */
    protected String orderNo;

    /**
     * -- GETTER --
     * 获取内容类型，1：消息，2：帖子
     */
    protected Integer targetType;

    /**
     * -- GETTER --
     * 获取内容ID
     */
    protected String targetId;

    /**
     * -- GETTER --
     * 获取礼物总价值（铃钱）
     */
    protected BigDecimal totalAmount;

    /**
     * -- GETTER --
     * 获取礼物信息Object
     */
    protected JSONObject gift;

    /**
     * -- GETTER --
     * 获取群分成（百分比）
     */
    protected BigDecimal islandRatio;

    /**
     * -- GETTER --
     * 获取群收入（里程）
     */
    protected BigDecimal islandIncome;

    /**
     * -- GETTER --
     * 获取赠礼人DoDoID
     */
    protected String dodoSourceId;

    /**
     * -- GETTER --
     * 获取赠礼人群昵称
     */
    protected String dodoIslandNickName;

    /**
     * -- GETTER --
     * 获取被赠礼人DoDoID
     */
    protected String toDodoSourceId;

    /**
     * -- GETTER --
     * 获取被赠礼人群昵称
     */
    protected String toDodoIslandNickName;

    /**
     * -- GETTER --
     * 获取被赠礼人分成（百分比）
     */
    protected BigDecimal toDodoRatio;

    /**
     * -- GETTER --
     * 获取被赠礼人收入（里程）
     */
    protected BigDecimal toDodoIncome;

    public GiftSendEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getLong("timestamp");
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