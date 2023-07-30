package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.card.Card;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 频道消息API
 */
public class ChannelMessageApi {
    public static String url, param;

    /**
     * 发送文本消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param Message   发送的消息
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendTextMessage(String clientId, String token, String channelId, String Message) throws IOException {
        return sendTextMessage(BaseUtil.Authorization(clientId, token), channelId, Message);
    }

    /**
     * 发送文本消息
     *
     * @param Authorization Authorization
     * @param Message       发送的消息
     * @param channelId     频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendTextMessage(String Authorization, String channelId, String Message) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": {" +
                "        \"content\": \"" + Message + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 置顶消息
     *
     * @param clientId    机器人唯一标识
     * @param token       机器人鉴权Token
     * @param operateType 操作类型，0：取消置顶，1：置顶
     * @param messageId   消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject setChannelMessageTop(String clientId, String token, String messageId, int operateType) throws IOException {
        return setChannelMessageTop(BaseUtil.Authorization(clientId, token), messageId, operateType);
    }

    /**
     * 置顶消息
     *
     * @param Authorization Authorization
     * @param operateType   操作类型，0：取消置顶，1：置顶
     * @param messageId     消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject setChannelMessageTop(String Authorization, String messageId, int operateType) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/top";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"," +
                "    \"operateType\": " + operateType + "" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 获取消息反应列表
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param messageId 消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelMessageReactionList(String clientId, String token, String messageId) throws IOException {
        return getChannelMessageReactionList(BaseUtil.Authorization(clientId, token), messageId);
    }

    /**
     * 获取消息反应列表
     *
     * @param Authorization Authorization
     * @param messageId     消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelMessageReactionList(String Authorization, String messageId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/reaction/list";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 获取消息反应内成员列表
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param messageId 消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelMessageReactionMemberList(String clientId, String token, String messageId, int type, String id, int pageSize, long maxId) throws IOException {
        return getChannelMessageReactionMemberList(BaseUtil.Authorization(clientId, token), messageId, type, id, pageSize, maxId);
    }

    /**
     * 获取消息反应内成员列表
     *
     * @param Authorization Authorization
     * @param messageId     消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getChannelMessageReactionMemberList(String Authorization, String messageId, int type, String id, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/reaction/member/list";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\",\n" +
                "    \"emoji\": {\n" +
                "        \"type\": " + type + ",\n" +
                "        \"id\": \"" + id + "\"\n" +
                "    },\n" +
                "    \"pageSize\": " + pageSize + ",\n" +
                "    \"maxId\": " + maxId +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 回复消息
     *
     * @param clientId            机器人唯一标识
     * @param token               机器人鉴权Token
     * @param Message             发送的消息
     * @param referencedMessageId 回复的消息ID
     * @param channelId           频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject referencedMessage(String clientId, String token, String channelId, String Message, String referencedMessageId) throws IOException {
        return referencedMessage(BaseUtil.Authorization(clientId, token), channelId, Message, referencedMessageId);
    }

    /**
     * 回复消息
     *
     * @param Authorization       Authorization
     * @param Message             发送的消息
     * @param channelId           频道号
     * @param referencedMessageId 回复的消息ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject referencedMessage(String Authorization, String channelId, String Message, String referencedMessageId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": {" +
                "        \"content\": \"" + Message + "\"" +
                "        \"referencedMessageId\": \"" + referencedMessageId + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 发送图片消息
     *
     * @param clientId   机器人唯一标识
     * @param token      机器人鉴权Token
     * @param channelId  频道号
     * @param Url        图片url地址
     * @param height     图片高度
     * @param width      图片宽度
     * @param isOriginal 是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelPictureMessage(String clientId, String token, String channelId, String Url, int width, int height, Boolean isOriginal) throws IOException {
        return sendChannelPictureMessage(BaseUtil.Authorization(clientId, token), channelId, Url, width, height, isOriginal);
    }

    /**
     * 发送图片消息
     *
     * @param Authorization Authorization
     * @param channelId     频道号
     * @param Url           图片url地址
     * @param height        图片高度
     * @param width         图片宽度
     * @param isOriginal    是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelPictureMessage(String Authorization, String channelId, String Url, int width, int height, Boolean isOriginal) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        int Original;
        if (isOriginal) {
            Original = 1;
        } else {
            Original = 0;
        }
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 2," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + Url + "\"," +
                "        \"width\": " + width + "," +
                "        \"height\": " + height + "," +
                "        \"isOriginal\": " + Original + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 发送图片消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param channelId 频道号
     * @param Url       图片url地址
     * @param height    图片高度
     * @param width     图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelPictureMessage(String clientId, String token, String channelId, String Url, int width, int height) throws IOException {
        return sendChannelPictureMessage(BaseUtil.Authorization(clientId, token), channelId, Url, width, height);
    }

    /**
     * 发送图片消息
     *
     * @param Authorization Authorization
     * @param channelId     频道号
     * @param Url           图片url地址
     * @param height        图片高度
     * @param width         图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelPictureMessage(String Authorization, String channelId, String Url, int width, int height) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 2," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + Url + "\"," +
                "        \"width\": " + width + "," +
                "        \"height\": " + height + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 发送视频消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param channelId 频道号
     * @param Url       视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelVideoMessage(String clientId, String token, String channelId, String Url) throws IOException {
        return sendChannelVideoMessage(BaseUtil.Authorization(clientId, token), channelId, Url);
    }

    /**
     * 发送视频消息
     *
     * @param Authorization Authorization
     * @param channelId     频道号
     * @param Url           视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelVideoMessage(String Authorization, String channelId, String Url) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 3," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + Url + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 发送视频消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param channelId 频道号
     * @param Url       视频url地址
     * @param coverUrl  封面url地址
     * @param duration  视频长度
     * @param size      视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelVideoMessage(String clientId, String token, String channelId, String Url, String coverUrl, long duration, long size) throws IOException {
        return sendChannelVideoMessage(BaseUtil.Authorization(clientId, token), channelId, Url, coverUrl, duration, size);
    }

    /**
     * 发送视频消息
     *
     * @param Authorization Authorization
     * @param channelId     频道号
     * @param Url           视频url地址
     * @param coverUrl      封面url地址
     * @param duration      视频长度
     * @param size          视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelVideoMessage(String Authorization, String channelId, String Url, String coverUrl, long duration, long size) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 3," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + Url + "\"," +
                "        \"coverUrl\": \"" + coverUrl + "\"," +
                "        \"duration\": " + duration + "," +
                "        \"size\": " + size + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 发送链接分享消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param channelId 频道号
     * @param jumpUrl   跳转的url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelShareMessage(String clientId, String token, String channelId, String jumpUrl) throws IOException {
        return sendChannelShareMessage(BaseUtil.Authorization(clientId, token), channelId, jumpUrl);
    }

    /**
     * 发送链接分享消息
     *
     * @param Authorization Authorization
     * @param channelId     频道号
     * @param jumpUrl       跳转的url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelShareMessage(String Authorization, String channelId, String jumpUrl) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 4," +
                "    \"messageBody\": {" +
                "        \"jumpUrl\": \"" + jumpUrl + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 发送文件消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param channelId 频道号
     * @param Url       文件链接
     * @param name      文件名称
     * @param size      文件大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelFileMessage(String clientId, String token, String channelId, String Url, String name, long size) throws IOException {
        return sendChannelFileMessage(BaseUtil.Authorization(clientId, token), channelId, Url, name, size);
    }

    /**
     * 发送文件消息
     *
     * @param Authorization Authorization
     * @param channelId     频道号
     * @param Url           文件链接
     * @param name          文件名称
     * @param size          文件大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendChannelFileMessage(String Authorization, String channelId, String Url, String name, long size) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 5," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + Url + "\"," +
                "        \"name\": \"" + name + "\"," +
                "        \"size\": " + size + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 编辑文字消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param messageId 待编辑的消息ID
     * @param content   文字消息
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelMessage(String clientId, String token, String messageId, String content) throws IOException {
        return editChannelMessage(BaseUtil.Authorization(clientId, token), messageId, content);
    }

    /**
     * 编辑文字消息
     *
     * @param Authorization Authorization
     * @param messageId     待编辑的消息ID
     * @param content       文字消息
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelMessage(String Authorization, String messageId, String content) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/edit";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": {" +
                "        \"content\": \"" + content + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 撤回消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param messageId 消息ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject withdrawChannelMessage(String clientId, String token, String messageId) throws IOException {
        return withdrawChannelMessage(BaseUtil.Authorization(clientId, token), messageId);
    }

    /**
     * 撤回消息
     *
     * @param Authorization Authorization
     * @param messageId     消息ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject withdrawChannelMessage(String Authorization, String messageId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/withdraw";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 撤回消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param messageId 消息ID
     * @param reason    撤回理由，理由不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject withdrawChannelMessageWithReason(String clientId, String token, String messageId, String reason) throws IOException {
        return withdrawChannelMessageWithReason(BaseUtil.Authorization(clientId, token), messageId, reason);
    }

    /**
     * 撤回消息
     *
     * @param Authorization Authorization
     * @param messageId     消息ID
     * @param reason        撤回理由，理由不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject withdrawChannelMessageWithReason(String Authorization, String messageId, String reason) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/withdraw";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"," +
                "    \"reason\": \"" + reason + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 添加表情反应
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param messageId 消息ID
     * @param id        表情ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannelMessageReaction(String clientId, String token, String messageId, String id) throws IOException {
        return addChannelMessageReaction(BaseUtil.Authorization(clientId, token), messageId, id);
    }

    /**
     * 添加表情反应
     *
     * @param Authorization Authorization
     * @param messageId     消息ID
     * @param id            表情ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject addChannelMessageReaction(String Authorization, String messageId, String id) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/reaction/add";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"," +
                "    \"emoji\": {" +
                "        \"type\": 1," +
                "        \"id\": \"" + id + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 取消指定消息中的指定用户的表情反应
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param messageId    消息ID
     * @param id           表情ID
     * @param dodoSourceId 用户dodoSourceId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeChannelMessageReaction(String clientId, String token, String messageId, String id, String dodoSourceId) throws IOException {
        return removeChannelMessageReaction(BaseUtil.Authorization(clientId, token), messageId, id, dodoSourceId);
    }

    /**
     * 取消指定消息中的指定用户的表情反应
     *
     * @param Authorization Authorization
     * @param messageId     消息ID
     * @param id            表情ID
     * @param dodoSourceId  用户dodoSourceId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeChannelMessageReaction(String Authorization, String messageId, String id, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/reaction/remove";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"," +
                "    \"emoji\": {" +
                "        \"type\": 1," +
                "        \"id\": \"" + id + "\"," +
                "    }," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 取消机器人在某条消息的表情反应
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param messageId 消息ID
     * @param id        表情ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeChannelMessageBotReaction(String clientId, String token, String messageId, String id) throws IOException {
        return removeChannelMessageBotReaction(BaseUtil.Authorization(clientId, token), messageId, id);
    }

    /**
     * 取消机器人在某条消息的表情反应
     *
     * @param Authorization Authorization
     * @param messageId     消息ID
     * @param id            表情ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject removeChannelMessageBotReaction(String Authorization, String messageId, String id) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/reaction/remove";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"," +
                "    \"emoji\": {" +
                "        \"type\": 1," +
                "        \"id\": \"" + id + "\"," +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 发送卡片消息
     *
     * @param clientId    机器人唯一标识
     * @param token       机器人鉴权Token
     * @param channelId   频道号
     * @param messageBody 卡片代码
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendCardMessage(String clientId, String token, String channelId, Card messageBody) throws IOException {
        return sendCardMessage(BaseUtil.Authorization(clientId, token), channelId, messageBody);
    }

    /**
     * 发送卡片消息
     *
     * @param Authorization Authorization
     * @param channelId     频道号
     * @param messageBody   卡片代码
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendCardMessage(String Authorization, String channelId, Card messageBody) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 6," +
                "    \"messageBody\": " + messageBody.toJSONObject().toString() +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 编辑卡片消息
     *
     * @param clientId    机器人唯一标识
     * @param token       机器人鉴权Token
     * @param messageId   待编辑的消息ID
     * @param messageBody 卡片代码
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelCardMessage(String clientId, String token, String messageId, Card messageBody) throws IOException {
        return editChannelCardMessage(BaseUtil.Authorization(clientId, token), messageId, messageBody);
    }

    /**
     * 编辑卡片消息
     *
     * @param Authorization Authorization
     * @param messageId     待编辑的消息ID
     * @param messageBody   卡片代码
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject editChannelCardMessage(String Authorization, String messageId, Card messageBody) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/edit";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": " + messageBody.toJSONObject().toString() +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

}