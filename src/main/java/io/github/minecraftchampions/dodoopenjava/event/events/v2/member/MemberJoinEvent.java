package io.github.minecraftchampions.dodoopenjava.event.events.v2.member;

import lombok.Getter;
import org.json.JSONObject;

/**
 * 成员加入事件
 *
 * @author qscbm187531
 */
@Getter
public class MemberJoinEvent extends AbstractMemberEvent {

    /**
     * -- GETTER --
     * 获取群号
     */
    protected final String islandSourceId;

    /**
     * -- GETTER --
     * 获取DodoSourceId
     */
    protected final String dodoSourceId;

    /**
     * -- GETTER --
     * 获取变动时间
     */
    protected final String modifyTime;

    public MemberJoinEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getLong("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        eventType = MemberJoinEvent.class;
    }
}