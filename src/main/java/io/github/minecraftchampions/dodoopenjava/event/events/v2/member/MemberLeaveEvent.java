package io.github.minecraftchampions.dodoopenjava.event.events.v2.member;

import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.MessageEvent;
import lombok.Getter;
import org.json.JSONObject;

/**
 * 成员离开事件
 *
 * @author qscbm187531
 */
@Getter
public class MemberLeaveEvent extends AbstractMemberEvent {

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
     * 获取成员Object
     */
    protected final JSONObject personal;

    /**
     * -- GETTER --
     * 获取发送者名字
     */
    protected final String userNickName;

    /**
     * -- GETTER --
     * 获取发送者头像URL
     */
    protected final String userAvatarUrl;

    /**
     * -- GETTER --
     * 获取性别（Int类型）
     */
    protected final Integer userIntSex;

    /**
     * -- GETTER --
     * 获取性别（String类型）
     */
    protected final String userSex;

    /**
     * -- GETTER --
     * 获取变动时间
     */
    protected final String modifyTime;

    /**
     * -- GETTER --
     * 获取离开类型（String）
     */
    protected final String leaveType;

    /**
     * -- GETTER --
     * 获取离开类型（Int）
     */
    protected final Integer leaveIntType;

    /**
     * -- GETTER --
     * 获取操作者Dodo号
     */
    protected final String operateDoDoId;

    public MemberLeaveEvent(JSONObject json) {
        super(true);
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.userNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.userAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.userSex = MessageEvent.intSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.userIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getLong("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        this.leaveType = intLeaveTypeToLeaveType(json.getJSONObject("data").getJSONObject("eventBody").getInt("leaveType"));
        this.leaveIntType = json.getJSONObject("data").getJSONObject("eventBody").getInt("leaveType");
        this.operateDoDoId = json.getJSONObject("data").getJSONObject("eventBody").getString("operateDoDoId");
        eventType = MemberLeaveEvent.class;
    }

    /**
     * 转换 为Int数据类型的 退出类型关键字 为 String 类型
     *
     * @param type 类型
     * @return 类型
     */
    public String intLeaveTypeToLeaveType(Integer type) {
        return switch (type) {
            case 1 -> "主动";
            case 2 -> "被踢";
            default -> "未知";
        };
    }
}