package io.github.minecraftchampions.dodoopenjava.event.events.v1;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * ��Ա�����¼�
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
    public String getIslandId() {
        return this.islandId;
    }

    /**
     * ��ȡDodoId
     * @return DodoId
     */
    public String getDodoId() {
        return this.dodoId;
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
