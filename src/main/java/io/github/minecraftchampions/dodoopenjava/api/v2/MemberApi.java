package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * 成员API
 *
 * @author qscbm187531
 */
public class MemberApi {
    /**
     * 获取成员列表
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberList(String clientId, String token, String islandSourceId, int pageSize, long maxId) throws IOException {
        return getMemberList(BaseUtil.generateAuthorization(clientId, token), islandSourceId, pageSize, maxId);
    }

    /**
     * 获取成员列表
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param pageSize       页大小，最大100
     * @param maxId          上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberList(String authorization, String islandSourceId, int pageSize, long maxId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("pageSize", pageSize)
                .put("maxId", maxId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取成员信息
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   玩家Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberInfo(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getMemberInfo(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 获取成员信息
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   玩家Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberInfo(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取成员身份组列表
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberRoleList(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getMemberRoleList(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 获取成员身份组列表
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberRoleList(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/role/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取成员邀请信息
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberInvitationInfo(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getMemberInvitationInfo(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 获取成员邀请信息
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberInvitationInfo(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/role/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 编辑成员群昵称
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param nickName       群昵称，昵称不能大于32个字符或16个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editMemberNickName(String clientId, String token, String islandSourceId, String dodoSourceId, String nickName) throws IOException {
        return editMemberNickName(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, nickName);
    }

    /**
     * 编辑成员群昵称
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param nickName       群昵称，昵称不能大于32个字符或16个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editMemberNickName(String authorization, String islandSourceId, String dodoSourceId, String nickName) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/nickname/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("nickName", nickName)
                .put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 禁言成员
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param duration       禁言时长(秒),最长为7天
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberMute(String clientId, String token, String islandSourceId, String dodoSourceId, int duration) throws IOException {
        return addMemberMute(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, duration);
    }

    /**
     * 禁言成员
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param duration       禁言时长(秒),最长为7天
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberMute(String authorization, String islandSourceId, String dodoSourceId, int duration) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("duration", duration);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 禁言成员
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param duration       禁言时长(秒),最长为7天
     * @param dodoSourceId   Dodo号
     * @param reason         禁言原因，原因不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberReasonMute(String clientId, String token, String islandSourceId, String dodoSourceId, int duration, String reason) throws IOException {
        return addMemberReasonMute(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, duration, reason);
    }

    /**
     * 禁言成员
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param duration       禁言时长(秒),最长为7天
     * @param reason         禁言原因，原因不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberReasonMute(String authorization, String islandSourceId, String dodoSourceId, int duration, String reason) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("reason", reason)
                .put("duration", duration);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 取消成员禁言
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result removeMemberMute(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return removeMemberMute(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 取消成员禁言
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result removeMemberMute(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/mute/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 永久封禁成员
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberBan(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return addMemberBan(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberBan(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 永久封禁成员
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param reason         封禁理由
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberReasonBan(String clientId, String token, String islandSourceId, String dodoSourceId, String reason) throws IOException {
        return addMemberReasonBan(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, reason);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @param reason         封禁理由
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberReasonBan(String authorization, String islandSourceId, String dodoSourceId, String reason) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("reason", reason);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);

    }

    /**
     * 永久封禁成员
     *
     * @param clientId        机器人唯一标识
     * @param token           机器人鉴权Token
     * @param islandSourceId  群号
     * @param noticeChannelId 提醒频道ID
     * @param dodoSourceId    Dodo号
     * @param reason          封禁理由
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberReasonChannelBan(String clientId, String token, String islandSourceId, String dodoSourceId, String reason, String noticeChannelId) throws IOException {
        return addMemberReasonChannelBan(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, reason, noticeChannelId);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization   authorization
     * @param islandSourceId  群号
     * @param dodoSourceId    Dodo号
     * @param reason          封禁理由
     * @param noticeChannelId 提醒频道ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberReasonChannelBan(String authorization, String islandSourceId, String dodoSourceId, String reason, String noticeChannelId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("reason", reason)
                .put("noticeChannelId", noticeChannelId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);

    }

    /**
     * 永久封禁成员
     *
     * @param clientId        机器人唯一标识
     * @param token           机器人鉴权Token
     * @param islandSourceId  群号
     * @param dodoSourceId    Dodo号
     * @param noticeChannelId 通知频道ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberChannelBan(String clientId, String token, String islandSourceId, String dodoSourceId, String noticeChannelId) throws IOException {
        return addMemberChannelBan(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, noticeChannelId);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization   authorization
     * @param islandSourceId  群号
     * @param dodoSourceId    Dodo号
     * @param noticeChannelId 通知频道ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberChannelBan(String authorization, String islandSourceId, String dodoSourceId, String noticeChannelId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("noticeChannelId", noticeChannelId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 永久封禁成员
     *
     * @param clientId        机器人唯一标识
     * @param token           机器人鉴权Token
     * @param islandSourceId  群号
     * @param dodoSourceId    Dodo号
     * @param noticeChannelId 通知频道ID
     * @param reason          封禁理由，理由不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberBan(String clientId, String token, String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) throws IOException {
        return addMemberBan(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, noticeChannelId, reason);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization   authorization
     * @param islandSourceId  群号
     * @param dodoSourceId    Dodo号
     * @param noticeChannelId 通知频道ID
     * @param reason          封禁理由，理由不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addMemberBan(String authorization, String islandSourceId, String dodoSourceId, String noticeChannelId, String reason) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/ban/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("reason", reason)
                .put("noticeChannelId", noticeChannelId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 取消成员永久封禁
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result removeMemberBan(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return removeMemberBan(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 取消成员永久封禁
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result removeMemberBan(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/ban/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取成员DoDo号映射列表
     *
     * @param clientId   机器人唯一标识
     * @param token      机器人鉴权Token
     * @param dodoIdList dodoId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberDodoIdMapList(String clientId, String token, List<String> dodoIdList) throws IOException {
        return getMemberDodoIdMapList(BaseUtil.generateAuthorization(clientId, token), dodoIdList);
    }

    /**
     * 获取成员DoDo号映射列表
     *
     * @param authorization authorization
     * @param dodoIdList    dodoId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberDodoIdMapList(String authorization, List<String> dodoIdList) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/dodoid/map/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dodoIdList", dodoIdList);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }
}
