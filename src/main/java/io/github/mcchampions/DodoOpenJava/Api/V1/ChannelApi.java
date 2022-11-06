package io.github.mcchampions.DodoOpenJava.Api.V1;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;

import java.io.IOException;

/**
 * Ƶ��API
 * @author qscbm187531
 */
public class ChannelApi {
    public static String url, param;
    /**
     * ��ȡƵ���б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelList(String clientId, String token, String islandId) throws IOException {
        return getChannelList(BaseUtil.Authorization(clientId,token), islandId);
    }

    /**
     * ��ȡƵ���б�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getChannelList(String authorization, String islandId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"" +
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
        url = "https://botopen.imdodo.com/api/v1/channel/info";
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
     * @param islandId Ⱥ��
     * @param channelName Ƶ����
     * @param channelType Ƶ������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addChannel(String clientId, String token, String islandId, String channelName, int channelType) throws IOException {
        return addChannel(BaseUtil.Authorization(clientId, token), islandId, channelName, channelType);
    }

    /**
     * ����Ƶ��
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param channelName Ƶ����
     * @param channelType Ƶ������
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject addChannel(String authorization, String islandId, String channelName, int channelType) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/add";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
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
     * @param islandId Ⱥ��
     * @param channelName Ƶ����
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannel(String clientId, String token, String islandId, String channelName, String channelId) throws IOException {
        return editChannel(BaseUtil.Authorization(clientId, token), islandId, channelName, channelId);
    }

    /**
     * �༭Ƶ��
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param channelName Ƶ����
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject editChannel(String authorization, String islandId, String channelName, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/edit";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
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
     * @param islandId Ⱥ��
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject deleteChannel(String clientId, String token, String islandId, String channelId) throws IOException {
        return deleteChannel(BaseUtil.Authorization(clientId, token), islandId, channelId);
    }

    /**
     * ɾ��Ƶ��
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param channelId Ƶ����
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject deleteChannel(String authorization, String islandId, String channelId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/channel/remove";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"channelId\": \"" + channelId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
