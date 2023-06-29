package io.github.minecraftchampions.dodoopenjava.api.v1;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ȺAPI
 */
public class IslandApi {
    public static String url, param;

    /**
     * ��ȡȺ��Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandInfo(String clientId, String token, String islandId) throws IOException {
        return getIslandInfo(BaseUtil.Authorization(clientId, token), islandId);
    }

    /**
     * ��ȡȺ��Ϣ
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandInfo(String authorization, String islandId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/info";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ������Ⱥ�б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandList(String clientId, String token) throws IOException {
        return getIslandList(BaseUtil.Authorization(clientId,token));
    }

    /**
     * ��ȡ������Ⱥ�б�
     *
     * @param authorization authorization
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandList(String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/list";
        param = "{}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡȺ�ȼ����а�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandLevelRankList(String clientId, String token, String islandId, int pageSize, long maxId) throws IOException {
        return getIslandLevelRankList(BaseUtil.Authorization(clientId, token), islandId, pageSize, maxId);
    }

    /**
     * ��ȡȺ�ȼ����а�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandLevelRankList(String authorization, String islandId, int pageSize, long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/info";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"pageSize\": \"" + pageSize + "\"," +
                "    \"maxId\": \"" + maxId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ�����б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandMuteList(String clientId, String token, String islandId) throws IOException {
        return getIslandMuteList(BaseUtil.Authorization(clientId, token), islandId);
    }

    /**
     * ��ȡ�����б�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandMuteList(String authorization, String islandId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/mute/list";
        param = "{" +
                "    \"islandID\": \"" + islandId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ����б�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandBanList(String clientId, String token, String islandId) throws IOException {
        return getIslandBanList(BaseUtil.Authorization(clientId, token), islandId);
    }

    /**
     * ��ȡ����б�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIslandBanList(String authorization, String islandId) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/island/ban/list";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
