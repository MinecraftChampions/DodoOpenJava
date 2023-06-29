package io.github.minecraftchampions.dodoopenjava.api.v1;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ��ԴAPI
 */
public class ResourceApi {
    public static String url;

    /**
     * �ϴ���Դ
     * @param clientId ������Ψһ��ʶ
     * @param token �����˼�ȨToken
     * @param path ·��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject uploadResource(String clientId, String token, String path) throws IOException {
        return uploadResource(BaseUtil.Authorization(clientId,token), path);
    }

    /**
     * �ϴ���Դ
     * @param authorization authorization
     * @param path ·��
     * @return JSON����
     * @throws IOException ʧ�ܺ��׳�
     */
    public static JSONObject uploadResource(String authorization, String path) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/resource/picture/upload";
        return new JSONObject(NetUtil.uploadFile(authorization, path, url));
    }
}
