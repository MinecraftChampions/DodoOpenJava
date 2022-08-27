package io.github.mcchampions.DodoOpenJava.api;

import com.alibaba.fastjson.JSONObject;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;

import java.io.IOException;

/**
 * 资源API
 */
public class ResourceApi {
    public static String url;

    public static String uploadResource(String clientId, String token, String path, Boolean returnJSONText) throws IOException {
        return uploadResource(BaseUtil.Authorization(clientId,token), path, returnJSONText);
    }

    public static String uploadResource(String Authorization, String path, Boolean returnJSONText) throws IOException {
        url = "https://botopen.imdodo.com/api/v1/resource/picture/upload";
        String Parm = BaseUtil.uploadFile(Authorization, path, url);
        if (!returnJSONText) {
            String URL = JSONObject.parseObject(Parm).getJSONObject("data").getString("url");
            int width = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("width");
            int height = JSONObject.parseObject(Parm).getJSONObject("data").getIntValue("height");
            Parm = "图片URL地址: \"" + URL + "\"\n" +
                   "图片宽度: \"" + width + "\"\n" +
                   "图片高度: \"" + height + "\"";
        }
        return Parm;
    }
}
