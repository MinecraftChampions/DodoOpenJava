package io.github.minecraftchampions.dodoopenjava.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;

/**
 * 开放秘密工具
 */
public class OpenSecretUtil {
    /**
     * WebHook解密
     *
     * @param payload   加密消息
     * @param secretKey 解密密钥
     * @return 解密后的字符串
     */
    public static String WebHookDecrypt(String payload, String secretKey) {
        try {
            return AESDecrypt(hexToBytes(payload), hexToBytes(secretKey), new byte[16], Cipher.getInstance("AES/CBC/PKCS5Padding"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * AES解密
     *
     * @param payload   加密消息
     * @param secretKey 解密密钥
     * @param iv        IV向量
     * @param cipher    AES配置
     * @return 解密密过后的值
     */
    private static String AESDecrypt(byte[] payload, byte[] secretKey, byte[] iv, Cipher cipher) throws Exception {
        var sKeySpec = new SecretKeySpec(secretKey, "AES");
        var params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
        var result = cipher.doFinal(payload);
        return new String(result);
    }

    /**
     * 十六进制字符串转字节数组
     *
     * @param hex 十六进制字符串
     * @return 字节数组
     */
    private static byte[] hexToBytes(String hex) {
        byte[] result = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length() / 2; i++) {
            int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
