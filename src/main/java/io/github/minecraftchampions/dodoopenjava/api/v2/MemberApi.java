package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.User;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.impl.DodoUserImpl;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import lombok.*;
import org.json.JSONObject;

import java.util.List;

/**
 * 成员API
 *
 * @author qscbm187531
 */
@RequiredArgsConstructor
@Data
public class MemberApi {
    @NonNull
    private Bot bot;

    /**
     * 获取成员列表
     *
     * @param islandSourceId 群号
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return result
     */
    public Result getMemberList(String islandSourceId, int pageSize, long maxId) {
        String url = DodoOpenJava.BASEURL + "member/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("pageSize", pageSize)
                .put("maxId", maxId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取成员信息
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   玩家Dodo号
     * @return result
     */
    public Result getMemberInfo(String islandSourceId, String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "member/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取成员身份组列表
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return result
     */
    public Result getMemberRoleList(String islandSourceId, String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "member/role/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取成员邀请信息
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return result
     */
    public Result getMemberInvitationInfo(String islandSourceId, String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "member/invitation/info[-0o.";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 编辑成员群昵称
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param nickName       群昵称，昵称不能大于32个字符或16个汉字
     * @return result
     */
    public Result editMemberNickName(String islandSourceId, String dodoSourceId,
                                     String nickName) {
        String url = DodoOpenJava.BASEURL + "member/nickname/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("nickName", nickName)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 禁言成员
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param duration       禁言时长(秒),最长为7天
     * @return result
     */
    public Result addMemberMute(String islandSourceId, String dodoSourceId, int duration) {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("duration", duration);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 禁言成员
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param duration       禁言时长(秒),最长为7天
     * @param reason         禁言原因，原因不能大于64个字符或32个汉字
     * @return result
     */
    public Result addMemberReasonMute(String islandSourceId, String dodoSourceId,
                                      int duration, String reason) {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("reason", reason)
                .put("duration", duration);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 取消成员禁言
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return result
     */
    public Result removeMemberMute(String islandSourceId, String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "member/mute/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 永久封禁成员
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param setting        封禁成员的一些参数设置
     * @return result
     */
    public Result addMemberBan(String islandSourceId, String dodoSourceId, BanSetting setting) {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        if (setting.hasReason()) {
            jsonObject.put("reason", setting.getReason());
        }
        if (setting.hasNoticeChannelId()) {
            jsonObject.put("noticeChannelId", setting.getNoticeChannelId());
        }
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 取消成员永久封禁
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return result
     */
    public Result removeMemberBan(String islandSourceId, String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "member/ban/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取成员DoDo号映射列表
     *
     * @param dodoIdList dodoId
     * @return result
     */
    public Result getMemberDodoIdMapList(List<String> dodoIdList) {
        String url = DodoOpenJava.BASEURL + "member/dodoid/map/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dodoIdList", dodoIdList);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取用户
     *
     * @param islandSourceId 群id
     * @param dodoSourceId   dodoId
     * @return User
     */
    public User getUser(String islandSourceId, String dodoSourceId) {
        return new DodoUserImpl(dodoSourceId, islandSourceId, bot);
    }

    @Data
    @Builder
    public static class BanSetting {
        private String noticeChannelId;
        private String reason;

        public boolean hasReason() {
            return reason != null && !reason.isEmpty();
        }

        public boolean hasNoticeChannelId() {
            return noticeChannelId != null && !noticeChannelId.isEmpty();
        }
    }
}
