package io.github.mcchampions.DodoOpenJava.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.Util;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * 成员API
 */
public class MemberApi {
    public static String url,parm;

    /**
     * 获取成员列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getMemberList(String clientId, String token, String islandId, int pageSize, long maxId, Boolean returnJSONText) throws IOException {
        return getMemberList(Util.Authorization(clientId, token), islandId, pageSize, maxId, returnJSONText);
    }

    /**
     * 获取成员列表
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getMemberList(String Authorization, String islandId, int pageSize, long maxId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/list";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"pageSize\": \"" + pageSize + "\",\n" +
                "    \"maxId\": \"" + maxId + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        String PARM;
        if (!returnJSONText) {
            JSONArray JSONList = JSONObject.parseObject(Parm).getJSONObject("data").getJSONArray("list");
            int JSONListSize = JSONList.size();
            String[] List = new String[0];
            for (int i = 0;i < JSONListSize;i++) {
                Object object = JSONList.get(i);
                String JSONText = object.toString();
                String Member = String.valueOf(i + 1);
                String dodoId = JSONObject.parseObject(JSONText).getString("dodoId");
                String nickName = JSONObject.parseObject(JSONText).getString("nickName");
                String personalNickName = JSONObject.parseObject(JSONText).getString("personalNickName");
                String avatarUrl = JSONObject.parseObject(JSONText).getString("avatarUrl");
                String joinTime = JSONObject.parseObject(JSONText).getString("joinTime");
                String SexType,ISBOT,OnlineDevice,OnlineStatus;
                int sex = JSONObject.parseObject(JSONText).getIntValue("sex");
                int level = JSONObject.parseObject(JSONText).getIntValue("level");
                int isBot = JSONObject.parseObject(JSONText).getIntValue("isBot");
                int onlineDevice = JSONObject.parseObject(JSONText).getIntValue("onlineDevice");
                int onlineStatus = JSONObject.parseObject(JSONText).getIntValue("onlineStatus");

                if (sex == 0) {
                    SexType = "女";
                } else {
                    if (sex == -1) {
                        SexType = "保密";
                    } else {
                        SexType = "男";
                    }
                }

                if (isBot == 0) {
                    ISBOT = "否";
                } else {
                    ISBOT = "是";
                }

                if (onlineDevice == 0) {
                    OnlineDevice = "无客户端在线";
                } else {
                    if (onlineDevice == 1) {
                        OnlineDevice = "PC在线";
                    } else {
                        OnlineDevice = "手机在线";
                    }
                }

                if (onlineStatus == 0) {
                    OnlineStatus = "离线";
                } else {
                    if (onlineStatus == 1) {
                        OnlineStatus = "在线";
                    } else {
                        if (onlineStatus == 2) {
                            OnlineStatus = "请勿打扰";
                        } else {
                            OnlineStatus = "离开";
                        }
                    }
                }

                PARM = "  成员" + Member + ": \n" +
                        "    群昵称: \"" + nickName + "\"\n" +
                        "    Dodo昵称: \"" + personalNickName + "\"\n" +
                        "    Dodo号: \"" + dodoId + "\"\n" +
                        "    头像URL地址: \"" + avatarUrl + "\"\n" +
                        "    加群时间: \"" + joinTime + "\"\n" +
                        "    性别 \"" + SexType + "\"\n" +
                        "    等级: \"" + level + "\"\n" +
                        "    是否为机器人: \"" + ISBOT + "\"\n" +
                        "    在线设备: \"" + OnlineDevice + "\"\n" +
                        "    在线状态: \"" + OnlineStatus + "\"\n";
                List[i] = PARM;
            }
            String mb = StringUtils.join(List);
            Parm = "成员列表:\n" + mb;
        }
        return Parm;
    }

    /**
     * 获取成员信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId 玩家Dodo号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getMemberInfo(String clientId, String token, String islandId,String DodoId, Boolean returnJSONText) throws IOException {
        return getMemberInfo(Util.Authorization(clientId, token), islandId, DodoId,returnJSONText);
    }

    /**
     * 获取成员信息
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId 玩家Dodo号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getMemberInfo(String Authorization, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/info";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            JSONObject JSONText = JSONObject.parseObject(Parm).getJSONObject("data");
            String dodoId = JSONText.getString("dodoId");
            String nickName = JSONText.getString("nickName");
            String personalNickName = JSONText.getString("personalNickName");
            String avatarUrl = JSONText.getString("avatarUrl");
            String joinTime = JSONText.getString("joinTime");
            String SexType,ISBOT,OnlineDevice,OnlineStatus;
            int sex = JSONText.getIntValue("sex");
            int level = JSONText.getIntValue("level");
            int isBot = JSONText.getIntValue("isBot");
            int onlineDevice = JSONText.getIntValue("onlineDevice");
            int onlineStatus = JSONText.getIntValue("onlineStatus");

            if (sex == 0) {
                SexType = "女";
            } else {
                if (sex == -1) {
                    SexType = "保密";
                } else {
                    SexType = "男";
                }
            }

            if (isBot == 0) {
                ISBOT = "否";
            } else {
                ISBOT = "是";
            }

            if (onlineDevice == 0) {
                OnlineDevice = "无客户端在线";
            } else {
                if (onlineDevice == 1) {
                    OnlineDevice = "PC在线";
                } else {
                    OnlineDevice = "手机在线";
                }
            }

            if (onlineStatus == 0) {
                OnlineStatus = "离线";
            } else {
                if (onlineStatus == 1) {
                    OnlineStatus = "在线";
                } else {
                    if (onlineStatus == 2) {
                        OnlineStatus = "请勿打扰";
                    } else {
                        OnlineStatus = "离开";
                    }
                }
            }

            Parm =  "群昵称: \"" + nickName + "\"\n" +
                    "Dodo昵称: \"" + personalNickName + "\"\n" +
                    "Dodo号: \"" + dodoId + "\"\n" +
                    "头像URL地址: \"" + avatarUrl + "\"\n" +
                    "加群时间: \"" + joinTime + "\"\n" +
                    "群: \"" + islandId + "\"\n" +
                    "性别 \"" + SexType + "\"\n" +
                    "等级: \"" + level + "\"\n" +
                    "是否为机器人: \"" + ISBOT + "\"\n" +
                    "在线设备: \"" + OnlineDevice + "\"\n" +
                    "在线状态: \"" + OnlineStatus + "\"\n";
        }
        return Parm;
    }

    /**
     * 获取成员身份组列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数
     */
    public static String getMemberRoleList(String clientId, String token, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        return getMemberRoleList(Util.Authorization(clientId, token), islandId, DodoId, returnJSONText);
    }

    /**
     * 获取成员身份组列表
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数
     */
    public static String getMemberRoleList(String Authorization, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/role/list";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoID\": \"" + DodoId + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        String role;
        if (!returnJSONText) {
            JSONArray JSONList = JSONObject.parseObject(Parm).getJSONArray("data");
            int JSONListSize = JSONList.size();
            String[] List = new String[0];
            for (int i = 0; i < JSONListSize; i++) {
                Object object = JSONList.get(i);
                String JSONText = object.toString();
                String roleCount = String.valueOf(i + 1);
                String roleId = JSONObject.parseObject(JSONText).getString("roleId");
                String roleName = JSONObject.parseObject(JSONText).getString("roleName");
                String roleColor = JSONObject.parseObject(JSONText).getString("roleColor");
                String position = String.valueOf(JSONObject.parseObject(JSONText).getIntValue("position"));
                String permission = JSONObject.parseObject(JSONText).getString("permission");
                role = "  身份组" + roleCount + ": \n" +
                        "    身份组ID: \"" + roleId + "\"\n" +
                        "    身份组名称: \"" + roleName + "\"\n" +
                        "    身份组颜色: \"" + roleColor + "\"\n" +
                        "    身份组排序位置: \"" + position + "\"\n" +
                        "    身份组权限值(16进制): \"" + permission + "\"\n";
                List[i] = role;
            }
            String roleList = StringUtils.join(List);
            Parm = "身份组列表:\n" + roleList;
        }
        return Parm;
    }

    /**
     * 获取成员邀请信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数
     */
    public static String getMemberInvitationInfo(String clientId, String token, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        return getMemberInvitationInfo(Util.Authorization(clientId, token), islandId, DodoId, returnJSONText);
    }

    /**
     * 获取成员邀请信息
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数
     */
    public static String getMemberInvitationInfo(String Authorization, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/role/list";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String DoDoId = JSONObject.parseObject(Parm).getJSONObject("data").getString("dodoId");
            String nickName = JSONObject.parseObject(Parm).getJSONObject("data").getString("nickName");
            int invitationCount = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("invitationCount");
            Parm =  "DoDo号: \"" + DoDoId + "\"\n" +
                    "群昵称: \"" + nickName + "\"\n" +
                    "邀请人数: \"" + invitationCount + "\"\n";
        }
        return Parm;
    }

    /**
     * 编辑成员群昵称
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param nickName 群昵称，昵称不能大于32个字符或16个汉字
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberNickNameEdit(String clientId, String token, String islandId, String DodoId, String nickName, Boolean returnJSONText) throws IOException {
        return setMemberNickNameEdit(Util.Authorization(clientId, token), islandId, DodoId, nickName, returnJSONText);
    }

    /**
     * 编辑成员群昵称
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param nickName 群昵称，昵称不能大于32个字符或16个汉字
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberNickNameEdit(String Authorization, String islandId, String DodoId, String nickName, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/nick/set";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\",\n" +
                "    \"nickName\": \"" + nickName + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 禁言成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param duration 禁言时长(秒),最长为7天
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberMuteAdd(String clientId, String token, String islandId, String DodoId, int duration, Boolean returnJSONText) throws IOException {
        return setMemberMuteAdd(Util.Authorization(clientId, token), islandId, DodoId, duration, returnJSONText);
    }

    /**
     * 禁言成员
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param duration 禁言时长(秒),最长为7天
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberMuteAdd(String Authorization, String islandId, String DodoId, int duration, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/set";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\",\n" +
                "    \"duration\": " + duration + "\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 禁言成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param duration 禁言时长(秒),最长为7天
     * @param DodoId Dodo号
     * @param reason 禁言原因，原因不能大于64个字符或32个汉字
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberReasonrMuteAdd(String clientId, String token, String islandId, String DodoId, int duration, String reason, Boolean returnJSONText) throws IOException {
        return setMemberReasonrMuteAdd(Util.Authorization(clientId, token), islandId, DodoId, duration, reason, returnJSONText);
    }

    /**
     * 禁言成员
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param duration 禁言时长(秒),最长为7天
     * @param reason 禁言原因，原因不能大于64个字符或32个汉字
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberReasonrMuteAdd(String Authorization, String islandId, String DodoId, int duration, String reason, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/set";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\",\n" +
                "    \"duration\": " + duration + ",\n" +
                "    \"reason\": \"" + reason + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 取消成员禁言
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberMuteRemove(String clientId, String token, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        return setMemberMuteRemove(Util.Authorization(clientId, token), islandId, DodoId, returnJSONText);
    }

    /**
     * 取消成员禁言
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberMuteRemove(String Authorization, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/mute/remove";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 永久封禁成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberBanAdd(String clientId, String token, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        return setMemberBanAdd(Util.Authorization(clientId, token), islandId, DodoId, returnJSONText);
    }

    /**
     * 永久封禁成员
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberBanAdd(String Authorization, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 永久封禁成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param reason 封禁理由
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberReasonBanAdd(String clientId, String token, String islandId, String DodoId, String reason, Boolean returnJSONText) throws IOException {
        return setMemberReasonBanAdd(Util.Authorization(clientId, token), islandId, DodoId, reason, returnJSONText);
    }

    /**
     * 永久封禁成员
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param reason 封禁理由
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberReasonBanAdd(String Authorization, String islandId, String DodoId, String reason, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\",\n" +
                "    \"reason\": \"" + reason + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 永久封禁成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param noticeChannelId 通知频道ID
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMembeChannelBanAdd(String clientId, String token, String islandId, String DodoId, String noticeChannelId, Boolean returnJSONText) throws IOException {
        return setMembeChannelBanAdd(Util.Authorization(clientId, token), islandId, DodoId, noticeChannelId, returnJSONText);
    }

    /**
     * 永久封禁成员
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param noticeChannelId 通知频道ID
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMembeChannelBanAdd(String Authorization, String islandId, String DodoId, String noticeChannelId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\",\n" +
                "    \"noticeChannelId\": \"" + noticeChannelId + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 永久封禁成员
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param noticeChannelId 通知频道ID
     * @param reason 封禁理由，理由不能大于64个字符或32个汉字
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberBanAdd(String clientId, String token, String islandId, String DodoId, String noticeChannelId, String reason, Boolean returnJSONText) throws IOException {
        return setMemberBanAdd(Util.Authorization(clientId, token), islandId, DodoId, noticeChannelId, reason, returnJSONText);
    }

    /**
     * 永久封禁成员
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param noticeChannelId 通知频道ID
     * @param reason 封禁理由，理由不能大于64个字符或32个汉字
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberBanAdd(String Authorization, String islandId, String DodoId, String noticeChannelId, String reason, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/add";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\",\n" +
                "    \"noticeChannelId\": \"" + noticeChannelId + "\",\n" +
                "    \"reason\": \"" + reason + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 取消成员永久封禁
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberBanRemove(String clientId, String token, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        return setMemberBanRemove(Util.Authorization(clientId, token), islandId, DodoId, returnJSONText);
    }

    /**
     * 取消成员永久封禁
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param DodoId Dodo号
     * @param returnJSONText 返回原本的出参JSON文本还是经过解析后的参数,由于没有什么好解析的,所以如果设置为false就返回null
     */
    public static String setMemberBanRemove(String Authorization, String islandId, String DodoId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/ban/remove";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + DodoId + "\"\n" +
                "}";
        String Parm = Util.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }
}
