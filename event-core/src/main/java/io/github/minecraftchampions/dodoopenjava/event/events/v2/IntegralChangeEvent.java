package io.github.minecraftchampions.dodoopenjava.event.events.v2;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * ���ֱ���¼�
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

    public String modifyTime;

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
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        this.integral = json.getJSONObject("data").getJSONObject("eventBody").getLong("integral");
        this.operateType = json.getJSONObject("data").getJSONObject("eventBody").getInt("operateType");
    }

    /**
     * ��ȡ��������
     * 1��ǩ����2�����룬3��ת�ˣ�4��������Ʒ��5��������֣�6����Ⱥ
     * @return ��������
     */
    public int getOperateType() {
        return operateType;
    }

    /**
     * ��ȡ��������
     * ����ʱ��ʾ�������ӣ�����ʱ��ʾ���ּ���
     * @return ����
     */
    public long getIntegral() {
        return integral;
    }

    /**
     * ��ȡʱ���
     * @return ����ʱ���
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * ��ȡ�¼�ID
     * @return �¼�ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * ��ȡȺ��
     * @return Ⱥ��
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * ��ȡDodoId
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * ��ȡ�䶯ʱ��
     * @return �䶯ʱ��
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * ��ȡJSONObject
     * @return JSONObject
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * ��ȡJsonString
     * @return String
     */
    public String getJsonString() {
        return this.jsonString;
    }
}
