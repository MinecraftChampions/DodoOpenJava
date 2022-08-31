package io.github.mcchampions.DodoOpenJava.api;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;

import java.io.IOException;

/**
 * 数字藏品API
 */

public class NTFApi {
    public static String parm,url;

    /**
     * 获取成员数字藏品判断
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId DodoId
     * @param platform 数藏平台
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getMemberNftStatus(String clientId, String token, String islandId, String dodoId, String platform, Boolean returnJSONText) throws IOException {
        return getMemberNftStatus(BaseUtil.Authorization(clientId, token), islandId, dodoId, platform, returnJSONText);
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param dodoId DodoId
     * @param platform 数藏平台
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getMemberNftStatus(String Authorization, String islandId, String dodoId, String platform, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/nft/status";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + dodoId + "\",\n" +
                "    \"platform\": \"" + platform + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if(!returnJSONText) {
            int isBandPlatformType = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("isBandPlatform");
            int isHaveIssuerType = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("isHaveIssuer");
            int isHaveSeriesType = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("isHaveSeries");
            String isHaveSeries, isHaveIssuer, isBandPlatform;

            if (isBandPlatformType == 0) {
                isBandPlatform = "否";
            } else {
                isBandPlatform = "是";
            }

            if (isHaveIssuerType == 0) {
                isHaveIssuer = "否";
            } else {
                isHaveIssuer = "是";
            }

            if (isHaveSeriesType == 0) {
                isHaveSeries = "否";
            } else {
                isHaveSeries = "是";
            }

            Parm =  "是否已绑定该数藏平台: \"" + isBandPlatform + "\"\n" +
                    "是否拥有该发行方的NFT: \"" + isHaveIssuer + "\"\n" +
                    "是否拥有该系列的NFT: \"" + isHaveSeries + "\"\n";
        }
        return Parm;
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param islandId 群号
     * @param dodoId DodoId
     * @param platform 数藏平台
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getMemberNftStatus(String clientId, String token, String islandId, String dodoId, String platform, String issuer, String series, Boolean returnJSONText) throws IOException {
        return getMemberNftStatus(BaseUtil.Authorization(clientId, token), islandId, dodoId, platform, issuer, series, returnJSONText);
    }

    /**
     * 获取成员数字藏品判断
     *
     * @param Authorization Authorization
     * @param islandId 群号
     * @param dodoId DodoId
     * @param platform 数藏平台
     * @param returnJSONText 是否返回原本的JSON参数，如果是false，则返回经过解析后的文本
     */
    public static String getMemberNftStatus(String Authorization, String islandId, String dodoId, String platform, String issuer, String series, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/member/nft/status";
        parm = "{\n" +
                "    \"islandId\": \"" + islandId + "\",\n" +
                "    \"dodoId\": \"" + dodoId + "\",\n" +
                "    \"platform\": \"" + platform + "\",\n" +
                "    \"issuer\": \"" + issuer + "\",\n" +
                "    \"series\": \"" + series + "\"\n" +
                "}";
        String Parm = NetUtil.sendRequest(parm, url, Authorization);
        if(!returnJSONText) {
            int isBandPlatformType = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("isBandPlatform");
            int isHaveIssuerType = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("isHaveIssuer");
            int isHaveSeriesType = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("isHaveSeries");
            String isHaveSeries, isHaveIssuer, isBandPlatform;

            if (isBandPlatformType == 0) {
                isBandPlatform = "否";
            } else {
                isBandPlatform = "是";
            }

            if (isHaveIssuerType == 0) {
                isHaveIssuer = "否";
            } else {
                isHaveIssuer = "是";
            }

            if (isHaveSeriesType == 0) {
                isHaveSeries = "否";
            } else {
                isHaveSeries = "是";
            }

            Parm =  "是否已绑定该数藏平台: \"" + isBandPlatform + "\"\n" +
                    "是否拥有该发行方的NFT: \"" + isHaveIssuer + "\"\n" +
                    "是否拥有该系列的NFT: \"" + isHaveSeries + "\"\n";
        }
        return Parm;
    }
}
