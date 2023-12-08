package io.github.minecraftchampions.dodoopenjava.event.events.v2.member;

import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import lombok.Getter;
import org.json.JSONObject;

import javax.annotation.Nonnull;

/**
 * 成员离开事件
 */
@Getter
public class MemberLeaveEvent extends MemberEvent {
    private static final HandlerList handlers = new HandlerList();

    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

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
     *  获取DodoSourceId
     *
     */
    public String dodoSourceId;

    /**
     * -- GETTER --
     *  获取成员Object
     *
     */
    public JSONObject personal;

    /**
     * -- GETTER --
     *  获取发送者名字
     *
     */
    public String userNickName;

    /**
     * -- GETTER --
     *  获取发送者头像URL
     *
     */
    public String userAvatarUrl;

    /**
     * -- GETTER --
     *  获取性别（Int类型）
     *
     */
    public Integer userIntSex;

    /**
     * -- GETTER --
     *  获取性别（String类型）
     *
     */
    public String userSex;

    /**
     * -- GETTER --
     *  获取变动时间
     *
     */
    public String modifyTime;

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
     *  获取离开类型（String）
     *
     */
    public String leaveType;

    /**
     * -- GETTER --
     *  获取离开类型（Int）
     *
     */
    public Integer leaveIntType;

    /**
     * -- GETTER --
     *  获取操作者Dodo号
     *
     */
    public String operateDoDoId;

    public MemberLeaveEvent(JSONObject json) {
        super(true);
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.userNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.userAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.userSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.userIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        this.leaveType = IntLeaveTypeToLeaveType(json.getJSONObject("data").getJSONObject("eventBody").getInt("leaveType"));
        this.leaveIntType = json.getJSONObject("data").getJSONObject("eventBody").getInt("leaveType");
        this.operateDoDoId = json.getJSONObject("data").getJSONObject("eventBody").getString("operateDoDoId");
    }

    /**
     * 转换 为Int数据类型的 性别关键字 为 String 类型
     *
     * @param IntSex 性别
     * @return 性别
     */
    public String IntSexToSex(Integer IntSex) {
        return switch (IntSex) {
            case 0 -> "女";
            case 1 -> "男";
            default -> "保密";
        };
    }

    /**
     * 转换 为Int数据类型的 退出类型关键字 为 String 类型
     *
     * @param Type 类型
     * @return 类型
     */
    public String IntLeaveTypeToLeaveType(Integer Type) {
        return switch (Type) {
            case 1 -> "主动";
            case 2 -> "被踢";
            default -> "未知";
        };
    }


}
