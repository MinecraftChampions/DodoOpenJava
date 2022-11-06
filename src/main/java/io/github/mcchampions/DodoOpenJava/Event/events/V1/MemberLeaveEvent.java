package io.github.mcchampions.DodoOpenJava.Event.events.V1;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;

import javax.annotation.Nonnull;

/**
 * ��Ա�뿪�¼�
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

    public String islandId;

    public String dodoId;

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
        this.islandId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandId");
        this.dodoId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoId");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        this.leaveType = IntLeaveTypeToLeaveType(json.getJSONObject("data").getJSONObject("eventBody").getInt("leaveType"));
        this.leaveIntType = json.getJSONObject("data").getJSONObject("eventBody").getInt("leaveType");
        this.operateDoDoId = json.getJSONObject("data").getJSONObject("eventBody").getString("operateDoDoId");
    }

    /**
     * ת�� ΪInt�������͵� �Ա�ؼ��� Ϊ String ����
     * @param IntSex �Ա�
     * @return �Ա�
     */
    public String IntSexToSex(Integer IntSex) {
        return switch (IntSex) {
            case 0 -> "Ů";
            case 1 -> "��";
            default -> "����";
        };
    }

    /**
     * ת�� ΪInt�������͵� �˳����͹ؼ��� Ϊ String ����
     * @param Type ����
     * @return ����
     */
    public String IntLeaveTypeToLeaveType(Integer Type) {
        return switch (Type) {
            case 1 -> "����";
            case 2 -> "����";
            default -> "δ֪";
        };
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

    /**
     * ��ȡ��ԱObject
     * @return ��ȡ��Ա�� JsonObject
     */
    public JSONObject getPersonal() {
        return this.personal;
    }


    /**
     * ��ȡ����������
     * @return ����
     */
    public String getUserNickName() {
        return this.userNickName;
    }

    /**
     * ��ȡ������ͷ��URL
     * @return ͷ��url
     */
    public String getUserAvatarUrl() {
        return this.userAvatarUrl;
    }

    /**
     * ��ȡ�Ա�Int���ͣ�
     * @return �Ա�
     */
    public Integer getUserIntSex() {
        return this.userIntSex;
    }

    /**
     * ��ȡ�Ա�String���ͣ�
     * @return �Ա�
     */
    public String getUserSex() {
        return this.userSex;
    }

    /**
     * ��ȡ�뿪���ͣ�String��
     * @return �뿪����
     */
    public String getLeaveType() {
        return this.leaveType;
    }

    /**
     * ��ȡ�뿪���ͣ�Int��
     * @return �뿪����
     */
    public Integer getLeaveIntType() {
        return this.leaveIntType;
    }

    /**
     * ��ȡ������Dodo��
     * @return Dodo��
     */
    public String getOperateDoDoId() {
        return this.operateDoDoId;
    }
}
