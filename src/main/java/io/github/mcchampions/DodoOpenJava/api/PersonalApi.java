package io.github.mcchampions.DodoOpenJava.api;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 私信API
 */
public class PersonalApi {
    public static String url, param;

    /**
     * 发送文本消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param Message 发送的消息
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendPersonalMessage(String clientId, String token, String dodoId, String Message) throws IOException {
        return sendPersonalMessage(BaseUtil.Authorization(clientId,token), dodoId, Message);
    }

    /**
     * 发送文本消息
     *
     * @param Authorization Authorization
     * @param Message 发送的消息
     * @param dodoId Dodo号
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendPersonalMessage(String Authorization, String dodoId, String Message) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        param = "{" +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": {" +
                "        \"content\": \"" + Message + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 发送图片消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoId dodo号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param isOriginal 是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoPictureMessage(String clientId, String token, String dodoId, String Url, int width, int height, Boolean isOriginal) throws IOException {
        return sendDodoPictureMessage(BaseUtil.Authorization(clientId,token), dodoId, Url, width, height, isOriginal);
    }

    /**
     * 发送图片消息
     *
     * @param Authorization Authorization
     * @param dodoId dodo号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param isOriginal 是否原图
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoPictureMessage(String Authorization, String dodoId, String Url, int width, int height, Boolean isOriginal) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        int Original;
        if (isOriginal) {
            Original = 1;
        } else {
            Original = 0;
        }
        param = "{" +
                "    \"dodoId\": \"" + dodoId + "\"," +
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
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoId dodo号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoPictureMessage(String clientId, String token, String dodoId, String Url, int width, int height) throws IOException {
        return sendDodoPictureMessage(BaseUtil.Authorization(clientId,token), dodoId, Url, width, height);
    }

    /**
     * 发送图片消息
     *
     * @param Authorization Authorization
     * @param dodoId dodo号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoPictureMessage(String Authorization, String dodoId, String Url, int width, int height) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        param = "{" +
                "    \"dodoId\": \"" + dodoId + "\"," +
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
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoId dodo号
     * @param Url 视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String clientId, String token, String dodoId, String Url) throws IOException {
        return sendDodoVideoMessage(BaseUtil.Authorization(clientId,token), dodoId, Url);
    }

    /**
     * 发送视频消息
     *
     * @param Authorization Authorization
     * @param dodoId dodo号
     * @param Url 视频url地址
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String Authorization, String dodoId, String Url) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        param = "{" +
                "    \"dodoId\": \"" + dodoId + "\"," +
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
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoId dodo号
     * @param Url 视频url地址
     * @param coverUrl 封面url地址
     * @param duration 视频长度
     * @param size 视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String clientId, String token, String dodoId, String Url, String coverUrl, long duration, long size) throws IOException {
        return sendDodoVideoMessage(BaseUtil.Authorization(clientId,token), dodoId, Url, coverUrl, duration, size);
    }

    /**
     * 发送视频消息
     *
     * @param Authorization Authorization
     * @param dodoId dodo号
     * @param Url 视频url地址
     * @param coverUrl 封面url地址
     * @param duration 视频长度
     * @param size 视频大小
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject sendDodoVideoMessage(String Authorization, String dodoId, String Url, String coverUrl, long duration, long size) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        param = "{" +
                "    \"dodoId\": \"" + dodoId + "\"," +
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
}
