package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.message.card.CardMessage;
import io.github.minecraftchampions.dodoopenjava.message.text.TextMessage;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 频道消息API
 */
public class ChannelMessageApi {
    /**
     * 发送文本消息
     *
     * @param clientId  机器人唯一标识
     * @param token     机器人鉴权Token
     * @param message   发送的消息
     * @param channelId 频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendTextMessage(String clientId, String token, String channelId, String message) throws IOException {
        return sendTextMessage(BaseUtil.Authorization(clientId, token), channelId, message);
    }

    /**
     * 发送文本消息
     *
     * @param clientId    机器人唯一标识
     * @param token       机器人鉴权Token
     * @param textMessage 发送的消息
     * @param channelId   频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendTextMessage(String clientId, String token, String channelId, TextMessage textMessage) throws IOException {
        return sendTextMessage(BaseUtil.Authorization(clientId, token), channelId, textMessage);
    }

    /**
     * 发送文本消息
     *
     * @param authorization authorization
     * @param textMessage   发送的消息
     * @param channelId     频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendTextMessage(String authorization, String channelId, TextMessage textMessage) throws IOException {
        return sendTextMessage(authorization, channelId, textMessage.toString());
    }

    /**
     * 发送文本消息
     *
     * @param authorization authorization
     * @param content       发送的消息
     * @param channelId     频道号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendTextMessage(String authorization, String channelId, String content) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageType", 1)
                .put("channelId", channelId)
                .put("messageBody", Map.of(
                        "content", content
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 发送@消息
     *
     * @param authorization authorization
     * @param channelId     频道id
     * @param dodoId        被@的dodoId
     * @param message       消息
     * @return JSON对象
     * @throws IOException 异常后抛出
     */
    public static Result sendAtTextMessage(String authorization, String channelId, String dodoId, String message) throws IOException {
        String text = "<@!" + dodoId + "> " + message;
        return sendTextMessage(authorization, channelId, text);
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
    public static Result setChannelMessageTop(String clientId, String token, String messageId, int operateType) throws IOException {
        return setChannelMessageTop(BaseUtil.Authorization(clientId, token), messageId, operateType);
    }

    /**
     * 置顶消息
     *
     * @param authorization authorization
     * @param operateType   操作类型，0：取消置顶，1：置顶
     * @param messageId     消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result setChannelMessageTop(String authorization, String messageId, int operateType) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/top";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("operateType", operateType);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result getChannelMessageReactionList(String clientId, String token, String messageId) throws IOException {
        return getChannelMessageReactionList(BaseUtil.Authorization(clientId, token), messageId);
    }

    /**
     * 获取消息反应列表
     *
     * @param authorization authorization
     * @param messageId     消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getChannelMessageReactionList(String authorization, String messageId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result getChannelMessageReactionMemberList(String clientId, String token, String messageId, int type, String id, int pageSize, long maxId) throws IOException {
        return getChannelMessageReactionMemberList(BaseUtil.Authorization(clientId, token), messageId, type, id, pageSize, maxId);
    }

    /**
     * 获取消息反应内成员列表
     *
     * @param authorization authorization
     * @param messageId     消息id
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getChannelMessageReactionMemberList(String authorization, String messageId, int type, String id, int pageSize, long maxId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/member/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("pageSize", pageSize)
                .put("maxId", maxId)
                .put("emoji", Map.of(
                        "type", type,
                        "id", id
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result referencedMessage(String clientId, String token, String channelId, String Message, String referencedMessageId) throws IOException {
        return referencedMessage(BaseUtil.Authorization(clientId, token), channelId, Message, referencedMessageId);
    }

    /**
     * 回复消息
     *
     * @param authorization       authorization
     * @param content             发送的消息
     * @param channelId           频道号
     * @param referencedMessageId 回复的消息ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result referencedMessage(String authorization, String channelId, String content, String referencedMessageId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 1)
                .put("messageBody", Map.of(
                        "content", content,
                        "referencedMessageId", referencedMessageId
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result sendChannelPictureMessage(String clientId, String token, String channelId, String Url, int width, int height, Boolean isOriginal) throws IOException {
        return sendChannelPictureMessage(BaseUtil.Authorization(clientId, token), channelId, Url, width, height, isOriginal);
    }

    /**
     * 发送图片消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param imageUrl      图片url地址
     * @param height        图片高度
     * @param width         图片宽度
     * @param isOriginal    是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendChannelPictureMessage(String authorization, String channelId, String imageUrl, int width, int height, Boolean isOriginal) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        int original;
        if (isOriginal) {
            original = 1;
        } else {
            original = 0;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 2)
                .put("messageBody", Map.of(
                        "url", imageUrl,
                        "width", width,
                        "height", height,
                        "isOriginal", original
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result sendChannelPictureMessage(String clientId, String token, String channelId, String Url, int width, int height) throws IOException {
        return sendChannelPictureMessage(BaseUtil.Authorization(clientId, token), channelId, Url, width, height);
    }

    /**
     * 发送图片消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param imageUrl      图片url地址
     * @param height        图片高度
     * @param width         图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendChannelPictureMessage(String authorization, String channelId, String imageUrl, int width, int height) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 2)
                .put("messageBody", Map.of(
                        "url", imageUrl,
                        "width", width,
                        "height", height
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 发送图片消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param file          图片文件
     * @return JSON对象
     * @throws IOException 异常后抛出
     */
    public static Result sendChannelPictureMessage(String authorization, String channelId, File file) throws IOException {
        if (!file.exists()) {
            throw new RuntimeException("传入的文件不存在");
        }
        JSONObject jsonObject = ResourceApi.uploadResource(authorization, file.getPath()).getJSONObjectData();
        int width = jsonObject.getInt("width");
        int height = jsonObject.getInt("height");
        String url = jsonObject.getString("url");
        return sendChannelPictureMessage(authorization, channelId, url, width, height);
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
    public static Result sendChannelVideoMessage(String clientId, String token, String channelId, String Url) throws IOException {
        return sendChannelVideoMessage(BaseUtil.Authorization(clientId, token), channelId, Url);
    }

    /**
     * 发送视频消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param videoUrl      视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendChannelVideoMessage(String authorization, String channelId, String videoUrl) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 3)
                .put("messageBody", Map.of(
                        "url", videoUrl
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result sendChannelVideoMessage(String clientId, String token, String channelId, String Url, String coverUrl, long duration, long size) throws IOException {
        return sendChannelVideoMessage(BaseUtil.Authorization(clientId, token), channelId, Url, coverUrl, duration, size);
    }

    /**
     * 发送视频消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param videoUrl      视频url地址
     * @param coverUrl      封面url地址
     * @param duration      视频长度
     * @param size          视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendChannelVideoMessage(String authorization, String channelId, String videoUrl, String coverUrl, long duration, long size) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 3)
                .put("messageBody", Map.of(
                        "url", videoUrl,
                        "coverUrl", coverUrl,
                        "duration", duration,
                        "size", size
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result sendChannelShareMessage(String clientId, String token, String channelId, String jumpUrl) throws IOException {
        return sendChannelShareMessage(BaseUtil.Authorization(clientId, token), channelId, jumpUrl);
    }

    /**
     * 发送链接分享消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param jumpUrl       跳转的url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendChannelShareMessage(String authorization, String channelId, String jumpUrl) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 4)
                .put("messageBody", Map.of(
                        "jumpUrl", jumpUrl
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result sendChannelFileMessage(String clientId, String token, String channelId, String Url, String name, long size) throws IOException {
        return sendChannelFileMessage(BaseUtil.Authorization(clientId, token), channelId, Url, name, size);
    }

    /**
     * 发送文件消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param fileUrl       文件链接
     * @param name          文件名称
     * @param size          文件大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendChannelFileMessage(String authorization, String channelId, String fileUrl, String name, long size) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 5)
                .put("messageBody", Map.of(
                        "url", fileUrl,
                        "name", name,
                        "size", size
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result editChannelMessage(String clientId, String token, String messageId, String content) throws IOException {
        return editChannelMessage(BaseUtil.Authorization(clientId, token), messageId, content);
    }

    /**
     * 编辑文字消息
     *
     * @param authorization authorization
     * @param messageId     待编辑的消息ID
     * @param content       文字消息
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editChannelMessage(String authorization, String messageId, String content) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("messageBody", Map.of(
                        "content", content
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result withdrawChannelMessage(String clientId, String token, String messageId) throws IOException {
        return withdrawChannelMessage(BaseUtil.Authorization(clientId, token), messageId);
    }

    /**
     * 撤回消息
     *
     * @param authorization authorization
     * @param messageId     消息ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result withdrawChannelMessage(String authorization, String messageId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/withdraw";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result withdrawChannelMessageWithReason(String clientId, String token, String messageId, String reason) throws IOException {
        return withdrawChannelMessageWithReason(BaseUtil.Authorization(clientId, token), messageId, reason);
    }

    /**
     * 撤回消息
     *
     * @param authorization authorization
     * @param messageId     消息ID
     * @param reason        撤回理由，理由不能大于64个字符或32个汉字
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result withdrawChannelMessageWithReason(String authorization, String messageId, String reason) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/withdraw";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("reason", reason);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result addChannelMessageReaction(String clientId, String token, String messageId, String id) throws IOException {
        return addChannelMessageReaction(BaseUtil.Authorization(clientId, token), messageId, id);
    }

    /**
     * 添加表情反应
     *
     * @param authorization authorization
     * @param messageId     消息ID
     * @param id            表情ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result addChannelMessageReaction(String authorization, String messageId, String id) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("emoji", Map.of(
                        "type", 1,
                        "id", id
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result removeChannelMessageReaction(String clientId, String token, String messageId, String id, String dodoSourceId) throws IOException {
        return removeChannelMessageReaction(BaseUtil.Authorization(clientId, token), messageId, id, dodoSourceId);
    }

    /**
     * 取消指定消息中的指定用户的表情反应
     *
     * @param authorization authorization
     * @param messageId     消息ID
     * @param id            表情ID
     * @param dodoSourceId  用户dodoSourceId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result removeChannelMessageReaction(String authorization, String messageId, String id, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("dodoSourceId", dodoSourceId)
                .put("emoji", Map.of(
                        "type", 1,
                        "id", id
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result removeChannelMessageBotReaction(String clientId, String token, String messageId, String id) throws IOException {
        return removeChannelMessageBotReaction(BaseUtil.Authorization(clientId, token), messageId, id);
    }

    /**
     * 取消机器人在某条消息的表情反应
     *
     * @param authorization authorization
     * @param messageId     消息ID
     * @param id            表情ID
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result removeChannelMessageBotReaction(String authorization, String messageId, String id) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("emoji", Map.of(
                        "type", 1,
                        "id", id
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result sendCardMessage(String clientId, String token, String channelId, CardMessage messageBody) throws IOException {
        return sendCardMessage(BaseUtil.Authorization(clientId, token), channelId, messageBody);
    }

    /**
     * 发送卡片消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param messageBody   卡片代码
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendCardMessage(String authorization, String channelId, CardMessage messageBody) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 6)
                .put("messageBody", messageBody.toMessage());
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 发送消息
     *
     * @param clientId    机器人唯一标识
     * @param token       机器人鉴权Token
     * @param channelId   频道号
     * @param messageBody 消息
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendMessage(String clientId, String token, String channelId, Message messageBody) throws IOException {
        return sendMessage(BaseUtil.Authorization(clientId, token), channelId, messageBody);
    }

    /**
     * 发送消息
     *
     * @param authorization authorization
     * @param channelId     频道号
     * @param messageBody   消息
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendMessage(String authorization, String channelId, Message messageBody) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", messageBody.getType())
                .put("messageBody", messageBody.toMessage());
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result editChannelCardMessage(String clientId, String token, String messageId, CardMessage messageBody) throws IOException {
        return editChannelCardMessage(BaseUtil.Authorization(clientId, token), messageId, messageBody);
    }

    /**
     * 编辑卡片消息
     *
     * @param authorization authorization
     * @param messageId     待编辑的消息ID
     * @param messageBody   卡片代码
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result editChannelCardMessage(String authorization, String messageId, CardMessage messageBody) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("messageBody", messageBody.toString());
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

}
