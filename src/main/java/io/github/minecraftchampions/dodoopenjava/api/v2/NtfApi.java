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
 * 数字藏品API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class NtfApi {
    @NonNull
    private Bot bot;

    /**
     * 获取成员数字藏品状态
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   dodoSourceId
     * @param platform       数藏平台
     * @return result
     */
    public Result getMemberNftStatus(String islandSourceId, String dodoSourceId,
                                     String platform) {
        String url = DodoOpenJava.BASEURL + "member/nft/status";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("platform", platform);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
    
    /**
     * 获取成员数字藏品判断
     *
     * @param islandSourceId 群号
     * @param dodoSourceId   dodoSourceId
     * @param platform       数藏平台
     */
    public Result getMemberNftStatus(String islandSourceId, String dodoSourceId,
                                     String platform, String issuer, String series) {
        String url = DodoOpenJava.BASEURL + "member/nft/status";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("islandSourceId", islandSourceId)
                .put("dodoSourceId", dodoSourceId)
                .put("issuer", issuer)
                .put("series", series)
                .put("platform", platform);
        return NetUtils.sendRequest(jsonObject.toString(), url, bot.getAuthorization());
    }
}
