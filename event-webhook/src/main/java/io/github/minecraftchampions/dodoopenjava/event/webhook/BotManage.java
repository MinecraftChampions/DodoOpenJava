package io.github.minecraftchampions.dodoopenjava.event.webhook;

/**
 * 机器人相关信息
 */
public class BotManage {
    /**
     * 端口（默认80）
     */
    public static int port = 80;

    /**
     * 解密密钥
     */
    public static String SecretKey;

    /**
     * api路径(默认为空)
     */
    public static String path;

    /**
     * 设置api路径
     * @param path 路径
     */
    public static void setPath(String path) {
        BotManage.path = path;
    }

    /**
     * 设置端口(默认80)
     * @param port 端口
     */
    public void setPort(int port) {
        BotManage.port = port;
    }

    /**
     * 解密密钥
     */
    public static void setSecretKey(String s) {
        SecretKey = s;
    }


}
