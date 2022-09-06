package io.github.mcchampions.DodoOpenJava.api;

import org.json.JSONArray;
import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * 群API
 */
public class IslandApi {
    public static String url,parm;

    /**
     * 获取群信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandInfo(String clientId, String token, String islandId, Boolean returnJSONText) throws IOException {
        return getIslandInfo(BaseUtil.Authorization(clientId, token), islandId, returnJSONText);
    }

    /**
     * 获取群信息
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandInfo(String Authorization, String islandId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/info";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            JSONObject JSONText = new JSONObject(Parm).getJSONObject("data");
            String islandID = JSONText.getString("islandID");
            String islandName = JSONText.getString("islandName");
            String coverUrl = JSONText.getString("coverUrl");
            String memberCount = String.valueOf(JSONText.getInt("memberCount"));
            String onlineMemberCount = String.valueOf(JSONText.getInt("onlineMemberCount"));
            String description = JSONText.getString("description");
            String defaultChannelId	 = JSONText.getString("defaultChannelId");
            String systemChannelId	 = JSONText.getString("systemChannelId");
            Parm = "群号: \"" + islandID + "\"\n" +
                    "群名称: \"" + islandName + "\"\n" +
                    "群头像: \"" + coverUrl + "\"\n" +
                    "成员数量: \"" + memberCount + "\"\n" +
                    "在线成员数量: \"" + onlineMemberCount + "\"\n" +
                    "群简介: \"" + description + "\"\n" +
                    "默认访问频道ID: \"" + defaultChannelId + "\"\n" +
                    "系统消息频道ID: \"" + systemChannelId + "\"";
        }
        return Parm;
    }

    /**
     * 获取机器人群列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandList(String clientId, String token, Boolean returnJSONText) throws IOException {
        return getIslandList(BaseUtil.Authorization(clientId,token), returnJSONText);
    }

    /**
     * 获取机器人群列表
     *
     * @param Authorization Authorization
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandList(String Authorization, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/list";
        parm = "{}";
        String island;
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            JSONArray JSONList = new JSONObject(Parm).getJSONArray("data");
            int JSONListSize = JSONList.toList().size();
            String[] List = new String[0];
            for (int i = 0;i < JSONListSize;i++) {
                Object object = JSONList.get(i);
                String JSONText = object.toString();
                String islandCount = String.valueOf(i + 1);
                String islandId = new JSONObject(JSONText).getString("islandID");
                String islandName = new JSONObject(JSONText).getString("islandName");
                String coverUrl = new JSONObject(JSONText).getString("coverUrl");
                String memberCount = String.valueOf(new JSONObject(JSONText).getInt("memberCount"));
                String onlineMemberCount = String.valueOf(new JSONObject(JSONText).getInt("onlineMemberCount"));
                String defaultChannelId	 = new JSONObject(JSONText).getString("defaultChannelId");
                String systemChannelId	 = new JSONObject(JSONText).getString("systemChannelId");
                island = "  群" + islandCount + ": \n" +
                        "    群号: \"" + islandId + "\"\n" +
                        "    群名称: \"" + islandName + "\"\n" +
                        "    群头像: \"" + coverUrl + "\"\n" +
                        "    成员数量: \"" + memberCount + "\"\n" +
                        "    在线成员数量: \"" + onlineMemberCount + "\"\n" +
                        "    默认访问频道ID: \"" + defaultChannelId + "\"\n" +
                        "    系统消息频道ID: \"" + systemChannelId + "\"\n";
                List[i] = island;
            }
            String islandList = StringUtils.join(List);
            Parm = "群列表:\n" + islandList;
        }
        return Parm;
    }

    /**
     * 获取群等级排行榜
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandLevelRankList(String clientId, String token, String islandId, int pageSize, long maxId, Boolean returnJSONText) throws IOException {
        return getIslandLevelRankList(BaseUtil.Authorization(clientId, token), islandId, pageSize, maxId, returnJSONText);
    }

    /**
     * 获取群等级排行榜
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param pageSize 页大小，最大100
     * @param maxId 上一页最大ID值，为提升分页查询性能，需要传入上一页查询记录中的最大ID值，首页请传0
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandLevelRankList(String Authorization, String islandId, int pageSize, long maxId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/info";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"pageSize\": \"" + pageSize + "\",\n" +
                "    \"maxId\": \"" + maxId + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        String PARM;
        if (!returnJSONText) {
            JSONArray JSONList = new JSONObject(Parm).getJSONArray("data");
            int JSONListSize = JSONList.toList().size();
            String[] List = new String[0];
            for (int i = 0;i < JSONListSize;i++) {
                Object object = JSONList.get(i);
                String JSONText = object.toString();
                String Rank = String.valueOf(i + 1);
                String dodoId = new JSONObject(JSONText).getString("dodoId");
                String nickName = new JSONObject(JSONText).getString("nickName");
                String level = String.valueOf(new JSONObject(JSONText).getInt("level"));
                String rank = "第" + new JSONObject(JSONText).getInt("rank");
                PARM = "  第" + Rank + ": \n" +
                        "    Dodo号: \"" + dodoId + "\"\n" +
                        "    群昵称: \"" + nickName + "\"\n" +
                        "    等级: \"" + level + "\"\n" +
                        "    排名: \"" + rank + "\"\n";
                List[i] = PARM;
            }
            String Rank = StringUtils.join(List);
            Parm = "群列表群等级排行榜:\n" + Rank;
        }
        return Parm;
    }

    /**
     * 获取禁言列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandMuteList(String clientId, String token, String islandId, Boolean returnJSONText) throws IOException {
        return getIslandMuteList(BaseUtil.Authorization(clientId, token), islandId, returnJSONText);
    }

    /**
     * 获取禁言列表
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandMuteList(String Authorization, String islandId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/mute/list";
        parm = "{\n" +
                "    \"islandID\": \"" + islandId + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if(!returnJSONText) {
            JSONArray JSONList = new JSONObject(Parm).getJSONObject("data").getJSONArray("list");
            int JSONListSize = JSONList.toList().size();
            String[] List = new String[0];
            for (int i = 0;i < JSONListSize;i++) {
                Object object = JSONList.get(i);
                String JSONText = object.toString();
                String DodoId = new JSONObject(JSONText).getString("dodoID");
                String mute = "  Dodo号" + DodoId + ": \n";

                List[i] = mute;
            }
            String MuteList = StringUtils.join(List);
            Parm = "禁言列表:\n" + MuteList;
        }
        return Parm;
    }

    /**
     * 获取封禁列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandBanList(String clientId, String token, String islandId, Boolean returnJSONText) throws IOException {
        return getIslandBanList(BaseUtil.Authorization(clientId, token), islandId, returnJSONText);
    }

    /**
     * 获取封禁列表
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getIslandBanList(String Authorization, String islandId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/ban/list";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if(!returnJSONText) {
            JSONArray JSONList = new JSONObject(Parm).getJSONObject("data").getJSONArray("list");
            int JSONListSize = JSONList.toList().size();
            String[] List = new String[0];
            for (int i = 0;i < JSONListSize;i++) {
                Object object = JSONList.get(i);
                String JSONText = object.toString();
                String DodoId = new JSONObject(JSONText).getString("dodoID");
                String ban = "  Dodo号" + DodoId + ": \n";

                List[i] = ban;
            }
            String BanList = StringUtils.join(List);
            Parm = "封禁列表:\n" + BanList;
        }
        return Parm;
    }
}
