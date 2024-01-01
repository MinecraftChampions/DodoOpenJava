package io.github.minecraftchampions.dodoopenjava.utils;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.Result;
import lombok.NonNull;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 一些 有关 网络请求 的相关方法
 */
public class NetUtil {
    /**
     * 发送请求（Dodo开放平台专用）
     *
     * @param param         发送附带参数
     * @param url           链接地址
     * @param authorization Authorization
     */
    public static Result sendRequest(@NonNull String param, @NonNull String url,
                                     @NonNull String authorization) throws IOException {
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", authorization);
        String str = sendPostJsonRequest(url, header, param);
        Result result = Result.of(new JSONObject(str));
        if (DodoOpenJava.getLogMap().containsKey(authorization)) {
            DodoOpenJava.getLogMap().get(authorization).addResult(result);
        }
        return result;
    }

    /**
     * 发送普通POST请求
     *
     * @param url 链接地址
     */
    public static String sendPostJsonRequest(@NonNull String url) throws IOException {
        return sendPostJsonRequest(url, new HashMap<>(), "");
    }

    /**
     * 发送普通POST请求（带Header）
     *
     * @param url 链接地址
     */
    public static String sendPostJsonRequest(@NonNull String url,
                                             @NonNull HashMap<String, String> Header) throws IOException {
        return sendPostJsonRequest(url, Header, "");
    }

    /**
     * 发送普通POST请求（带参数）
     *
     * @param url 链接地址
     */
    public static String sendPostJsonRequest(@NonNull String url, @NonNull String param) throws IOException {
        return sendPostJsonRequest(url, new HashMap<>(), param);
    }

    /**
     * 发送普通POST请求（带Header和参数）
     *
     * @param stringUrl 链接地址
     * @param header    Header
     * @param param     参数
     */
    public static String sendPostJsonRequest(@NonNull String stringUrl,
                                             @NonNull HashMap<String, String> header,
                                             @NonNull String param) throws IOException {
        return sendPostJsonRequest(stringUrl, header, param.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 发送普通POST请求（带Header和参数）
     *
     * @param stringUrl 链接地址
     * @param header    Header
     * @param param     参数
     */
    public static String sendPostJsonRequest(@NonNull String stringUrl,
                                             @NonNull HashMap<String, String> header,
                                             byte[] param) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(15000);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setInstanceFollowRedirects(true);
        header.forEach(con::setRequestProperty);
        con.connect();
        OutputStream out = con.getOutputStream();
        out.write(param);
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    /**
     * 发送普通的Get请求（带Header）
     *
     * @param stringUrl 链接地址
     */
    public static String sendGetRequest(@NonNull String stringUrl,
                                        @NonNull HashMap<String, String> header) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(15000);
        connection.setDoOutput(false);
        connection.setInstanceFollowRedirects(true);
        header.forEach(connection::setRequestProperty);
        connection.setUseCaches(false);
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static Result uploadFileToDodo(@NonNull HashMap<String, String> header,
                                          @NonNull String path,
                                          @NonNull String url) throws IOException {
        String str = uploadFile(header, path, url);
        Result result = Result.of(new JSONObject(str));
        String authorization = header.get("Authorization");
        if (DodoOpenJava.getLogMap().containsKey(authorization)) {
            DodoOpenJava.getLogMap().get(authorization).addResult(result);
        }
        return result;
    }


    /**
     * 上传资源图片
     *
     * @param path   文件路径
     * @param url    上传链接
     * @param header header
     */
    public static String uploadFile(@NonNull HashMap<String, String> header,
                                    @NonNull String path,
                                    @NonNull String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        File file = new File(path);
        String boundary = "===" + System.currentTimeMillis() + "===";
        header.put("Content-Type", "multipart/form-data; boundary=" + boundary);
        sb.append("--").append(boundary).append("\r\n")
                .append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                .append(file.getName()).append("\"")
                .append("\r\n").append("Content-Type: ")
                .append(HttpURLConnection.guessContentTypeFromName(file.getName())).append("\r\n")
                .append("\r\n");
        String start = sb.toString();
        byte[] bytes;
        try (InputStream inputStream = new FileInputStream(file)) {
            bytes = inputStream.readAllBytes();
        }
        String end = "\r\n" + "--" + boundary + "--" + "\r\n";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(start.getBytes());
        os.write(bytes);
        os.write(end.getBytes());
        byte[] byteArray = os.toByteArray();
        os.close();
        return sendPostJsonRequest(url, header, byteArray);
    }

    /**
     * 模拟浏览器发送请求
     *
     * @param url 链接地址
     */
    public static String simulationBrowserRequest(@NonNull String url) throws IOException {
        HashMap<String, String> Header = new HashMap<>();
        Header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        return sendGetRequest(url, Header);
    }

    /**
     * 发送普通的Get请求
     *
     * @param url 链接地址
     */
    public static String sendGetRequest(@NonNull String url) throws IOException {
        return sendGetRequest(url, new HashMap<>());
    }
}
