package io.github.minecraftchampions.dodoopenjava.api.v1;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

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
     * @param islandId Ⱥ��
     * @param dodoId DodoId
     * @param platform ����ƽ̨
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberNftStatus(String clientId, String token, String islandId, String dodoId, String platform) throws IOException {
        return getMemberNftStatus(BaseUtil.Authorization(clientId, token), islandId, dodoId, platform);
    }

    /**
     * ��ȡ��Ա���ֲ�Ʒ�ж�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId DodoId
     * @param platform ����ƽ̨
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberNftStatus(String authorization, String islandId, String dodoId, String platform) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/nft/status";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"platform\": \"" + platform + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * ��ȡ��Ա���ֲ�Ʒ�ж�
     *
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param islandId Ⱥ��
     * @param dodoId DodoId
     * @param platform ����ƽ̨
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject getMemberNftStatus(String clientId, String token, String islandId, String dodoId, String platform, String issuer, String series) throws IOException {
        return getMemberNftStatus(BaseUtil.Authorization(clientId, token), islandId, dodoId, platform, issuer, series);
    }

    /**
     * ��ȡ��Ա���ֲ�Ʒ�ж�
     *
     * @param authorization authorization
     * @param islandId Ⱥ��
     * @param dodoId DodoId
     * @param platform ����ƽ̨
     */
    public static JSONObject getMemberNftStatus(String authorization, String islandId, String dodoId, String platform, String issuer, String series) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/nft/status";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"platform\": \"" + platform + "\"," +
                "    \"issuer\": \"" + issuer + "\"," +
                "    \"series\": \"" + series + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
