package me.qscbm.DodoOpenJava.api;

import com.alibaba.fastjson.JSONObject;
import me.qscbm.DodoOpenJava.Utils;

import java.io.IOException;

public class ChannelTextApi {
    public static String url,parm;

    /**
     * 发送文本消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param Message 发送的消息
     * @param channelId 频道号
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelMessageSend(String clientId, String token, String channelId, String Message, Boolean returnJSONText) throws IOException {
        return setChannelMessageSend(Utils.Authorization(clientId,token), channelId, Message, returnJSONText);
    }

    /**
     * 发送文本消息
     *
     * @param Authorization Authorization
     * @param Message 发送的消息
     * @param channelId 频道号
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelMessageSend(String Authorization, String channelId, String Message, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/send";
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"messageType\": 1,\n" +
                "    \"messageBody\": {\n" +
                "        \"content\": \"" + Message + "\"\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String MessageID = JSONObject.parseObject(Parm).getJSONObject("data").getString("messageId");
            Parm = MessageID;
        }
        return Parm;
    }

    /**
     * 回复消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param Message 发送的消息
     * @param referencedMessageId 回复的消息ID
     * @param channelId 频道号
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String referencedMessage(String clientId, String token, String channelId, String Message, String referencedMessageId, Boolean returnJSONText) throws IOException {
        return referencedMessage(Utils.Authorization(clientId,token), channelId, Message, referencedMessageId, returnJSONText);
    }

    /**
     * 回复消息
     *
     * @param Authorization Authorization
     * @param Message 发送的消息
     * @param channelId 频道号
     * @param referencedMessageId 回复的消息ID
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String referencedMessage(String Authorization, String channelId, String Message,  String referencedMessageId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/send";
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"messageType\": 1,\n" +
                "    \"messageBody\": {\n" +
                "        \"content\": \"" + Message + "\"\n" +
                "        \"referencedMessageId\": \"" + referencedMessageId + "\"\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String MessageID = JSONObject.parseObject(Parm).getJSONObject("data").getString("messageId");
            Parm = MessageID;
        }
        return Parm;
    }

    /**
     * 发送图片消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param channelId 频道号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param isOriginal 是否原图
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelPictureMessageSend(String clientId, String token, String channelId, String Url, int width, int height, Boolean isOriginal, Boolean returnJSONText) throws IOException {
        return setChannelPictureMessageSend(Utils.Authorization(clientId,token), channelId, Url, width, height, isOriginal, returnJSONText);
    }

    /**
     * 发送图片消息
     *
     * @param Authorization Authorization
     * @param channelId 频道号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param isOriginal 是否原图
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelPictureMessageSend(String Authorization, String channelId, String Url, int width, int height, Boolean isOriginal, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/send";
        int Original;
        if (isOriginal) {
            Original = 1;
        } else {
            Original = 0;
        }
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"messageType\": 2,\n" +
                "    \"messageBody\": {\n" +
                "        \"url\": \"" + Url + "\",\n" +
                "        \"width\": " + width + ",\n" +
                "        \"height\": " + height + ",\n" +
                "        \"isOriginal\": " + Original + "\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String MessageID = JSONObject.parseObject(Parm).getJSONObject("data").getString("messageId");
            Parm = MessageID;
        }
        return Parm;
    }

    /**
     * 发送图片消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param channelId 频道号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelPictureMessageSend(String clientId, String token, String channelId, String Url, int width, int height, Boolean returnJSONText) throws IOException {
        return setChannelPictureMessageSend(Utils.Authorization(clientId,token), channelId, Url, width, height, returnJSONText);
    }

    /**
     * 发送图片消息
     *
     * @param Authorization Authorization
     * @param channelId 频道号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelPictureMessageSend(String Authorization, String channelId, String Url, int width, int height,  Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/send";
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"messageType\": 2,\n" +
                "    \"messageBody\": {\n" +
                "        \"url\": \"" + Url + "\",\n" +
                "        \"width\": " + width + ",\n" +
                "        \"height\": " + height + "\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String MessageID = JSONObject.parseObject(Parm).getJSONObject("data").getString("messageId");
            Parm = MessageID;
        }
        return Parm;
    }

    /**
     * 发送视频消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param channelId 频道号
     * @param Url 视频url地址
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelVideoMessageSend(String clientId, String token, String channelId, String Url, Boolean returnJSONText) throws IOException {
        return setChannelVideoMessageSend(Utils.Authorization(clientId,token), channelId, Url, returnJSONText);
    }

    /**
     * 发送视频消息
     *
     * @param Authorization Authorization
     * @param channelId 频道号
     * @param Url 视频url地址
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelVideoMessageSend(String Authorization, String channelId, String Url, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/send";
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"messageType\": 3,\n" +
                "    \"messageBody\": {\n" +
                "        \"url\": \"" + Url + "\"\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String MessageID = JSONObject.parseObject(Parm).getJSONObject("data").getString("messageId");
            Parm = MessageID;
        }
        return Parm;
    }

    /**
     * 发送视频消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param channelId 频道号
     * @param Url 视频url地址
     * @param coverUrl 封面url地址
     * @param duration 视频长度
     * @param size 视频大小
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelVideoMessageSend(String clientId, String token, String channelId, String Url, String coverUrl, long duration, long size, Boolean returnJSONText) throws IOException {
        return setChannelVideoMessageSend(Utils.Authorization(clientId,token), channelId, Url, coverUrl, duration, size, returnJSONText);
    }

    /**
     * 发送视频消息
     *
     * @param Authorization Authorization
     * @param channelId 频道号
     * @param Url 视频url地址
     * @param coverUrl 封面url地址
     * @param duration 视频长度
     * @param size 视频大小
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelVideoMessageSend(String Authorization, String channelId, String Url, String coverUrl, long duration, long size, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/send";
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"messageType\": 3,\n" +
                "    \"messageBody\": {\n" +
                "        \"url\": \"" + Url + "\",\n" +
                "        \"coverUrl\": \"" + coverUrl + "\",\n" +
                "        \"duration\": " + duration + ",\n" +
                "        \"size\": " + size + "\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String MessageID = JSONObject.parseObject(Parm).getJSONObject("data").getString("messageId");
            Parm = MessageID;
        }
        return Parm;
    }

    /**
     * 发送链接分享消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param channelId 频道号
     * @param jumpUrl 跳转的url地址
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelShareMessageSend(String clientId, String token, String channelId, String jumpUrl, Boolean returnJSONText) throws IOException {
        return setChannelShareMessageSend(Utils.Authorization(clientId,token), channelId, jumpUrl, returnJSONText);
    }

    /**
     * 发送链接分享消息
     *
     * @param Authorization Authorization
     * @param channelId 频道号
     * @param jumpUrl 跳转的url地址
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelShareMessageSend(String Authorization, String channelId, String jumpUrl, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/send";
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"messageType\": 4,\n" +
                "    \"messageBody\": {\n" +
                "        \"jumpUrl\": \"" + jumpUrl + "\"\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String MessageID = JSONObject.parseObject(Parm).getJSONObject("data").getString("messageId");
            Parm = MessageID;
        }
        return Parm;
    }

    /**
     * 发送链接分享消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param channelId 频道号
     * @param Url 文件链接
     * @param name 文件名称
     * @param size 文件大小
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelFileMessageSend(String clientId, String token, String channelId, String Url, String name, long size, Boolean returnJSONText) throws IOException {
        return setChannelFileMessageSend(Utils.Authorization(clientId,token), channelId, Url, name, size, returnJSONText);
    }

    /**
     * 发送链接分享消息
     *
     * @param Authorization Authorization
     * @param channelId 频道号
     * @param Url 文件链接
     * @param name 文件名称
     * @param size 文件大小
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setChannelFileMessageSend(String Authorization, String channelId, String Url, String name, long size, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/send";
        parm = "{\n" +
                "    \"channelId\": \"" + channelId + "\",\n" +
                "    \"messageType\": 5,\n" +
                "    \"messageBody\": {\n" +
                "        \"url\": \"" + Url + "\",\n" +
                "        \"name\": \"" + name + "\",\n" +
                "        \"size\": " + size + "\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            String MessageID = JSONObject.parseObject(Parm).getJSONObject("data").getString("messageId");
            Parm = MessageID;
        }
        return Parm;
    }

    /**
     * 编辑消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param messageId 待编辑的消息ID
     * @param content 文字消息
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageEdit(String clientId, String token, String messageId, String content, Boolean returnJSONText) throws IOException {
        return setChannelMessageEdit(Utils.Authorization(clientId,token), messageId, content, returnJSONText);
    }

    /**
     * 编辑消息
     *
     * @param Authorization Authorization
     * @param messageId 待编辑的消息ID
     * @param content 文字消息
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageEdit(String Authorization, String messageId, String content, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/edit";
        parm = "{\n" +
                "    \"messageId\": \"" + messageId	 + "\",\n" +
                "    \"messageType\": 1,\n" +
                "    \"messageBody\": {\n" +
                "        \"content\": \"" + content + "\"\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 编辑消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param messageId 消息ID
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageWithdraw(String clientId, String token, String messageId, Boolean returnJSONText) throws IOException {
        return setChannelMessageWithdraw(Utils.Authorization(clientId,token), messageId, returnJSONText);
    }

    /**
     * 编辑消息
     *
     * @param Authorization Authorization
     * @param messageId 消息ID
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageWithdraw(String Authorization, String messageId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/withdraw";
        parm = "{\n" +
                "    \"messageId\": \"" + messageId + "\"\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 编辑消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param messageId 消息ID
     * @param reason 撤回理由，理由不能大于64个字符或32个汉字
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageWithdrawWithReason(String clientId, String token, String messageId, String reason, Boolean returnJSONText) throws IOException {
        return setChannelMessageWithdrawWithReason(Utils.Authorization(clientId,token), messageId, reason, returnJSONText);
    }

    /**
     * 编辑消息
     *
     * @param Authorization Authorization
     * @param messageId 消息ID
     * @param reason 撤回理由，理由不能大于64个字符或32个汉字
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageWithdrawWithReason(String Authorization, String messageId, String reason, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/withdraw";
        parm = "{\n" +
                "    \"messageId\": \"" + messageId + "\",\n" +
                "    \"reason\": \"" + reason + "\"\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 添加表情反应
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param messageId 消息ID
     * @param id 表情ID
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageReactionAdd(String clientId, String token, String messageId, String id, Boolean returnJSONText) throws IOException {
        return setChannelMessageReactionAdd(Utils.Authorization(clientId,token), messageId, id, returnJSONText);
    }

    /**
     * 添加表情反应
     *
     * @param Authorization Authorization
     * @param messageId 消息ID
     * @param id 表情ID
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageReactionAdd(String Authorization, String messageId, String id, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/reaction/add";
        parm = "{\n" +
                "    \"messageId\": \"" + messageId + "\",\n" +
                "    \"emoji\": {\n" +
                "        \"type\": 1,\n" +
                "        \"id\": \"" + id + "\"\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 取消指定消息中的指定用户的表情反应
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param messageId 消息ID
     * @param id 表情ID
     * @param dodoId 用户DodoID
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageReactionRemove(String clientId, String token, String messageId, String id, String dodoId, Boolean returnJSONText) throws IOException {
        return setChannelMessageReactionRemove(Utils.Authorization(clientId,token), messageId, id, dodoId, returnJSONText);
    }

    /**
     * 取消指定消息中的指定用户的表情反应
     *
     * @param Authorization Authorization
     * @param messageId 消息ID
     * @param id 表情ID
     * @param dodoId 用户DodoID
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageReactionRemove(String Authorization, String messageId, String id, String dodoId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/reaction/remove";
        parm = "{\n" +
                "    \"messageId\": \"" + messageId + "\",\n" +
                "    \"emoji\": {\n" +
                "        \"type\": 1,\n" +
                "        \"id\": \"" + id + "\",\n" +
                "    },\n" +
                "    \"dodoId\": \"" + dodoId + "\"\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }

    /**
     * 取消机器人在某条消息的表情反应
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param messageId 消息ID
     * @param id 表情ID
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageBotReactionRemove(String clientId, String token, String messageId, String id, String dodoId, Boolean returnJSONText) throws IOException {
        return setChannelMessageBotReactionRemove(Utils.Authorization(clientId,token), messageId, id, dodoId, returnJSONText);
    }

    /**
     * 取消机器人在某条消息的表情反应
     *
     * @param Authorization Authorization
     * @param messageId 消息ID
     * @param id 表情ID
     * @param returnJSONText 是否返回json文本
     */
    public static String setChannelMessageBotReactionRemove(String Authorization, String messageId, String id, String dodoId, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/message/reaction/remove";
        parm = "{\n" +
                "    \"messageId\": \"" + messageId + "\",\n" +
                "    \"emoji\": {\n" +
                "        \"type\": 1,\n" +
                "        \"id\": \"" + id + "\",\n" +
                "    }\n" +
                "}";
        String Parm = Utils.sendRequest(parm, url, Authorization);
        if (!returnJSONText) {
            Parm = null;
        }
        return Parm;
    }
}