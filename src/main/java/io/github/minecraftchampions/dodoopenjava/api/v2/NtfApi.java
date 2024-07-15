package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtils;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 数字藏品API
 *
 * @author qscbm187531
 */

public class NtfApi {
    /**
     * 获取成员数字藏品判断
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   dodoSourceId
     * @param platform       数藏平台
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberNftStatus(String clientId, String token, String islandSourceId, String dodoSourceId, String platform) throws IOException {
        return getMemberNftStatus(BaseUtils.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, platform);
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   dodoSourceId
     * @param platform       数藏平台
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberNftStatus(String authorization, String islandSourceId, String dodoSourceId, String platform) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/nft/status";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("platform", platform);
        return NetUtils.sendRequest(jsonObject.toString(), url, authorization);
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群号
     * @param dodoSourceId   dodoSourceId
     * @param platform       数藏平台
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getMemberNftStatus(String clientId, String token, String islandSourceId, String dodoSourceId, String platform, String issuer, String series) throws IOException {
        return getMemberNftStatus(BaseUtils.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, platform, issuer, series);
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param authorization  authorization
     * @param islandSourceId 群号
     * @param dodoSourceId   dodoSourceId
     * @param platform       数藏平台
     */
    public static Result getMemberNftStatus(String authorization, String islandSourceId, String dodoSourceId, String platform, String issuer, String series) throws IOException {
        String url = DodoOpenJava.BASEURL + "member/nft/status";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("issuer", issuer)
                .put("series", series)
                .put("platform", platform);
        return NetUtils.sendRequest(jsonObject.toString(), url, authorization);
    }
}
