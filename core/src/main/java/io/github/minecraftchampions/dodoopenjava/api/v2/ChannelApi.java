package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Ƶ��API
 */
public class ChannelApi {
    public static String url, param;
    /**
     * ��ȡƵ���б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelList(String clientId, String token, String islandSourceId) throws IOException {
        return getChannelList(BaseUtil.Authorization(clientId,token), islandSourceId);
    }

    /**
     * ��ȡƵ���б�
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelList(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/list";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡƵ����Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelInfo(String clientId, String token, String channelId) throws IOException {
        return getChannelInfo(BaseUtil.Authorization(clientId, token), channelId);
    }

    /**
     * ��ȡƵ����Ϣ
     *
     * @param authorization authorization
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelInfo(String authorization, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/info";
        param = "{" +
                "    \"channelId\": \"" + channelId + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ����Ƶ��
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param channelName Ƶ����
     * @param channelType Ƶ������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addChannel(String clientId, String token, String islandSourceId, String channelName, int channelType) throws IOException {
        return addChannel(BaseUtil.Authorization(clientId, token), islandSourceId, channelName, channelType);
    }

    /**
     * ����Ƶ��
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param channelName Ƶ����
     * @param channelType Ƶ������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addChannel(String authorization, String islandSourceId, String channelName, int channelType) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/add";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"channelName\": \"" + channelName + "\"," +
                "    \"channelType\": " + channelType + "" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
    
    /**
     * �༭Ƶ��
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param channelName Ƶ����
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannel(String clientId, String token, String islandSourceId, String channelName, String channelId) throws IOException {
        return editChannel(BaseUtil.Authorization(clientId, token), islandSourceId, channelName, channelId);
    }

    /**
     * �༭Ƶ��
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param channelName Ƶ����
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannel(String authorization, String islandSourceId, String channelName, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/edit";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"channelId\": \"" + channelId + "\"," +
                "    \"channelName\": \"" + channelName + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
    
    /**
     * ɾ��Ƶ��
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject deleteChannel(String clientId, String token, String islandSourceId, String channelId) throws IOException {
        return deleteChannel(BaseUtil.Authorization(clientId, token), islandSourceId, channelId);
    }

    /**
     * ɾ��Ƶ��
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject deleteChannel(String authorization, String islandSourceId, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/channel/remove";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"channelId\": \"" + channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
