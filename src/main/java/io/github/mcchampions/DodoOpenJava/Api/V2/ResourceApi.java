package io.github.mcchampions.DodoOpenJava.Api.V2;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;

import java.io.IOException;

/**
 * ��ԴAPI
 * @author qscbm187531
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
        url = "https://botopen.imdodo.com/api/v2/resource/picture/upload";
        return new JSONObject(NetUtil.uploadFile(authorization, path, url));
    }
}
