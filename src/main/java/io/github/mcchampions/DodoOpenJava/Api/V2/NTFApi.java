package io.github.mcchampions.DodoOpenJava.Api.V2;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;

import java.io.IOException;

/**
 * 数字藏品API
 * @author qscbm187531
 */

public class NTFApi {
    public static String param,url;

    /**
     * 获取成员数字藏品判断
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId DodoId
     * @param platform 数藏平台
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberNftStatus(String clientId, String token, String islandId, String dodoId, String platform) throws IOException {
        return getMemberNftStatus(BaseUtil.Authorization(clientId, token), islandId, dodoId, platform);
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId DodoId
     * @param platform 数藏平台
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberNftStatus(String authorization, String islandId, String dodoId, String platform) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/nft/status";
        param = "{" +
                "    \"islandId\": \"" + islandId + "\"," +
                "    \"dodoId\": \"" + dodoId + "\"," +
                "    \"platform\": \"" + platform + "\"" +
                "}";
        return  new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId DodoId
     * @param platform 数藏平台
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getMemberNftStatus(String clientId, String token, String islandId, String dodoId, String platform, String issuer, String series) throws IOException {
        return getMemberNftStatus(BaseUtil.Authorization(clientId, token), islandId, dodoId, platform, issuer, series);
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param authorization authorization
     * @param islandId 群号
     * @param dodoId DodoId
     * @param platform 数藏平台
     */
    public static JSONObject getMemberNftStatus(String authorization, String islandId, String dodoId, String platform, String issuer, String series) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/member/nft/status";
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
