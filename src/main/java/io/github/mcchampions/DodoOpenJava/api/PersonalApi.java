package io.github.mcchampions.DodoOpenJava.api;

import com.alibaba.fastjson.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils;

import java.io.IOException;

/**
 * 私信API
 */
public class PersonalApi {
    public static String url,parm;

    /**
     * 发送文本消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param Message 发送的消息
     * @param dodoId Dodo号
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setPersonalMessageSend(String clientId, String token, String dodoId, String Message, Boolean returnJSONText) throws IOException {
        return setPersonalMessageSend(Utils.Authorization(clientId,token), dodoId, Message, returnJSONText);
    }

    /**
     * 发送文本消息
     *
     * @param Authorization Authorization
     * @param Message 发送的消息
     * @param dodoId Dodo号
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setPersonalMessageSend(String Authorization, String dodoId, String Message, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        parm = "{\n" +
                "    \"dodoId\": \"" + dodoId + "\",\n" +
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
     * 发送图片消息
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param dodoId dodo号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param isOriginal 是否原图
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setdodoPictureMessageSend(String clientId, String token, String dodoId, String Url, int width, int height, Boolean isOriginal, Boolean returnJSONText) throws IOException {
        return setdodoPictureMessageSend(Utils.Authorization(clientId,token), dodoId, Url, width, height, isOriginal, returnJSONText);
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
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setdodoPictureMessageSend(String Authorization, String dodoId, String Url, int width, int height, Boolean isOriginal, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        int Original;
        if (isOriginal) {
            Original = 1;
        } else {
            Original = 0;
        }
        parm = "{\n" +
                "    \"dodoId\": \"" + dodoId + "\",\n" +
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
     * @param dodoId dodo号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setdodoPictureMessageSend(String clientId, String token, String dodoId, String Url, int width, int height, Boolean returnJSONText) throws IOException {
        return setdodoPictureMessageSend(Utils.Authorization(clientId,token), dodoId, Url, width, height, returnJSONText);
    }

    /**
     * 发送图片消息
     *
     * @param Authorization Authorization
     * @param dodoId dodo号
     * @param Url 图片url地址
     * @param height 图片高度
     * @param width 图片宽度
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setdodoPictureMessageSend(String Authorization, String dodoId, String Url, int width, int height,  Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        parm = "{\n" +
                "    \"dodoId\": \"" + dodoId + "\",\n" +
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
     * @param dodoId dodo号
     * @param Url 视频url地址
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setdodoVideoMessageSend(String clientId, String token, String dodoId, String Url, Boolean returnJSONText) throws IOException {
        return setdodoVideoMessageSend(Utils.Authorization(clientId,token), dodoId, Url, returnJSONText);
    }

    /**
     * 发送视频消息
     *
     * @param Authorization Authorization
     * @param dodoId dodo号
     * @param Url 视频url地址
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setdodoVideoMessageSend(String Authorization, String dodoId, String Url, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        parm = "{\n" +
                "    \"dodoId\": \"" + dodoId + "\",\n" +
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
     * @param dodoId dodo号
     * @param Url 视频url地址
     * @param coverUrl 封面url地址
     * @param duration 视频长度
     * @param size 视频大小
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setdodoVideoMessageSend(String clientId, String token, String dodoId, String Url, String coverUrl, long duration, long size, Boolean returnJSONText) throws IOException {
        return setdodoVideoMessageSend(Utils.Authorization(clientId,token), dodoId, Url, coverUrl, duration, size, returnJSONText);
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
     * @param returnJSONText true返回原本的json文本，false返回消息ID
     */
    public static String setdodoVideoMessageSend(String Authorization, String dodoId, String Url, String coverUrl, long duration, long size, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/personal/message/send";
        parm = "{\n" +
                "    \"dodoId\": \"" + dodoId + "\",\n" +
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
}
