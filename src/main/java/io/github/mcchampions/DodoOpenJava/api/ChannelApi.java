package io.github.mcchampions.DodoOpenJava.api;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * 频道API
 */
public class ChannelApi {
    public static String url,parm;
    /**
     * 获取频道列表
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getChannelList(String clientId, String token, String islandId, Boolean returnJSONText) throws IOException {
        return getChannelList(BaseUtil.Authorization(clientId,token), islandId, returnJSONText);
    }

    /**
     * 获取频道列表
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getChannelList(String Authorization, String islandId,Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/list";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\"\n" +
                "}";
        String channel;
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            JSONArray JSONList = JSONObject.parseObject(Parm).getJSONArray("data");
            int JSONListSize = JSONList.size();
            String[] List = new String[0];
            for (int i = 0;i < JSONListSize;i++) {
                Object object = JSONList.get(i);
                String JSONText = object.toString();
                String channelCount = String.valueOf(i + 1);
                String channelId = JSONObject.parseObject(JSONText).getString("channelId");
                String channelName = JSONObject.parseObject(JSONText).getString("channelName");
                int channelTypeInt = JSONObject.parseObject(JSONText).getIntValue("channelType");
                String channelType = switch (channelTypeInt) {
                    case 1 -> "文字频道";
                    case 2 -> "语音频道";
                    case 4 -> "帖子频道";
                    case 5 -> "链接频道";
                    case 6 -> "资料频道";
                    default -> null;
                };
                int defaultFlagInt = JSONObject.parseObject(JSONText).getIntValue("defaultFlag");
                String defaultFlag;
                if (defaultFlagInt == 0) {
                    defaultFlag = "否";
                } else {
                    defaultFlag = "是";
                }
                String groupId = JSONObject.parseObject(JSONText).getString("groupId");
                String groupName = JSONObject.parseObject(JSONText).getString("defaultChannelId");
                channel = "  频道" + channelCount + ": \n" +
                          "    频道ID: \"" + channelId + "\"\n" +
                          "    频道名称: \"" + channelName + "\"\n" +
                          "    频道类型: \"" + channelType + "\"\n" +
                          "    是否为默认访问频道: \"" + defaultFlag + "\"\n" +
                          "    分组ID: \"" + groupId + "\"\n" +
                          "    分组名称: \"" + groupName + "\"\n";
                List[i] = channel;
            }
            String islandList = StringUtils.join(List);
            Parm = "群列表:\n" + islandList;
        }
        return Parm;
    }

    /**
     * 获取频道信息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param channelId 频道号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getChannelInfo(String clientId, String token, String channelId, Boolean returnJSONText) throws IOException {
        return getChannelInfo(BaseUtil.Authorization(clientId, token), channelId, returnJSONText);
    }

    /**
     * 获取频道信息
     *
     * @param Authorization Authorization
     * @param channelId 频道号
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getChannelInfo(String Authorization, String channelId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/info";
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            JSONObject JSONText = JSONObject.parseObject(Parm).getJSONObject("data");
            String ChannelId = JSONText.getString("channelId");
            String channelName = JSONText.getString("channelName");
            int channelTypeInt = JSONText.getIntValue("channelType");
            String channelType = switch (channelTypeInt) {
                case 1 -> "文字频道";
                case 2 -> "语音频道";
                case 4 -> "帖子频道";
                case 5 -> "链接频道";
                case 6 -> "资料频道";
                default -> null;
            };
            int defaultFlagInt = JSONText.getIntValue("defaultFlag");
            String defaultFlag;
            if (defaultFlagInt == 0) {
                defaultFlag = "否";
            } else {
                defaultFlag = "是";
            }
            String groupId = JSONText.getString("groupId");
            String groupName = JSONText.getString("defaultChannelId");
            Parm = "    频道ID: \"" + ChannelId + "\"\n" +
                        "    频道名称: \"" + channelName + "\"\n" +
                        "    频道类型: \"" + channelType + "\"\n" +
                        "    是否为默认访问频道: \"" + defaultFlag + "\"\n" +
                        "    分组ID: \"" + groupId + "\"\n" +
                        "    分组名称: \"" + groupName + "\"\n";
        }
        return Parm;
    }

    /**
     * 创建频道
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param channelName 频道名
     * @param channelType 频道类型
     * @param returnJSONText 返回原本的JSON文本还是直接返回频道ID
     */
    public static String addChannel(String clientId, String token, String islandId, String channelName, int channelType, Boolean returnJSONText) throws IOException {
        return addChannel(BaseUtil.Authorization(clientId, token), islandId, channelName, channelType, returnJSONText);
    }

    /**
     * 创建频道
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param channelName 频道名
     * @param channelType 频道类型
     * @param returnJSONText 返回原本的JSON文本还是直接返回频道ID
     */
    public static String addChannel(String Authorization, String islandId, String channelName, int channelType, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/add";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"channelName\": \"" + channelName + "\",\n" +
                "    \"channelType\": " + channelType + "\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = JSONObject.parseObject(Parm).getJSONObject("data").getString("channelId");
        }
        return Parm;
    }
    
    /**
     * 编辑频道
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param channelName 频道名
     * @param channelId 频道号
     * @param returnJSONText 返回原本的JSON文本还是直接返回null
     */
    public static String editChannel(String clientId, String token, String islandId, String channelName, String channelId, Boolean returnJSONText) throws IOException {
        return editChannel(BaseUtil.Authorization(clientId, token), islandId, channelName, channelId, returnJSONText);
    }

    /**
     * 编辑频道
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param channelName 频道名
     * @param channelId 频道号
     * @param returnJSONText 返回原本的JSON文本还是直接返回null
     */
    public static String editChannel(String Authorization, String islandId, String channelName, String channelId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/edit";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"channelName\": \"" + channelName + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }
    
    /**
     * 删除频道
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param channelId 频道号
     * @param returnJSONText 返回原本的JSON文本还是直接返回null
     */
    public static String deleteChannel(String clientId, String token, String islandId, String channelId, Boolean returnJSONText) throws IOException {
        return deleteChannel(BaseUtil.Authorization(clientId, token), islandId, channelId, returnJSONText);
    }

    /**
     * 删除频道
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param channelId 频道号
     * @param returnJSONText 返回原本的JSON文本还是直接返回null
     */
    public static String deleteChannel(String Authorization, String islandId, String channelId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/remove";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"channelId\": \"" + channelId + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }
}
