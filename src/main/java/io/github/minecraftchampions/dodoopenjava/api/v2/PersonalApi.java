package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * 私信API
 *
 * @author qscbm187531
 */
public class PersonalApi {
    /**
     * 发送文本消息
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param message      发送的消息
     * @param dodoSourceId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendPersonalMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String message) throws IOException {
        return sendPersonalMessage(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, message);
    }

    /**
     * 发送文本消息
     *
     * @param authorization authorization
     * @param message       发送的消息
     * @param dodoSourceId  Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendPersonalMessage(String authorization, String islandSourceId, String dodoSourceId, String message) throws IOException {
        String url = DodoOpenJava.BASEURL + "personal/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", 1)
                .put("messageBody", Map.of("content", message));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 发送消息
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param messageBody    消息
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendMessage(String clientId, String token, String islandSourceId, String dodoSourceId, Message messageBody) throws IOException {
        return sendMessage(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, messageBody);
    }

    /**
     * 发送消息
     *
     * @param authorization  authorization
     * @param messageBody    消息
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendMessage(String authorization, String islandSourceId, String dodoSourceId, Message messageBody) throws IOException {
        String url = DodoOpenJava.BASEURL + "channel/message/send";
        JSONObject jsonObject = new JSONObject()
                .put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", messageBody.getType())
                .put("messageBody", messageBody.toMessage());
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 发送图片消息
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url          图片url地址
     * @param height       图片高度
     * @param width        图片宽度
     * @param isOriginal   是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendDodoPictureMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String url, int width, int height, Boolean isOriginal) throws IOException {
        return sendDodoPictureMessage(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, url, width, height, isOriginal);
    }

    /**
     * 发送图片消息
     *
     * @param authorization authorization
     * @param dodoSourceId  dodo号
     * @param u             图片url地址
     * @param height        图片高度
     * @param width         图片宽度
     * @param isOriginal    是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendDodoPictureMessage(String authorization, String islandSourceId, String dodoSourceId, String u, int width, int height, Boolean isOriginal) throws IOException {
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
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 发送图片消息
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url          图片url地址
     * @param height       图片高度
     * @param width        图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendDodoPictureMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String url, int width, int height) throws IOException {
        return sendDodoPictureMessage(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, url, width, height);
    }

    /**
     * 发送图片消息
     *
     * @param authorization authorization
     * @param dodoSourceId  dodo号
     * @param u             图片url地址
     * @param height        图片高度
     * @param width         图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendDodoPictureMessage(String authorization, String islandSourceId, String dodoSourceId, String u, int width, int height) throws IOException {
        String url = DodoOpenJava.BASEURL + "personal/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", 2)
                .put("messageBody", Map.of(
                        "url", u,
                        "width", width,
                        "height", height));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 发送视频消息
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url          视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendDodoVideoMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String url) throws IOException {
        return sendDodoVideoMessage(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, url);
    }

    /**
     * 发送视频消息
     *
     * @param authorization authorization
     * @param dodoSourceId  dodo号
     * @param u             视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendDodoVideoMessage(String authorization, String islandSourceId, String dodoSourceId, String u) throws IOException {
        String url = DodoOpenJava.BASEURL + "personal/message/send";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("messageType", 3)
                .put("messageBody", Map.of(
                        "url", u
                ));
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 发送视频消息
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url          视频url地址
     * @param coverUrl     封面url地址
     * @param duration     视频长度
     * @param size         视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendDodoVideoMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String url, String coverUrl, long duration, long size) throws IOException {
        return sendDodoVideoMessage(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, url, coverUrl, duration, size);
    }

    /**
     * 发送视频消息
     *
     * @param authorization authorization
     * @param dodoSourceId  dodo号
     * @param u             视频url地址
     * @param coverUrl      封面url地址
     * @param duration      视频长度
     * @param size          视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result sendDodoVideoMessage(String authorization, String islandSourceId, String dodoSourceId, String u, String coverUrl, long duration, long size) throws IOException {
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
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }
}
