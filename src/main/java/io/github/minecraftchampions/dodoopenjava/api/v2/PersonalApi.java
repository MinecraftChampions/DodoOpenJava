package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

import java.util.Map;

/**
 * 私信API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class PersonalApi {
    @NonNull
    private Bot bot;

    /**
     * 发送文本消息
     *
     * @param message      发送的消息
     * @param dodoSourceId Dodo号
     * @return result
     */
    public Result sendPersonalMessage(String islandSourceId, String dodoSourceId,
                                      String message) {
        String url = DodoOpenJava.BASEURL + "personal/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", 1)
                .put("messageBody", Map.of("content", message));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送消息
     *
     * @param messageBody    消息
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @return result
     */
    public Result sendMessage(String islandSourceId,
                              String dodoSourceId, Message messageBody) {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject()
                .put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", messageBody.getType())
                .put("messageBody", messageBody.toMessage());
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送图片消息
     *
     * @param dodoSourceId dodo号
     * @param u            图片url地址
     * @param height       图片高度
     * @param width        图片宽度
     * @param isOriginal   是否原图
     * @return result
     */
    public Result sendDodoPictureMessage(String islandSourceId, String dodoSourceId,
                                         String u, int width, int height,
                                         boolean isOriginal) {
        String url = DodoOpenJava.BASEURL + "personal/message/send";
        int original;
        if (isOriginal) {
            original = 1;
        } else {
            original = 0;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", 2)
                .put("messageBody", Map.of(
                        "url", u,
                        "width", width,
                        "height", height,
                        "isOriginal", original));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送图片消息
     *
     * @param dodoSourceId dodo号
     * @param u            图片url地址
     * @param height       图片高度
     * @param width        图片宽度
     * @return result
     */
    public Result sendDodoPictureMessage(String islandSourceId, String dodoSourceId,
                                         String u, int width, int height) {
        String url = DodoOpenJava.BASEURL + "personal/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", 2)
                .put("messageBody", Map.of(
                        "url", u,
                        "width", width,
                        "height", height));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送视频消息
     *
     * @param dodoSourceId dodo号
     * @param u            视频url地址
     * @return result
     */
    public Result sendDodoVideoMessage(String islandSourceId, String dodoSourceId, String u) {
        String url = DodoOpenJava.BASEURL + "personal/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", 3)
                .put("messageBody", Map.of(
                        "url", u
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 发送视频消息
     *
     * @param dodoSourceId dodo号
     * @param u            视频url地址
     * @param coverUrl     封面url地址
     * @param duration     视频长度
     * @param size         视频大小
     * @return result
     */
    public Result sendDodoVideoMessage(String islandSourceId, String dodoSourceId,
                                       String u, String coverUrl, long duration, long size) {
        String url = DodoOpenJava.BASEURL + "personal/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", 3)
                .put("messageBody", Map.of(
                        "url", u,
                        "coverurl", coverUrl,
                        "duration", duration,
                        "size", size
                ));
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
