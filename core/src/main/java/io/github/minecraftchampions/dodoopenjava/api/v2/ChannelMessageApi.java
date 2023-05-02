package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.card.Card;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Ƶ����ϢAPI
 * @author qscbm187531
 */
public class ChannelMessageApi {
    public static String url, param;

    /**
     * �����ı���Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param Message ���͵���Ϣ
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendTextMessage(String clientId, String token, String channelId, String Message) throws IOException {
        return sendTextMessage(BaseUtil.Authorization(clientId,token), channelId, Message);
    }

    /**
     * �����ı���Ϣ
     *
     * @param Authorization Authorization
     * @param Message ���͵���Ϣ
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * �ö���Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param operateType �������ͣ�0��ȡ���ö���1���ö�
     * @param messageId ��Ϣid
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject setChannelMessageTop(String clientId, String token, String messageId, int operateType) throws IOException {
        return setChannelMessageTop(BaseUtil.Authorization(clientId,token), messageId, operateType);
    }

    /**
     * �ö���Ϣ
     *
     * @param Authorization Authorization
     * @param operateType �������ͣ�0��ȡ���ö���1���ö�
     * @param messageId ��Ϣid
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject setChannelMessageTop(String Authorization, String messageId, int operateType) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/top";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"," +
                "    \"operateType\": "+ operateType + "" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * ��ȡ��Ϣ��Ӧ�б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ��Ϣid
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelMessageReactionList(String clientId, String token, String messageId) throws IOException {
        return getChannelMessageReactionList(BaseUtil.Authorization(clientId,token), messageId);
    }

    /**
     * ��ȡ��Ϣ��Ӧ�б�
     *
     * @param Authorization Authorization
     * @param messageId ��Ϣid
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelMessageReactionList(String Authorization, String messageId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/reaction/list";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * ��ȡ��Ϣ��Ӧ�ڳ�Ա�б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ��Ϣid
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelMessageReactionMemberList(String clientId, String token, String messageId, int type, String id, int pageSize, long maxId) throws IOException {
        return getChannelMessageReactionMemberList(BaseUtil.Authorization(clientId,token), messageId, type, id ,pageSize, maxId);
    }

    /**
     * ��ȡ��Ϣ��Ӧ�ڳ�Ա�б�
     *
     * @param Authorization Authorization
     * @param messageId ��Ϣid
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelMessageReactionMemberList(String Authorization, String messageId, int type, String id, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/reaction/member/list";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\",\n" +
                "    \"emoji\": {\n" +
                "        \"type\": " + type + ",\n" +
                "        \"id\": \"" + id + "\"\n" +
                "    },\n" +
                "    \"pageSize\": "+pageSize+",\n" +
                "    \"maxId\": " + maxId +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * �ظ���Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param Message ���͵���Ϣ
     * @param referencedMessageId �ظ�����ϢID
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject referencedMessage(String clientId, String token, String channelId, String Message, String referencedMessageId) throws IOException {
        return referencedMessage(BaseUtil.Authorization(clientId,token), channelId, Message, referencedMessageId);
    }

    /**
     * �ظ���Ϣ
     *
     * @param Authorization Authorization
     * @param Message ���͵���Ϣ
     * @param channelId Ƶ����
     * @param referencedMessageId �ظ�����ϢID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject referencedMessage(String Authorization, String channelId, String Message,  String referencedMessageId) throws IOException {
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
     * ����ͼƬ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param channelId Ƶ����
     * @param Url ͼƬurl��ַ
     * @param height ͼƬ�߶�
     * @param width ͼƬ���
     * @param isOriginal �Ƿ�ԭͼ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendChannelPictureMessage(String clientId, String token, String channelId, String Url, int width, int height, Boolean isOriginal) throws IOException {
        return sendChannelPictureMessage(BaseUtil.Authorization(clientId,token), channelId, Url, width, height, isOriginal);
    }

    /**
     * ����ͼƬ��Ϣ
     *
     * @param Authorization Authorization
     * @param channelId Ƶ����
     * @param Url ͼƬurl��ַ
     * @param height ͼƬ�߶�
     * @param width ͼƬ���
     * @param isOriginal �Ƿ�ԭͼ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ����ͼƬ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param channelId Ƶ����
     * @param Url ͼƬurl��ַ
     * @param height ͼƬ�߶�
     * @param width ͼƬ���
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendChannelPictureMessage(String clientId, String token, String channelId, String Url, int width, int height) throws IOException {
        return sendChannelPictureMessage(BaseUtil.Authorization(clientId,token), channelId, Url, width, height);
    }

    /**
     * ����ͼƬ��Ϣ
     *
     * @param Authorization Authorization
     * @param channelId Ƶ����
     * @param Url ͼƬurl��ַ
     * @param height ͼƬ�߶�
     * @param width ͼƬ���
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ������Ƶ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param channelId Ƶ����
     * @param Url ��Ƶurl��ַ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendChannelVideoMessage(String clientId, String token, String channelId, String Url) throws IOException {
        return sendChannelVideoMessage(BaseUtil.Authorization(clientId,token), channelId, Url);
    }

    /**
     * ������Ƶ��Ϣ
     *
     * @param Authorization Authorization
     * @param channelId Ƶ����
     * @param Url ��Ƶurl��ַ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ������Ƶ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param channelId Ƶ����
     * @param Url ��Ƶurl��ַ
     * @param coverUrl ����url��ַ
     * @param duration ��Ƶ����
     * @param size ��Ƶ��С
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendChannelVideoMessage(String clientId, String token, String channelId, String Url, String coverUrl, long duration, long size) throws IOException {
        return sendChannelVideoMessage(BaseUtil.Authorization(clientId,token), channelId, Url, coverUrl, duration, size);
    }

    /**
     * ������Ƶ��Ϣ
     *
     * @param Authorization Authorization
     * @param channelId Ƶ����
     * @param Url ��Ƶurl��ַ
     * @param coverUrl ����url��ַ
     * @param duration ��Ƶ����
     * @param size ��Ƶ��С
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * �������ӷ�����Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param channelId Ƶ����
     * @param jumpUrl ��ת��url��ַ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendChannelShareMessage(String clientId, String token, String channelId, String jumpUrl) throws IOException {
        return sendChannelShareMessage(BaseUtil.Authorization(clientId,token), channelId, jumpUrl);
    }

    /**
     * �������ӷ�����Ϣ
     *
     * @param Authorization Authorization
     * @param channelId Ƶ����
     * @param jumpUrl ��ת��url��ַ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * �����ļ���Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param channelId Ƶ����
     * @param Url �ļ�����
     * @param name �ļ�����
     * @param size �ļ���С
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendChannelFileMessage(String clientId, String token, String channelId, String Url, String name, long size) throws IOException {
        return sendChannelFileMessage(BaseUtil.Authorization(clientId,token), channelId, Url, name, size);
    }

    /**
     * �����ļ���Ϣ
     *
     * @param Authorization Authorization
     * @param channelId Ƶ����
     * @param Url �ļ�����
     * @param name �ļ�����
     * @param size �ļ���С
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * �༭������Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ���༭����ϢID
     * @param content ������Ϣ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannelMessage(String clientId, String token, String messageId, String content) throws IOException {
        return editChannelMessage(BaseUtil.Authorization(clientId,token), messageId, content);
    }

    /**
     * �༭������Ϣ
     *
     * @param Authorization Authorization
     * @param messageId ���༭����ϢID
     * @param content ������Ϣ
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannelMessage(String Authorization, String messageId, String content) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/edit";
        param = "{" +
                "    \"messageId\": \"" + messageId	 + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": {" +
                "        \"content\": \"" + content + "\"" +
                "    }" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * ������Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ��ϢID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject withdrawChannelMessage(String clientId, String token, String messageId) throws IOException {
        return withdrawChannelMessage(BaseUtil.Authorization(clientId,token), messageId);
    }

    /**
     * ������Ϣ
     *
     * @param Authorization Authorization
     * @param messageId ��ϢID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject withdrawChannelMessage(String Authorization, String messageId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/withdraw";
        param = "{" +
                "    \"messageId\": \"" + messageId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * ������Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ��ϢID
     * @param reason �������ɣ����ɲ��ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject withdrawChannelMessageWithReason(String clientId, String token, String messageId, String reason) throws IOException {
        return withdrawChannelMessageWithReason(BaseUtil.Authorization(clientId,token), messageId, reason);
    }

    /**
     * ������Ϣ
     *
     * @param Authorization Authorization
     * @param messageId ��ϢID
     * @param reason �������ɣ����ɲ��ܴ���64���ַ���32������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ��ӱ��鷴Ӧ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ��ϢID
     * @param id ����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addChannelMessageReaction(String clientId, String token, String messageId, String id) throws IOException {
        return addChannelMessageReaction(BaseUtil.Authorization(clientId,token), messageId, id);
    }

    /**
     * ��ӱ��鷴Ӧ
     *
     * @param Authorization Authorization
     * @param messageId ��ϢID
     * @param id ����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ȡ��ָ����Ϣ�е�ָ���û��ı��鷴Ӧ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ��ϢID
     * @param id ����ID
     * @param dodoSourceId �û�dodoSourceId
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeChannelMessageReaction(String clientId, String token, String messageId, String id, String dodoSourceId) throws IOException {
        return removeChannelMessageReaction(BaseUtil.Authorization(clientId,token), messageId, id, dodoSourceId);
    }

    /**
     * ȡ��ָ����Ϣ�е�ָ���û��ı��鷴Ӧ
     *
     * @param Authorization Authorization
     * @param messageId ��ϢID
     * @param id ����ID
     * @param dodoSourceId �û�dodoSourceId
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ȡ����������ĳ����Ϣ�ı��鷴Ӧ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ��ϢID
     * @param id ����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject removeChannelMessageBotReaction(String clientId, String token, String messageId, String id) throws IOException {
        return removeChannelMessageBotReaction(BaseUtil.Authorization(clientId,token), messageId, id);
    }

    /**
     * ȡ����������ĳ����Ϣ�ı��鷴Ӧ
     *
     * @param Authorization Authorization
     * @param messageId ��ϢID
     * @param id ����ID
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
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
     * ���Ϳ�Ƭ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param channelId Ƶ����
     * @param messageBody ��Ƭ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendCardMessage(String clientId, String token, String channelId, Card messageBody) throws IOException {
        return sendCardMessage(BaseUtil.Authorization(clientId,token), channelId, messageBody);
    }

    /**
     * ���Ϳ�Ƭ��Ϣ
     *
     * @param Authorization Authorization
     * @param channelId Ƶ����
     * @param messageBody ��Ƭ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject sendCardMessage(String Authorization, String channelId,Card messageBody) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/send";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"messageType\": 6," +
                "    \"messageBody\": " + messageBody.toJSONObject().toString() +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * �༭��Ƭ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param messageId ���༭����ϢID
     * @param messageBody ��Ƭ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannelCardMessage(String clientId, String token, String messageId, Card messageBody) throws IOException {
        return editChannelCardMessage(BaseUtil.Authorization(clientId,token), messageId, messageBody);
    }

    /**
     * �༭��Ƭ��Ϣ
     *
     * @param Authorization Authorization
     * @param messageId ���༭����ϢID
     * @param messageBody ��Ƭ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannelCardMessage(String Authorization, String messageId, Card messageBody) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/message/edit";
        param = "{" +
                "    \"messageId\": \"" + messageId	 + "\"," +
                "    \"messageType\": 1," +
                "    \"messageBody\": " + messageBody.toJSONObject().toString() +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

}