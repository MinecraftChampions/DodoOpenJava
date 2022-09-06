package io.github.mcchampions.DodoOpenJava.api;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;

import java.io.IOException;

/**
 * 资源API
 */
public class ResourceApi {
    public static String url;

    /**
     * 上传资源
     * @param clientId 机器人唯一标识
     * @param token 机器人鉴权Token
     * @param path 路径
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject uploadResource(String clientId, String token, String path) throws IOException {
        return uploadResource(BaseUtil.Authorization(clientId,token), path);
    }

    /**
     * 上传资源
     * @param Authorization Authorization
     * @param path 路径
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static JSONObject uploadResource(String Authorization, String path) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/resource/picture/upload";
        return new JSONObject(NetUtil.uploadFile(Authorization, path, url));
    }
}
