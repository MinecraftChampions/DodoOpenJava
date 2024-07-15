package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 积分API
 *
 * @author qscbm187531
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
    public static Result getIntegralInfo(String clientId, String token, String islandSourceId, String dodoSourceId) throws IOException {
        return getIntegralInfo(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId);
    }

    /**
     * 查询成员积分
     *
     * @param authorization  authorization
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result getIntegralInfo(String authorization, String islandSourceId, String dodoSourceId) throws IOException {
        String url = DodoOpenJava.BASEURL + "integral/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
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
    public static Result setIntegralEdit(String clientId, String token, String islandSourceId, String dodoSourceId, int operateType, long integral) throws IOException {
        return setIntegralEdit(BaseUtil.generateAuthorization(clientId, token), islandSourceId, dodoSourceId, operateType, integral);
    }

    /**
     * 管理成员积分
     *
     * @param authorization  authorization
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @param operateType    管理类型，1：发放积分，2：扣除积分
     * @param integral       积分，必须是正整数且小于1千亿
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result setIntegralEdit(String authorization, String islandSourceId, String dodoSourceId, int operateType, long integral) throws IOException {
        String url = DodoOpenJava.BASEURL + "integral/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("operateType", operateType)
                .put("integral", integral);
        return NetUtil.sendRequest(jsonObject.toString(), url, authorization);
    }
}
