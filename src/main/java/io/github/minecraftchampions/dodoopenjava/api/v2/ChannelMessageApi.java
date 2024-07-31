package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.message.card.CardMessage;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * 频道消息API
 *
 * @author qscbm187531
 * @author zimzaza4
 */
@Data
@RequiredArgsConstructor
public class ChannelMessageApi {
    @NonNull
    private Bot bot;

    /**
     * 发送文本消息
     *
     * @param content   发送的消息
     * @param channelId 频道号
     * @return result
     */
    public Result sendTextMessage(String channelId, String content) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageType", 1)
                .put("channelId", channelId)
                .put("messageBody", Map.of(
                        "content", content
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送@消息
     *
     * @param channelId 频道id
     * @param dodoId    被@的dodoId
     * @param message   消息
     * @return result
     * @ 异常后抛出
     */
    public Result sendAtTextMessage(String channelId, String dodoId, String message) {
        String text = "<@!" + dodoId + "> " + message;
        return sendTextMessage(channelId, text);
    }


    /**
     * 置顶消息
     *
     * @param operateType 操作类型，0：取消置顶，1：置顶
     * @param messageId   消息id
     * @return result
     */
    public Result setChannelMessageTop(String messageId, int operateType) {
        String url = DodoOpenJava.BASEURL + "channel/message/top";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("operateType", operateType);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取消息反应列表
     *
     * @param messageId 消息id
     * @return result
     */
    public Result getChannelMessageReactionList(String messageId) {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 获取消息反应内成员列表
     *
     * @param messageId 消息id
     * @return result
     */
    public Result getChannelMessageReactionMemberList(String messageId, int type,
                                                      String id, int pageSize, long maxId) {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/member/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("pageSize", pageSize)
                .put("maxId", maxId)
                .put("emoji", Map.of(
                        "type", type,
                        "id", id
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }


    /**
     * 回复消息
     *
     * @param content             发送的消息
     * @param channelId           频道号
     * @param referencedMessageId 回复的消息ID
     * @return result
     */
    public Result referencedMessage(String channelId, String content,
                                    String referencedMessageId) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 1)
                .put("messageBody", Map.of(
                        "content", content,
                        "referencedMessageId", referencedMessageId
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 回复消息
     *
     * @param message             发送的消息
     * @param channelId           频道号
     * @param referencedMessageId 回复的消息ID
     * @return result
     */
    public Result referencedMessage(String channelId, Message message,
                                    String referencedMessageId) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 1)
                .put("messageBody", message);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送图片消息
     *
     * @param channelId  频道号
     * @param imageUrl   图片url地址
     * @param height     图片高度
     * @param width      图片宽度
     * @param isOriginal 是否原图
     * @return result
     */
    public Result sendChannelPictureMessage(String channelId, String imageUrl, int width,
                                            int height, Boolean isOriginal) {
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
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送图片消息
     *
     * @param channelId 频道号
     * @param imageUrl  图片url地址
     * @param height    图片高度
     * @param width     图片宽度
     * @return result
     */
    public Result sendChannelPictureMessage(String channelId, String imageUrl, int width,
                                            int height) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 2)
                .put("messageBody", Map.of(
                        "url", imageUrl,
                        "width", width,
                        "height", height
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送图片消息
     *
     * @param channelId 频道号
     * @param file      图片文件
     * @return result
     * @ 异常后抛出
     */
    public Result sendChannelPictureMessage(String channelId, File file) {
        if (!file.exists()) {
            throw new RuntimeException("传入的文件不存在");
        }
        JSONObject jsonObject = bot.getApi().getResourceApi().uploadResource(file.getPath()).getData();
        int width = jsonObject.getInt("width");
        int height = jsonObject.getInt("height");
        String url = jsonObject.getString("url");
        return sendChannelPictureMessage(channelId, url, width, height);
    }

    /**
     * 发送视频消息
     *
     * @param channelId 频道号
     * @param videoUrl  视频url地址
     * @return result
     */
    public Result sendChannelVideoMessage(String channelId, String videoUrl) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 3)
                .put("messageBody", Map.of(
                        "url", videoUrl
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送视频消息
     *
     * @param channelId 频道号
     * @param videoUrl  视频url地址
     * @param coverUrl  封面url地址
     * @param duration  视频长度
     * @param size      视频大小
     * @return result
     */
    public Result sendChannelVideoMessage(String channelId, String videoUrl,
                                          String coverUrl, long duration, long size) {
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
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送链接分享消息
     *
     * @param channelId 频道号
     * @param jumpUrl   跳转的url地址
     * @return result
     */
    public Result sendChannelShareMessage(String channelId, String jumpUrl) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 4)
                .put("messageBody", Map.of(
                        "jumpUrl", jumpUrl
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送文件消息
     *
     * @param channelId 频道号
     * @param fileUrl   文件链接
     * @param name      文件名称
     * @param size      文件大小
     * @return result
     */
    public Result sendChannelFileMessage(String channelId, String fileUrl, String name,
                                         long size) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 5)
                .put("messageBody", Map.of(
                        "url", fileUrl,
                        "name", name,
                        "size", size
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 编辑文字消息
     *
     * @param messageId 待编辑的消息ID
     * @param content   文字消息
     * @return result
     */
    public Result editChannelMessage(String messageId, String content) {
        String url = DodoOpenJava.BASEURL + "channel/message/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("messageBody", Map.of(
                        "content", content
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 编辑消息
     *
     * @param messageId 待编辑的消息ID
     * @param message   message
     * @return result
     */
    public Result editChannelMessage(String messageId, Message message) {
        String url = DodoOpenJava.BASEURL + "channel/message/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("messageBody", message.toMessage());
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 撤回消息
     *
     * @param messageId 消息ID
     * @return result
     */
    public Result withdrawChannelMessage(String messageId) {
        String url = DodoOpenJava.BASEURL + "channel/message/withdraw";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 撤回消息
     *
     * @param messageId 消息ID
     * @param reason    撤回理由，理由不能大于64个字符或32个汉字
     * @return result
     */
    public Result withdrawChannelMessageWithReason(String messageId, String reason) {
        String url = DodoOpenJava.BASEURL + "channel/message/withdraw";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("reason", reason);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 添加表情反应
     *
     * @param messageId 消息ID
     * @param id        表情ID
     * @return result
     */
    public Result addChannelMessageReaction(String messageId, String id) {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/add";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("emoji", Map.of(
                        "type", 1,
                        "id", id
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 取消指定消息中的指定用户的表情反应
     *
     * @param messageId    消息ID
     * @param id           表情ID
     * @param dodoSourceId 用户dodoSourceId
     * @return result
     */
    public Result removeChannelMessageReaction(String messageId, String id,
                                               String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("dodoSourceId", dodoSourceId)
                .put("emoji", Map.of(
                        "type", 1,
                        "id", id
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 取消机器人在某条消息的表情反应
     *
     * @param messageId 消息ID
     * @param id        表情ID
     * @return result
     */
    public Result removeChannelMessageBotReaction(String messageId, String id) {
        String url = DodoOpenJava.BASEURL + "channel/message/reaction/remove";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("emoji", Map.of(
                        "type", 1,
                        "id", id
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送卡片消息
     *
     * @param channelId   频道号
     * @param messageBody 卡片代码
     * @return result
     */
    public Result sendCardMessage(String channelId, CardMessage messageBody) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", 6)
                .put("messageBody", messageBody.toMessage());
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送消息
     *
     * @param channelId   频道号
     * @param messageBody 消息
     * @return result
     */
    public Result sendMessage(String channelId, Message messageBody) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", messageBody.getType())
                .put("messageBody", messageBody.toMessage());
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送频道私信消息
     *
     * @param channelId    频道号
     * @param messageBody  消息
     * @param dodoSourceId id
     * @return result
     */
    public Result sendChannelPersonalMessage(String channelId, Message messageBody,
                                             String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channelId", channelId)
                .put("messageType", messageBody.getType())
                .put("messageBody", messageBody.toMessage())
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 编辑卡片消息
     *
     * @param messageId   待编辑的消息ID
     * @param messageBody 卡片代码
     * @return result
     */
    public Result editChannelCardMessage(String messageId, CardMessage messageBody) {
        String url = DodoOpenJava.BASEURL + "channel/message/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId", messageId)
                .put("messageBody", messageBody.toString());
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
