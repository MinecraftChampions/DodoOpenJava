package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ������API
 * @author qscbm187531
 */
public class BotApi {
    public static String url, param;

    /**
     * ��ȡ��������Ϣ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getBotInfo(String clientId, String token) throws IOException {
        return getBotInfo(BaseUtil.Authorization(clientId,token));
    }

    /**
     * ��ȡ��������Ϣ
     *
     * @param authorization authorization
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getBotInfo(String authorization) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/info";
        param = "{}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��������Ⱥ
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject setBotIslandLeave(String clientId, String token, String islandSourceId) throws IOException {
        return setBotIslandLeave(BaseUtil.Authorization(clientId, token), islandSourceId);
    }

    /**
     * ��������Ⱥ
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject setBotIslandLeave(String authorization, String islandSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/island/leave";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ�����������б�
     *
     * @param authorization authorization
     * @param pageSize ҳ��С�����100
     * @param maxId ��һҳ���IDֵ��Ϊ������ҳ��ѯ���ܣ���Ҫ������һҳ��ѯ��¼�е����IDֵ����ҳ�봫0
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject getBotInviteList(String authorization,int pageSize,long maxId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/invite/list";
        param = "{" +
                "    \"pageSize\":" + pageSize + "," +
                "    \"maxId\":" + maxId + "" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ӳ�Ա�������������б�
     *
     * @param authorization authorization
     * @param dodoSourceId dodoSourceId
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject addBotInvite(String authorization,String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/invite/add";
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * �Ƴ���Ա�������������б�
     *
     * @param authorization authorization
     * @param dodoSourceId dodoSourceId
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
     */
    public static JSONObject removeBotInvite(String authorization,String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/bot/invite/remove";
        param = "{" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
