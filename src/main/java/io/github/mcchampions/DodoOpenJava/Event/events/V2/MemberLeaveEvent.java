package io.github.mcchampions.DodoOpenJava.Event.events.V2;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;

import javax.annotation.Nonnull;

/**
 * 成员离开事件
 * @author qscbm187531
 */
public class MemberLeaveEvent extends Event {
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

    public JSONObject personal;

    public String userNickName;

    public String userAvatarUrl;

    public Integer userIntSex;

    public String userSex;

    public String modifyTime;

    public JSONObject jsonObject;

    public String jsonString;

    public String leaveType;

    public Integer leaveIntType;

    public String operateDoDoId;

    public MemberLeaveEvent(JSONObject json) {
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

    /**
     * 获取时间戳
     * @return 返回时间戳
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * 获取事件ID
     * @return 事件ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 获取群号
     * @return 群号
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * 获取DodoSourceId
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * 获取变动时间
     * @return 变动时间
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 获取JSONObject
     * @return JSONObject
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * 获取JsonString
     * @return String
     */
    public String getJsonString() {
        return this.jsonString;
    }

    /**
     * 获取成员Object
     * @return 获取成员的 JsonObject
     */
    public JSONObject getPersonal() {
        return this.personal;
    }


    /**
     * 获取发送者名字
     * @return 名字
     */
    public String getUserNickName() {
        return this.userNickName;
    }

    /**
     * 获取发送者头像URL
     * @return 头像url
     */
    public String getUserAvatarUrl() {
        return this.userAvatarUrl;
    }

    /**
     * 获取性别（Int类型）
     * @return 性别
     */
    public Integer getUserIntSex() {
        return this.userIntSex;
    }

    /**
     * 获取性别（String类型）
     * @return 性别
     */
    public String getUserSex() {
        return this.userSex;
    }

    /**
     * 获取离开类型（String）
     * @return 离开类型
     */
    public String getLeaveType() {
        return this.leaveType;
    }

    /**
     * 获取离开类型（Int）
     * @return 离开类型
     */
    public Integer getLeaveIntType() {
        return this.leaveIntType;
    }

    /**
     * 获取操作者Dodo号
     * @return Dodo号
     */
    public String getOperateDoDoId() {
        return this.operateDoDoId;
    }
}
