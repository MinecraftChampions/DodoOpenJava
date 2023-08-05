package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 私信API
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
    public static JSONObject sendPersonalMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String message) throws IOException {
        return sendPersonalMessage(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, message);
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
    public static JSONObject sendPersonalMessage(String authorization, String islandSourceId, String dodoSourceId, String message) throws IOException {
        String url = "https://botopen.imdodo.com/api/v2/personal/message/send";
        String param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": {" +
                "        \"content\": \"" + message + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
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
    public static JSONObject sendDodoPictureMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String url, int width, int height, Boolean isOriginal) throws IOException {
        return sendDodoPictureMessage(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, url, width, height, isOriginal);
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
    public static JSONObject sendDodoPictureMessage(String authorization, String islandSourceId, String dodoSourceId, String u, int width, int height, Boolean isOriginal) throws IOException {
        String url = "https://botopen.imdodo.com/api/v2/personal/message/send";
        int original;
        if (isOriginal) {
            original = 1;
        } else {
            original = 0;
        }
        String param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"messageType\": 2," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + u + "\"," +
                "        \"width\": " + width + "," +
                "        \"height\": " + height + "," +
                "        \"isOriginal\": " + original + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
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
    public static JSONObject sendDodoPictureMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String url, int width, int height) throws IOException {
        return sendDodoPictureMessage(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, url, width, height);
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
    public static JSONObject sendDodoPictureMessage(String authorization, String islandSourceId, String dodoSourceId, String u, int width, int height) throws IOException {
        String url = "https://botopen.imdodo.com/api/v2/personal/message/send";
        String param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"messageType\": 2," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + u + "\"," +
                "        \"width\": " + width + "," +
                "        \"height\": " + height + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
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
    public static JSONObject sendDodoVideoMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String url) throws IOException {
        return sendDodoVideoMessage(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, url);
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
    public static JSONObject sendDodoVideoMessage(String authorization, String islandSourceId, String dodoSourceId, String u) throws IOException {
        String url = "https://botopen.imdodo.com/api/v2/personal/message/send";
        String param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"messageType\": 3," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + u + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 发送视频消息
     *
     * @param clientId     机器人唯一标识
     * @param token        机器人鉴权Token
     * @param dodoSourceId dodo号
     * @param url          视频url地址
     * @param coverurl     封面url地址
     * @param duration     视频长度
     * @param size         视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String clientId, String token, String islandSourceId, String dodoSourceId, String url, String coverurl, long duration, long size) throws IOException {
        return sendDodoVideoMessage(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, url, coverurl, duration, size);
    }

    /**
     * 发送视频消息
     *
     * @param authorization authorization
     * @param dodoSourceId  dodo号
     * @param u             视频url地址
     * @param coverurl      封面url地址
     * @param duration      视频长度
     * @param size          视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String authorization, String islandSourceId, String dodoSourceId, String u, String coverurl, long duration, long size) throws IOException {
        String url = "https://botopen.imdodo.com/api/v2/personal/message/send";
        String param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"messageType\": 3," +
                "    \"messageBody\": {" +
                "        \"url\": \"" + u + "\"," +
                "        \"coverurl\": \"" + coverurl + "\"," +
                "        \"duration\": " + duration + "," +
                "        \"size\": " + size + "" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
