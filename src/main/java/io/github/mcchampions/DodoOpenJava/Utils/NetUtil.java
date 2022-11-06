package io.github.mcchampions.DodoOpenJava.Utils;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * һЩ���� �������� ����ط���
 * @author qscbm187531
 */
public class NetUtil {
    static OkHttpClient client = new OkHttpClient();

    /**
     * ��������Dodo����ƽ̨ר�ã�
     *
     * @param parm ���͸�������
     * @param url ���ӵ�ַ
     * @param Authorization Authorization
     */
     public static String sendRequest(String parm, String url, String Authorization) throws IOException {
         Map<String, String> Header = new HashMap<>();
         Header.put("Content-Type", "application/json");
         Header.put("Authorization", Authorization);
         return sendPostJsonRequest(url,Header,parm);
     }

    /**
     * ������ͨPOST����
     *
     * @param url ���ӵ�ַ
     */
    public static String sendPostJsonRequest(String url) throws IOException {
        return sendPostJsonRequest(url, new HashMap<>(), "");
    }

    /**
     * ������ͨPOST���󣨴�Header��
     *
     * @param url ���ӵ�ַ
     */
    public static String sendPostJsonRequest(String url, Map<String, String> Header) throws IOException {
        return sendPostJsonRequest(url, Header, "");
    }

    /**
     * ������ͨPOST���󣨴�������
     *
     * @param url ���ӵ�ַ
     */
    public static String sendPostJsonRequest(String url, String param) throws IOException {
        return sendPostJsonRequest(url, null, param);
    }

    /**
     * ������ͨPOST���󣨴�Header�Ͳ�����
     *
     * @param url ���ӵ�ַ
     * @param Header Header
     * @param param ����
     */
    public static String sendPostJsonRequest(String url, Map<String, String> Header, String param) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url(url).post(RequestBody.create(MediaType.parse("application/json"), param));
        JSONObject json = new JSONObject(Header);
        for (int i = 0;i<json.keySet().size();i++) {
            builder.addHeader(Header.keySet().stream().toList().get(i), Header.values().stream().toList().get(i));
        }
        Response response = client.newCall(builder.build()).execute();
        String a = Objects.requireNonNull(response.body()).string();
        response.close();
        return a;
    }

    /**
     * ������ͨ��Get���󣨴�Header��
     * @param url ���ӵ�ַ
     */
    public static String sendGetRequest(String url, Map<String, String> Header) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        for (int i = 0; i < MapUtil.ergodicMaps(Header).size(); i++) {
            builder.addHeader(MapUtil.ergodicMaps(Header).get(i).get(0).toString(), MapUtil.ergodicMaps(Header).get(i).get(1).toString());
        }

        Response response = client.newCall(builder.build()).execute();

        String a = Objects.requireNonNull(response.body()).string();
        response.close();
        return  a;
    }

    /**
     * �ϴ���ԴͼƬ
     *
     * @param path �ļ�·��
     * @param url �ϴ�����
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
     * ģ���������������
     * @param url ���ӵ�ַ
     */
     public static String simulationBrowserRequest(String url) throws IOException {
         Map<String, String> Header = new HashMap<>();
         Header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
         return sendGetRequest(url, Header);
     }

    /**
     * ������ͨ��Get����
     * @param url ���ӵ�ַ
     */
    public static String sendGetRequest(String url) throws IOException {
        return sendGetRequest(url,new HashMap<>());
    }

    /**
     * ������Url�������ļ�
     *
     * @param url   ·��
     * @param saveDir  ����·��
     * @param fileName �ļ�����
     * @throws IOException IOException�쳣
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
     * ������Url�������ļ���ʹ���ļ�ԭ�����֣�
     *
     * @param url   ·��
     * @param saveDir  ����·��
     * @throws IOException IOException�쳣
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
     * ��ȡ��������IP
     * @return IP
     * @throws IOException IOException�쳣
     */
    public static String getIP() throws IOException {
        JSONObject json = new JSONObject(sendGetRequest("https://ipinfo.io/"));
        return json.getString("ip");
    }

    /**
     * ���ָ����ַ��Ϣ�е�MAC��ַ��ʹ�÷ָ�����-��
     *
     * @param inetAddress {@link InetAddress}
     * @return MAC��ַ����-�ָ�
     */
    private static String getMacAddress(InetAddress inetAddress) {
        return getMacAddress(inetAddress, "-");
    }

    /**
     * ���ָ����ַ��Ϣ�е�MAC��ַ
     *
     * @param inetAddress {@link InetAddress}
     * @param separator   �ָ������Ƽ�ʹ�á�-�����ߡ�:��
     * @return MAC��ַ����-�ָ�
     */
    private static String getMacAddress(InetAddress inetAddress, String separator) {
        if (inetAddress == null) {
            return null;
        }

        final byte[] mac = getHardwareAddress(inetAddress);
        if (mac != null) {
            final StringBuilder sb = new StringBuilder();
            String s;
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append(separator);
                }
                // �ֽ�ת��Ϊ����
                s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            return sb.toString();
        }

        return null;
    }

    /**
     * ���ָ����ַ��Ϣ�е�Ӳ����ַ
     *
     * @param inetAddress {@link InetAddress}
     * @return Ӳ����ַ
     */
    private static byte[] getHardwareAddress(InetAddress inetAddress) {
        if (inetAddress == null) {
            return null;
        }

        try {
            final NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
            if (networkInterface != null) {
                return networkInterface.getHardwareAddress();
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * ��ñ���MAC��ַ
     *
     * @return ����MAC��ַ
     */
    public static String getLocalMacAddress() throws IOException {
        String macAddress = getMacAddress(getLocalhost());
        if (StringUtil.isEmpty(macAddress)) {
            return getIP();
        }
        return macAddress;
    }

    /**
     * ��ȡ��������IP��ַ���������£�
     *
     * <pre>
     * 1. ��������������ַ������ǻ�·��loopback����ַ���Ǿ�������ַ��siteLocal����IPv4��ַ
     * 2. ���������Ҫ��ĵ�ַ������ {@link InetAddress#getLocalHost()} ��ȡ��ַ
     * </pre>
     * <p>
     * �˷��������׳��쳣����ȡʧ�ܽ�����{@code null}<br>
     * <p>
     *
     * @return ��������IP��ַ����ȡʧ�ܷ���{@code null}
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
