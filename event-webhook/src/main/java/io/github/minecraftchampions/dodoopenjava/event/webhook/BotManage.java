package io.github.minecraftchampions.dodoopenjava.event.webhook;

/**
 * �����������Ϣ
 */
public class BotManage {
    /**
     * �˿ڣ�Ĭ��80��
     */
    public static int port = 80;

    /**
     * ������Կ
     */
    public static String SecretKey;

    /**
     * api·��(Ĭ��Ϊ��)
     */
    public static String path;

    /**
     * ����api·��
     * @param path ·��
     */
    public static void setPath(String path) {
        BotManage.path = path;
    }

    /**
     * ���ö˿�(Ĭ��80)
     * @param port �˿�
     */
    public void setPort(int port) {
        BotManage.port = port;
    }

    /**
     * ������Կ
     */
    public static void setSecretKey(String s) {
        SecretKey = s;
    }


}
