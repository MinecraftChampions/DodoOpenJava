package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 私信API(勿用)
 * @author qscbm187531
 * @deprecated 因为Dodo开放平台还没完善这类
 */
@Deprecated
public class PersonalApi {
    public static String URL, param;

    /**
     * 发送文本消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param message 发送的消息
     * @param dodoSourceId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendPersonalMessage(String clientId, String token, String dodoSourceId, String message) throws IOException {
        return sendPersonalMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, message);
    }

    /**
     * 发送文本消息
     *
     * @param authorization authorization
     * @param message 发送的消息
     * @param dodoSourceId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendPersonalMessage(String authorization, String dodoSourceId, String message) throws IOException {
        URL = "https://botopen.imdodo.com/api/v2/personal/message/send";
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": {" +
                "        \"content\": \"" + message + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, URL, authorization));
    }

    /**
     * 发送图片消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param isOriginal 是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoPictureMessage(String clientId, String token, String dodoSourceId, String url, int width, int height, Boolean isOriginal) throws IOException {
        return sendDodoPictureMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, url, width, height, isOriginal);
    }

    /**
     * 发送图片消息
     *
     * @param authorization authorization
     * @param dodoSourceId dodo号
     * @param url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param isOriginal 是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoPictureMessage(String authorization, String dodoSourceId, String url, int width, int height, Boolean isOriginal) throws IOException {
        URL = "https://botopen.imdodo.com/api/v2/personal/message/send";
        int original;
        if (isOriginal) {
            original = 1;
        } else {
            original = 0;
        }
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"messageType\": 2," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + url + "\"," +
                "        \"width\": " + width + "," +
                "        \"height\": " + height + "," +
                "        \"isOriginal\": " + original + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, URL, authorization));
    }

    /**
     * 发送图片消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoPictureMessage(String clientId, String token, String dodoSourceId, String url, int width, int height) throws IOException {
        return sendDodoPictureMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, url, width, height);
    }

    /**
     * 发送图片消息
     *
     * @param authorization authorization
     * @param dodoSourceId dodo号
     * @param url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoPictureMessage(String authorization, String dodoSourceId, String url, int width, int height) throws IOException {
        URL = "https://botopen.imdodo.com/api/v2/personal/message/send";
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"messageType\": 2," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + url + "\"," +
                "        \"width\": " + width + "," +
                "        \"height\": " + height + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, URL, authorization));
    }

    /**
     * 发送视频消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url 视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String clientId, String token, String dodoSourceId, String url) throws IOException {
        return sendDodoVideoMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, url);
    }

    /**
     * 发送视频消息
     *
     * @param authorization authorization
     * @param dodoSourceId dodo号
     * @param url 视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String authorization, String dodoSourceId, String url) throws IOException {
        URL = "https://botopen.imdodo.com/api/v2/personal/message/send";
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"messageType\": 3," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + url + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, URL, authorization));
    }

    /**
     * 发送视频消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url 视频url地址
     * @param coverurl 封面url地址
     * @param duration 视频长度
     * @param size 视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String clientId, String token, String dodoSourceId, String url, String coverurl, long duration, long size) throws IOException {
        return sendDodoVideoMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, url, coverurl, duration, size);
    }

    /**
     * 发送视频消息
     *
     * @param authorization authorization
     * @param dodoSourceId dodo号
     * @param url 视频url地址
     * @param coverurl 封面url地址
     * @param duration 视频长度
     * @param size 视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String authorization, String dodoSourceId, String url, String coverurl, long duration, long size) throws IOException {
        URL = "https://botopen.imdodo.com/api/v2/personal/message/send";
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"messageType\": 3," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + url + "\"," +
                "        \"coverurl\": \"" + coverurl + "\"," +
                "        \"duration\": " + duration + "," +
                "        \"size\": " + size + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, URL, authorization));
    }
}
