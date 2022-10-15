package io.github.mcchampions.DodoOpenJava.Api;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 成员API
 * @author qscbm187531
 */
public class MemberApi {
    public static String url, param;

    /**
     * 获取成员列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberList(String clientId, String token, String islandId, int pageSize, long maxId) throws IOException {
        return getMemberList(BaseUtil.Authorization(clientId, token), islandId, pageSize, maxId);
    }

    /**
     * 获取成员列表
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberList(String authorization, String islandId, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"pageSize\": \"" + pageSize + "\"," +
                "    \"maxId\": \"" + maxId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取成员信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId 玩家Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberInfo(String clientId, String token, String islandId,String dodoId) throws IOException {
        return getMemberInfo(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * 获取成员信息
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId 玩家Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberInfo(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/info";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取成员身份组列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberRoleList(String clientId, String token, String islandId, String dodoId) throws IOException {
        return getMemberRoleList(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * 获取成员身份组列表
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberRoleList(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/role/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoID\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取成员邀请信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberInvitationInfo(String clientId, String token, String islandId, String dodoId) throws IOException {
        return getMemberInvitationInfo(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * 获取成员邀请信息
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberInvitationInfo(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/role/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 编辑成员群昵称
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param nickName 群昵称，昵称不能大于32个字符或16个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editMemberNickName(String clientId, String token, String islandId, String dodoId, String nickName) throws IOException {
        return editMemberNickName(BaseUtil.Authorization(clientId, token), islandId, dodoId, nickName);
    }

    /**
     * 编辑成员群昵称
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param nickName 群昵称，昵称不能大于32个字符或16个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editMemberNickName(String authorization, String islandId, String dodoId, String nickName) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/nick/set";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"nickName\": \"" + nickName + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 禁言成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param duration 禁言时长(秒),最长为7天
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberMute(String clientId, String token, String islandId, String dodoId, int duration) throws IOException {
        return addMemberMute(BaseUtil.Authorization(clientId, token), islandId, dodoId, duration);
    }

    /**
     * 禁言成员
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param duration 禁言时长(秒),最长为7天
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberMute(String authorization, String islandId, String dodoId, int duration) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/set";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"duration\": " + duration + "" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 禁言成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param duration 禁言时长(秒),最长为7天
     * @param dodoId Dodo号
     * @param reason 禁言原因，原因不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberReasonrMute(String clientId, String token, String islandId, String dodoId, int duration, String reason) throws IOException {
        return addMemberReasonrMute(BaseUtil.Authorization(clientId, token), islandId, dodoId, duration, reason);
    }

    /**
     * 禁言成员
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param duration 禁言时长(秒),最长为7天
     * @param reason 禁言原因，原因不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberReasonrMute(String authorization, String islandId, String dodoId, int duration, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/set";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"duration\": " + duration + "," +
                "    \"reason\": \"" + reason + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 取消成员禁言
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeMemberMute(String clientId, String token, String islandId, String dodoId) throws IOException {
        return removeMemberMute(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * 取消成员禁言
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeMemberMute(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/mute/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 永久封禁成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberBan(String clientId, String token, String islandId, String dodoId) throws IOException {
        return addMemberBan(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberBan(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 永久封禁成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param reason 封禁理由
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberReasonBan(String clientId, String token, String islandId, String dodoId, String reason) throws IOException {
        return addMemberReasonBan(BaseUtil.Authorization(clientId, token), islandId, dodoId, reason);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param reason 封禁理由
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberReasonBan(String authorization, String islandId, String dodoId, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"reason\": \"" + reason + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 永久封禁成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param noticeChannelId 通知频道ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberChannelBan(String clientId, String token, String islandId, String dodoId, String noticeChannelId) throws IOException {
        return addMemberChannelBan(BaseUtil.Authorization(clientId, token), islandId, dodoId, noticeChannelId);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param noticeChannelId 通知频道ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberChannelBan(String authorization, String islandId, String dodoId, String noticeChannelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"noticeChannelId\": \"" + noticeChannelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 永久封禁成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param noticeChannelId 通知频道ID
     * @param reason 封禁理由，理由不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberBan(String clientId, String token, String islandId, String dodoId, String noticeChannelId, String reason) throws IOException {
        return addMemberBan(BaseUtil.Authorization(clientId, token), islandId, dodoId, noticeChannelId, reason);
    }

    /**
     * 永久封禁成员
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @param noticeChannelId 通知频道ID
     * @param reason 封禁理由，理由不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addMemberBan(String authorization, String islandId, String dodoId, String noticeChannelId, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"noticeChannelId\": \"" + noticeChannelId + "\"," +
                "    \"reason\": \"" + reason + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 取消成员永久封禁
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeMemberBan(String clientId, String token, String islandId, String dodoId) throws IOException {
        return removeMemberBan(BaseUtil.Authorization(clientId, token), islandId, dodoId);
    }

    /**
     * 取消成员永久封禁
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeMemberBan(String authorization, String islandId, String dodoId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
