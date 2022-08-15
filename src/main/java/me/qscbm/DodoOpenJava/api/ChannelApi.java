package me.qscbm.DodoOpenJava.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.qscbm.DodoOpenJava.Utils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

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
        return getChannelList(Utils.Authorization(clientId,token), islandId, returnJSONText);
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
        String Parm = Utils.sendRequest(parm, url, Authorization);
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
                String channelType = null;
                switch(channelTypeInt) {
                    case 1:
                        channelType = "文字频道";
                        break;
                    case 2:
                        channelType = "语音频道";
                        break;
                    case  4:
                        channelType = "帖子频道";
                        break;
                    case 5:
                        channelType = "链接频道";
                        break;
                    case 6:
                        channelType = "资料频道";
                }
                int defaultFlagInt = JSONObject.parseObject(JSONText).getIntValue("defaultFlag");
                String defaultFlag = null;
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
        return getChannelInfo(Utils.Authorization(clientId, token), channelId, returnJSONText);
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
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            JSONObject JSONText = JSONObject.parseObject(Parm).getJSONObject("data");
            String ChannelId = JSONText.getString("channelId");
            String channelName = JSONText.getString("channelName");
            int channelTypeInt = JSONText.getIntValue("channelType");
            String channelType = null;
            switch(channelTypeInt) {
                case 1:
                    channelType = "文字频道";
                    break;
                case 2:
                    channelType = "语音频道";
                    break;
                case  4:
                    channelType = "帖子频道";
                    break;
                case 5:
                    channelType = "链接频道";
                    break;
                case 6:
                    channelType = "资料频道";
            }
            int defaultFlagInt = JSONText.getIntValue("defaultFlag");
            String defaultFlag = null;
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
}
