package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ˽��API
 * */
public class PersonalApi {
    public static String URL, param;

    /**
     * �����ı���Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param message ���͵���Ϣ
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendPersonalMessage(String clientId, String token, String dodoSourceId, String message) throws IOException {
        return sendPersonalMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, message);
    }

    /**
     * �����ı���Ϣ
     *
     * @param authorization authorization
     * @param message ���͵���Ϣ
     * @param dodoSourceId Dodo��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ����ͼƬ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param dodoSourceId dodo��
     * @param url ͼƬurl��ַ
     * @param height ͼƬ�߶�
     * @param width ͼƬ���
     * @param isOriginal �Ƿ�ԭͼ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendDodoPictureMessage(String clientId, String token, String dodoSourceId, String url, int width, int height, Boolean isOriginal) throws IOException {
        return sendDodoPictureMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, url, width, height, isOriginal);
    }

    /**
     * ����ͼƬ��Ϣ
     *
     * @param authorization authorization
     * @param dodoSourceId dodo��
     * @param url ͼƬurl��ַ
     * @param height ͼƬ�߶�
     * @param width ͼƬ���
     * @param isOriginal �Ƿ�ԭͼ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ����ͼƬ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param dodoSourceId dodo��
     * @param url ͼƬurl��ַ
     * @param height ͼƬ�߶�
     * @param width ͼƬ���
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendDodoPictureMessage(String clientId, String token, String dodoSourceId, String url, int width, int height) throws IOException {
        return sendDodoPictureMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, url, width, height);
    }

    /**
     * ����ͼƬ��Ϣ
     *
     * @param authorization authorization
     * @param dodoSourceId dodo��
     * @param url ͼƬurl��ַ
     * @param height ͼƬ�߶�
     * @param width ͼƬ���
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ������Ƶ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param dodoSourceId dodo��
     * @param url ��Ƶurl��ַ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendDodoVideoMessage(String clientId, String token, String dodoSourceId, String url) throws IOException {
        return sendDodoVideoMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, url);
    }

    /**
     * ������Ƶ��Ϣ
     *
     * @param authorization authorization
     * @param dodoSourceId dodo��
     * @param url ��Ƶurl��ַ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ������Ƶ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param dodoSourceId dodo��
     * @param url ��Ƶurl��ַ
     * @param coverurl ����url��ַ
     * @param duration ��Ƶ����
     * @param size ��Ƶ��С
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendDodoVideoMessage(String clientId, String token, String dodoSourceId, String url, String coverurl, long duration, long size) throws IOException {
        return sendDodoVideoMessage(BaseUtil.Authorization(clientId,token), dodoSourceId, url, coverurl, duration, size);
    }

    /**
     * ������Ƶ��Ϣ
     *
     * @param authorization authorization
     * @param dodoSourceId dodo��
     * @param url ��Ƶurl��ַ
     * @param coverurl ����url��ַ
     * @param duration ��Ƶ����
     * @param size ��Ƶ��С
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
