package io.github.mcchampions.DodoOpenJava.Api.V2;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;

import java.io.IOException;

/**
 * ���ֲ�ƷAPI
 * @author qscbm187531
 */

public class NTFApi {
    public static String param,url;

    /**
     * ��ȡ��Ա���ֲ�Ʒ�ж�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId dodoSourceId
     * @param platform ����ƽ̨
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberNftStatus(String clientId, String token, String islandSourceId, String dodoSourceId, String platform) throws IOException {
        return getMemberNftStatus(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, platform);
    }

    /**
     * ��ȡ��Ա���ֲ�Ʒ�ж�
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId dodoSourceId
     * @param platform ����ƽ̨
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberNftStatus(String authorization, String islandSourceId, String dodoSourceId, String platform) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/nft/status";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"platform\": \"" + platform + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ��Ա���ֲ�Ʒ�ж�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId dodoSourceId
     * @param platform ����ƽ̨
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberNftStatus(String clientId, String token, String islandSourceId, String dodoSourceId, String platform, String issuer, String series) throws IOException {
        return getMemberNftStatus(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, platform, issuer, series);
    }

    /**
     * ��ȡ��Ա���ֲ�Ʒ�ж�
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @param dodoSourceId dodoSourceId
     * @param platform ����ƽ̨
     */
    public static JSONObject getMemberNftStatus(String authorization, String islandSourceId, String dodoSourceId, String platform, String issuer, String series) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/nft/status";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"platform\": \"" + platform + "\"," +
                "    \"issuer\": \"" + issuer + "\"," +
                "    \"series\": \"" + series + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
