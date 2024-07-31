package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

/**
 * 积分API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class IntegralApi {
    @NonNull
    private Bot bot;

    /**
     * 查询成员积分
     *
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @return result
     */
    public Result getIntegralInfo(String islandSourceId, String dodoSourceId) {
        String url = DodoOpenJava.BASEURL + "integral/info";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }

    /**
     * 管理成员积分
     *
     * @param islandSourceId 群ID
     * @param dodoSourceId   DodoId
     * @param operateType    管理类型，1：发放积分，2：扣除积分
     * @param integral       积分，必须是正整数且小于1千亿
     * @return result
     */
    public Result setIntegralEdit(String islandSourceId, String dodoSourceId,
                                  int operateType, long integral) {
        String url = DodoOpenJava.BASEURL + "integral/edit";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("operateType", operateType)
                .put("integral", integral);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
