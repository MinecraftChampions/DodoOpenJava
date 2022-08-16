package io.github.mcchampions.DodoOpenJava;

import okhttp3.*;

import java.io.*;


public class Utils {
    static OkHttpClient client = new OkHttpClient();

    /**
     * 发送POST请求
     *
     * @param parm 发送附带参数
     * @param url 链接地址
     * @param Authorization Authorization
     */
     public static String sendRequest(String url, String parm,String Authorization) throws IOException {
        Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json").addHeader("Authorization", Authorization)
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Authorization)
                .post(RequestBody.create(MediaType.parse("application/json"), parm))
                .build();
         Response response = client.newCall(request).execute();

         String a = response.body().string();
         response.close();
         return  a;
     }

    /**
     * 上传资源图片
     *
     * @param path 文件路径
     * @param url 上传链接
     * @param Authorization Authorization
     */
    public static String uploadFile(String Authorization, String path, String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file","Parm.png",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(path)))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",Authorization)
                .addHeader("Content-Type","multipart/form-data")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 拼接 Authorization
     *
     * @param clientId 机器人唯一标示
     * @param token 机器人鉴权Token
     * @return 返回拼接后的文本
     */
    public static String Authorization(String clientId, String token) {
        return "Bot " + clientId + "." + token;
    }
    
    /**
     * 模拟浏览器发送请求
     * @param url 链接地址
     */
     public static String sendRequest(String url) throws IOException {
        Request request = new Request.Builder()
                            .url(url)
                            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                            .get()
                            .build();
         Response response = client.newCall(request).execute();

         String a = response.body().string();
         response.close();
         return  a;
     }
}
