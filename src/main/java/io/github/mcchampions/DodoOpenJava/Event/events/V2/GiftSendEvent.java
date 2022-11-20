package io.github.mcchampions.DodoOpenJava.Event.events.V2;

import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * ����ɹ��¼�
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
     * ��ȡ������
     * @return ������
     */
    public String getOrderNo() {
        return this.orderNo;
    }

    /**
     * ��ȡ��ԴƵ��ID
     * @return ��ԴƵ��ID
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * ��ȡ�����ܼ�ֵ����Ǯ��
     * @return �����ܼ�ֵ����Ǯ��
     */
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * ��ȡ��������DoDoID
     * @return ��������DoDoID
     */
    public String getToDodoSourceId() {
        return this.toDodoSourceId;
    }

    /**
     * ��ȡ�������˷ֳɣ��ٷֱȣ�
     * @return �������˷ֳɣ��ٷֱȣ�
     */
    public BigDecimal getToDodoRatio() {
        return this.toDodoRatio;
    }

    /**
     * ��ȡ��������Ⱥ�ǳ�
     * @return ��������Ⱥ�ǳ�
     */
    public String getToDodoIslandNickName() {
        return this.toDodoIslandNickName;
    }

    /**
     * ��ȡ�����������루��̣�
     * @return �����������루��̣�
     */
    public BigDecimal getToDodoIncome() {
        return this.toDodoIncome;
    }

    /**
     * ��ȡʱ���
     * @return ʱ���
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * ��ȡ�������ͣ�1����Ϣ��2������
     * @return �������ͣ�1����Ϣ��2������
     */
    public Integer getTargetType() {
        return this.targetType;
    }

    /**
     * ��ȡ����ID
     * @return ����ID
     */
    public String getTargetId() {
        return this.targetId;
    }

    /**
     * ��ȡ�¼�����
     * @return �¼�����
     */
    public String getJsonString() {
        return this.jsonString;
    }

    /**
     * ��ȡ�¼�����
     * @return �¼�����
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * ��ȡ��ԴȺID
     * @return ��ԴȺID
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * ��ȡȺ�ֳɣ��ٷֱȣ�
     * @return Ⱥ�ֳɣ��ٷֱȣ�
     */
    public BigDecimal getIslandRatio() {
        return this.islandRatio;
    }

    /**
     * ��ȡȺ���루��̣�
     * @return Ⱥ���루��̣�
     */
    public BigDecimal getIslandIncome() {
        return this.islandIncome;
    }

    /**
     * ��ȡ������ϢObject
     * @return ������ϢObject
     */
    public JSONObject getGift() {
        return this.gift;
    }

    /**
     * ��ȡ�¼�ID
     * @return �¼�ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * ��ȡ������DoDoID
     * @return ������DoDoID
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * ��ȡ������Ⱥ�ǳ�
     * @return ������Ⱥ�ǳ�
     */
    public String getDodoIslandNickName() {
        return this.dodoIslandNickName;
    }
}
