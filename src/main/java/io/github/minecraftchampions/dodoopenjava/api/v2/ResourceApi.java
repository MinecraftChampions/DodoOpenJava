package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 资源API
 *
 * @author qscbm187531
 */
public class ResourceApi {
    /**
     * 上传资源
     *
     * @param clientId 机器人唯一标识
     * @param token    机器人鉴权Token
     * @param path     路径
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result uploadResource(String clientId, String token, String path) throws IOException {
        return uploadResource(BaseUtil.generateAuthorization(clientId, token), path);
    }

    /**
     * 上传资源
     *
     * @param authorization authorization
     * @param path          路径
     * @return JSON对象
     * @throws IOException 失败后抛出
     */
    public static Result uploadResource(String authorization, String path) throws IOException {
        String url = DodoOpenJava.BASEURL + "resource/picture/upload";
        return NetUtil.uploadFileToDodo(new HashMap<>(Map.of("Authorization", authorization)), path, url);
    }
}
