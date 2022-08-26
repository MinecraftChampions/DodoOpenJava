package io.github.mcchampions.DodoOpenJava.Utils;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 一些相关的方法
 */
public class Util {
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

         String a = Objects.requireNonNull(response.body()).string();
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
        File File = new File(path);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",File.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(path)))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",Authorization)
                .addHeader("Content-Type","multipart/form-data")
                .method("POST", body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
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

         String a = Objects.requireNonNull(response.body()).string();
         response.close();
         return  a;
     }

    /**
     * 复制文件
     *
     * @param inFile 原本的文件对象
     * @param outFile 复制到的文件对象
     * @return true就是成功，false就是失败
     */
    public static boolean copy(File inFile, File outFile) {
        if (!inFile.exists()) {
            return false;
        }

        FileChannel in = null;
        FileChannel out = null;

        try {
            in = new FileInputStream(inFile).getChannel();
            out = new FileOutputStream(outFile).getChannel();

            long pos = 0;
            long size = in.size();

            while (pos < size) {
                pos += in.transferTo(pos, 10 * 1024 * 1024, out);
            }
        } catch (IOException ioe) {
            return false;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ioe) {
                return false;
            }
        }

        return true;

    }
}
