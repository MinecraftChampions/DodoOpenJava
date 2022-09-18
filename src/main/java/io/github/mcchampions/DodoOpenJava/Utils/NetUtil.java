package io.github.mcchampions.DodoOpenJava.Utils;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * 一些关于 网络请求 的相关方法
 */
public class NetUtil {
    static OkHttpClient client = new OkHttpClient();

    /**
     * 发送POST请求
     *
     * @param parm 发送附带参数
     * @param url 链接地址
     * @param Authorization Authorization
     */
     public static String sendRequest(String url, String parm,String Authorization) throws IOException {
         Map<String, String> Header = new HashMap<>();
         Header.put("Content-Type", "application/json");
         Header.put("Authorization", Authorization);
         return sendPostRequest(url,Header,parm);
     }

    /**
     * 发送普通POST请求
     * 
     *
     * @param url 链接地址
     */
    public static String sendPostRequest(String url) throws IOException {
        return sendPostRequest(url, new HashMap<>(), "");
    }

    /**
     * 发送普通POST请求（带Header）
     *
     * @param url 链接地址
     */
    public static String sendPostRequest(String url, Map<String, String> Header) throws IOException {
        return sendPostRequest(url, Header, "");
    }

    /**
     * 发送普通POST请求（带参数）
     *
     * @param url 链接地址
     */
    public static String sendPostRequest(String url, String param) throws IOException {
        return sendPostRequest(url, null, param);
    }

    /**
     * 发送普通POST请求（带Header和参数）
     *
     * @param url 链接地址
     * @param Header Header
     * @param param 参数
     */
    public static String sendPostRequest(String url, Map<String, String> Header, String param) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json"),param))
                .build();
        for (int i = 0; i < MapUtil.ergodicMaps(Header).size(); i++) {
            request.newBuilder().addHeader(MapUtil.ergodicMaps(Header).get(i).get(0).toString(), MapUtil.ergodicMaps(Header).get(i).get(1).toString());
        }
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
     * 模拟浏览器发送请求
     * @param url 链接地址
     */
     public static String sendRequest(String url) throws IOException {
         Map<String, String> Header = new HashMap<>();
         Header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
         return sendGetRequest(url, Header);
     }

    /**
     * 发送普通的Get请求
     * @param url 链接地址
     */
    public static String sendGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String a = Objects.requireNonNull(response.body()).string();
        response.close();
        return  a;
    }

    /**
     * 发送普通的Get请求（带Header）
     * @param url 链接地址
     */
    public static String sendGetRequest(String url, Map<String, String> Header) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        for (int i = 0; i < MapUtil.ergodicMaps(Header).size(); i++) {
            request.newBuilder().addHeader(MapUtil.ergodicMaps(Header).get(i).get(0).toString(), MapUtil.ergodicMaps(Header).get(i).get(1).toString());
        }

        Response response = client.newCall(request).execute();

        String a = Objects.requireNonNull(response.body()).string();
        response.close();
        return  a;
    }

    /**
     * 从网络Url中下载文件
     *
     * @param url   路径
     * @param saveDir  保存路径
     * @param fileName 文件名称
     * @throws IOException IOException异常
     */
    public static void downloadFile(String url, File saveDir, String fileName) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();

        byte[] bytes = Objects.requireNonNull(response.body()).bytes();

        if (saveDir.exists()) {
            saveDir.mkdirs();
        }
        String folder = saveDir + File.separator + fileName;
        File file = new File(folder);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }

    /**
     * 从网络Url中下载文件（使用文件原本名字）
     *
     * @param url   路径
     * @param saveDir  保存路径
     * @throws IOException IOException异常
     */
    public static void downloadFile(String url, File saveDir) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();

        byte[] bytes = Objects.requireNonNull(response.body()).bytes();

        if (saveDir.exists()) {
            saveDir.mkdirs();
        }
        String folder = saveDir + File.separator + StringUtils.substringAfterLast(url, "/");
        File file = new File(folder);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }

    /**
     * 获取本机公网IP
     * @return IP
     * @throws IOException IOException异常
     */
    public static String getIP() throws IOException {
        return sendGetRequest("http://81.70.104.51:9020/api/public/getIp");
    }

    /**
     * 获得指定地址信息中的MAC地址，使用分隔符“-”
     *
     * @param inetAddress {@link InetAddress}
     * @return MAC地址，用-分隔
     */
    private static String getMacAddress(InetAddress inetAddress) {
        return getMacAddress(inetAddress, "-");
    }

    /**
     * 获得指定地址信息中的MAC地址
     *
     * @param inetAddress {@link InetAddress}
     * @param separator   分隔符，推荐使用“-”或者“:”
     * @return MAC地址，用-分隔
     */
    private static String getMacAddress(InetAddress inetAddress, String separator) {
        if (null == inetAddress) {
            return null;
        }

        final byte[] mac = getHardwareAddress(inetAddress);
        if (null != mac) {
            final StringBuilder sb = new StringBuilder();
            String s;
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append(separator);
                }
                // 字节转换为整数
                s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            return sb.toString();
        }

        return null;
    }

    /**
     * 获得指定地址信息中的硬件地址
     *
     * @param inetAddress {@link InetAddress}
     * @return 硬件地址
     */
    private static byte[] getHardwareAddress(InetAddress inetAddress) {
        if (null == inetAddress) {
            return null;
        }

        try {
            final NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
            if (null != networkInterface) {
                return networkInterface.getHardwareAddress();
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 获得本机MAC地址
     *
     * @return 本机MAC地址
     */
    public static String getLocalMacAddress() throws IOException {
        String macAddress = getMacAddress(getLocalhost());
        if (StrUtil.isEmpty(macAddress)) {
            return getIP();
        }
        return macAddress;
    }

    /**
     * 获取本机网卡IP地址，规则如下：
     *
     * <pre>
     * 1. 查找所有网卡地址，必须非回路（loopback）地址、非局域网地址（siteLocal）、IPv4地址
     * 2. 如果无满足要求的地址，调用 {@link InetAddress#getLocalHost()} 获取地址
     * </pre>
     * <p>
     * 此方法不会抛出异常，获取失败将返回{@code null}<br>
     * <p>
     *
     * @return 本机网卡IP地址，获取失败返回{@code null}
     */
    private static InetAddress getLocalhost() throws UnknownHostException {
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                        if (ip instanceof Inet4Address) {
                            return ip;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
        if (jdkSuppliedAddress == null) {
            throw new UnknownHostException();
        }
        return jdkSuppliedAddress;
    }
}
