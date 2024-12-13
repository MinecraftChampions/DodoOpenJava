package io.github.minecraftchampions.dodoopenjava.utils;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 一些 有关 网络请求 的相关方法
 *
 * @author qscbm187531
 */
@Slf4j
public class NetUtils {
    /**
     * 发送请求（Dodo开放平台专用）
     *
     * @param param         发送附带参数
     * @param url           链接地址
     * @param authorization Authorization
     */
    public static Result sendRequest(@NonNull String param, @NonNull String url,
                                     @NonNull String authorization) {
        try {
            HashMap<String, String> header = new HashMap<>(2);
            header.put("Content-Type", "application/json");
            header.put("Authorization", authorization);
            String str = sendPostRequest(url, header, param);
            Result result = Result.of(new JSONObject(str), new JSONObject(param));
            if (DodoOpenJava.DEBUG_LOGGER_MAP.containsKey(authorization)) {
                DodoOpenJava.DEBUG_LOGGER_MAP.get(authorization).log(result);
            }
            return result;
        } catch (UnknownHostException e) {
            log.warn("解析Dodo域名错误,本错误理应是偶发事件,不会造成问题,如果一直提醒最终陷入死循环,请检查网络环境");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            return sendRequest(param, url, authorization);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送普通POST请求
     *
     * @param url 链接地址
     */
    public static String sendPostRequest(@NonNull String url) throws IOException {
        return sendPostRequest(url, new HashMap<>(0), "");
    }

    /**
     * 发送普通POST请求（带Header）
     *
     * @param url 链接地址
     */
    public static String sendPostRequest(@NonNull String url,
                                         @NonNull Map<String, String> header) throws IOException {
        return sendPostRequest(url, header, "");
    }

    /**
     * 发送普通POST请求（带参数）
     *
     * @param url 链接地址
     */
    public static String sendPostRequest(@NonNull String url, @NonNull String param) throws IOException {
        return sendPostRequest(url, new HashMap<>(0), param);
    }

    /**
     * 发送普通POST请求（带Header和参数）
     *
     * @param stringUrl 链接地址
     * @param header    Header
     * @param param     参数
     */
    public static String sendPostRequest(@NonNull String stringUrl,
                                         @NonNull Map<String, String> header,
                                         @NonNull String param) throws IOException {
        return sendPostRequest(stringUrl, header, param.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 发送普通POST请求（带Header和参数）
     *
     * @param stringUrl 链接地址
     * @param header    Header
     * @param param     参数
     */
    public static String sendPostRequest(@NonNull String stringUrl,
                                         @NonNull Map<String, String> header,
                                         byte[] param) throws IOException {
        try {
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
        } catch (SocketTimeoutException e) {
            log.error("发送请求超时", e);
            return """
                    {
                        "status": -9999,
                        "message": "发送请求失败,本返回由DodoOpenJava自动生成,请检查网络环境(等待时间:15s)"
                    }""";
        }
    }

    /**
     * 发送普通的Get请求（带Header）
     *
     * @param stringUrl 链接地址
     */
    public static String sendGetRequest(@NonNull String stringUrl,
                                        @NonNull Map<String, String> header) throws IOException {
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

    public static Result uploadFileToDodo(@NonNull Map<String, String> header,
                                          @NonNull String path,
                                          @NonNull String url) {
        try {
            String str = uploadFile(header, path, url);
            Result result = Result.of(new JSONObject(Objects.requireNonNull(str)),
                    new JSONObject(Map.of("message", "文件内容，不予展示")));
            String authorization = header.get("Authorization");
            if (DodoOpenJava.DEBUG_LOGGER_MAP.containsKey(authorization)) {
                DodoOpenJava.DEBUG_LOGGER_MAP.get(authorization).log(result);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 上传资源图片
     *
     * @param path   文件路径
     * @param url    上传链接
     * @param header header
     */
    public static String uploadFile(@NonNull Map<String, String> header,
                                    @NonNull String path,
                                    @NonNull String url) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            File file = new File(path);
            String boundary = "===" + System.currentTimeMillis() + "===";
            header.put("Content-Type", "multipart/form-data; boundary=" + boundary);
            sb.append("--").append(boundary).append("\r\nContent-Disposition: form-data; name=\"file\"; filename=\"")
                    .append(file.getName()).append("\"")
                    .append("\r\nContent-Type: ")
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
            return sendPostRequest(url, header, byteArray);
        } catch (SocketTimeoutException e) {
            log.error("发送请求超时", e);
            return null;
        }
    }

    private static final Map<String, String> browserHeaderMap = new HashMap<>(
            Map.of("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"));

    /**
     * 模拟浏览器发送请求
     *
     * @param url 链接地址
     */
    public static String simulationBrowserRequest(@NonNull String url) throws IOException {
        return sendGetRequest(url, browserHeaderMap);
    }

    /**
     * 发送普通的Get请求
     *
     * @param url 链接地址
     */
    public static String sendGetRequest(@NonNull String url) throws IOException {
        return sendGetRequest(url, new HashMap<>(0));
    }
}