package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ����API
 */
public class IntegralApi {
    public static String url, param;

    /**
     * ��ѯ��Ա����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId ȺID
     * @param dodoSourceId DodoId
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIntegralInfo(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getIntegralInfo(BaseUtil.Authorization(clientId,token), islandSourceId, dodoSourceId);
    }

    /**
     * ��ѯ��Ա����
     *
     * @param Authorization Authorization
     * @param islandSourceId ȺID
     * @param dodoSourceId DodoId
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getIntegralInfo(String Authorization, String islandSourceId, String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/integral/info";
        param = "{\n" +
                "    \"islandSourceId\": \"" + islandSourceId +"\",\n" +
                "    \"dodoSourceId\": \"" + dodoSourceId +"\"\n" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * �����Ա����
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId ȺID
     * @param dodoSourceId DodoId
     * @param operateType �������ͣ�1�����Ż��֣�2���۳�����
     * @param integral ���֣���������������С��1ǧ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject setIntegralEdit(String clientId, String token, String islandSourceId, String dodoSourceId, int operateType, long integral) throws IOException {
        return setIntegralEdit(BaseUtil.Authorization(clientId,token), islandSourceId, dodoSourceId,operateType,integral);
    }

    /**
     * �����Ա����
     *
     * @param Authorization Authorization
     * @param islandSourceId ȺID
     * @param dodoSourceId DodoId
     * @param operateType �������ͣ�1�����Ż��֣�2���۳�����
     * @param integral ���֣���������������С��1ǧ��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject setIntegralEdit(String Authorization, String islandSourceId, String dodoSourceId, int operateType, long integral) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/integral/info";
        param = "{\n" +
                "    \"islandSourceId\": \"" + islandSourceId +"\",\n" +
                "    \"dodoSourceId\": \"" + dodoSourceId +"\"\n" +
                "    \"operateType\": " + operateType +",\n" +
                "    \"integral\": " + integral +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }
}
