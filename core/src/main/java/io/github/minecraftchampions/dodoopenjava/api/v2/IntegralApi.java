package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 积分API
 */
public class IntegralApi {
    /**
     * 查询成员积分
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIntegralInfo(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getIntegralInfo(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 查询成员积分
     *
     * @param Authorization  Authorization
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject getIntegralInfo(String Authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = "https://botopen.imdodo.com/api/v2/integral/info";
        String param = "{\n" +
                "    \"islandSourceId\": \"" + islandSourceId + "\",\n" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"\n" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }

    /**
     * 管理成员积分
     *
     * @param clientId       机器人唯一标识
     * @param token          机器人鉴权Token
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @param operateType    管理类型，1：发放积分，2：扣除积分
     * @param integral       积分，必须是正整数且小于1千亿
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject setIntegralEdit(String clientId, String token, String islandSourceId, String dodoSourceId, int operateType, long integral) throws IOException {
        return setIntegralEdit(BaseUtil.Authorization(clientId, token), islandSourceId, dodoSourceId, operateType, integral);
    }

    /**
     * 管理成员积分
     *
     * @param Authorization  Authorization
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @param operateType    管理类型，1：发放积分，2：扣除积分
     * @param integral       积分，必须是正整数且小于1千亿
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject setIntegralEdit(String Authorization, String islandSourceId, String dodoSourceId, int operateType, long integral) throws IOException {
        String url = "https://botopen.imdodo.com/api/v2/integral/edit";
        String param = "{\n" +
                "    \"islandSourceId\": \"" + islandSourceId + "\",\n" +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\",\n" +
                "    \"operateType\": " + operateType + ",\n" +
                "    \"integral\": " + integral +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, Authorization));
    }
}
